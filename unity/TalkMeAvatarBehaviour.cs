using System;
using System.Globalization;
using UnityEngine;

namespace TalkMe.Avatar
{
    /// <summary>
    /// Procedural idle, breathing, blinking, gaze, expression and speech animation.
    /// Attach to the same GameObject as TalkMeAvatarBridge.
    /// All blendshape indices are optional and must be configured per character.
    /// </summary>
    public sealed class TalkMeAvatarBehaviour : MonoBehaviour
    {
        public enum Emotion { Neutral, Warm, Happy, Excited, Curious, Thoughtful, Sad, Angry, Surprised, Laughing }
        public enum Activity { Idle, Listening, Thinking, Speaking, Error }

        [Header("Rig")]
        [SerializeField] private Transform head;
        [SerializeField] private Transform chest;
        [SerializeField] private SkinnedMeshRenderer face;
        [SerializeField] private int jawOpen = -1;
        [SerializeField] private int blinkLeft = -1;
        [SerializeField] private int blinkRight = -1;
        [SerializeField] private int smileLeft = -1;
        [SerializeField] private int smileRight = -1;
        [SerializeField] private int browUp = -1;
        [SerializeField] private int browDown = -1;

        [Header("Motion")]
        [SerializeField, Range(0f, 3f)] private float breathSpeed = 0.95f;
        [SerializeField, Range(0f, 8f)] private float headSwayDegrees = 1.2f;
        [SerializeField, Range(0.1f, 20f)] private float smoothing = 9f;

        private Activity activity = Activity.Idle;
        private Emotion emotion = Emotion.Neutral;
        private float amplitude;
        private float emotionUntil;
        private float nextBlink;
        private float blinkStart = -10f;
        private float nextGesture;
        private float gestureUntil;
        private float gestureYaw;
        private float gesturePitch;
        private Quaternion headBase;
        private Vector3 chestBase;
        private float currentJaw;
        private float currentSmile;
        private float currentBrowUp;
        private float currentBrowDown;

        private void Awake()
        {
            if (head != null) headBase = head.localRotation;
            if (chest != null) chestBase = chest.localPosition;
            nextBlink = Time.time + UnityEngine.Random.Range(1.5f, 3.7f);
            nextGesture = Time.time + 2f;
        }

        public void SetActivity(Activity value) { activity = value; }
        public void SetSpeechAmplitude(float value) { amplitude = Mathf.Clamp01(value); }

        public void SetEmotion(Emotion value, float durationSeconds = 3f)
        {
            emotion = value;
            emotionUntil = Time.time + Mathf.Max(0.1f, durationSeconds);
        }

        /// <summary>
        /// Examples: GREETING, EXPLAINING, QUESTION, PRAISE, CORRECTION, FAREWELL.
        /// Animator gesture clips may be layered on top of this procedural motion.
        /// </summary>
        public void TriggerTopicGesture(string cue)
        {
            switch ((cue ?? "").ToUpperInvariant())
            {
                case "GREETING": gestureYaw = -8f; gesturePitch = -3f; break;
                case "QUESTION": gestureYaw = 8f; gesturePitch = -5f; break;
                case "EXPLAINING": gestureYaw = -5f; gesturePitch = 5f; break;
                case "PRAISE": gestureYaw = 0f; gesturePitch = 8f; break;
                case "CORRECTION": gestureYaw = 0f; gesturePitch = -6f; break;
                case "FAREWELL": gestureYaw = 7f; gesturePitch = 2f; break;
                default: gestureYaw = 0f; gesturePitch = 0f; break;
            }
            gestureUntil = Time.time + 1.3f;
        }

        private void Update()
        {
            float t = Time.time;
            float dt = 1f - Mathf.Exp(-smoothing * Time.deltaTime);
            if (t >= emotionUntil) emotion = Emotion.Neutral;

            if (t >= nextBlink)
            {
                blinkStart = t;
                nextBlink = t + UnityEngine.Random.Range(2.2f, 5.2f);
            }
            float blinkPhase = (t - blinkStart) / 0.17f;
            float blink = blinkPhase >= 0f && blinkPhase <= 1f
                ? Mathf.Sin(blinkPhase * Mathf.PI) : 0f;

            if (t >= nextGesture && activity != Activity.Speaking)
            {
                nextGesture = t + UnityEngine.Random.Range(3.5f, 7f);
                gestureYaw = UnityEngine.Random.Range(-3f, 3f);
                gesturePitch = UnityEngine.Random.Range(-2f, 2f);
                gestureUntil = t + 1f;
            }

            float activityMultiplier = activity == Activity.Thinking ? 0.6f : 1f;
            float sway = Mathf.Sin(t * 0.55f) * headSwayDegrees * activityMultiplier;
            float gestureWeight = Mathf.Clamp01((gestureUntil - t) * 2f);
            if (head != null)
            {
                var target = headBase * Quaternion.Euler(
                    Mathf.Sin(t * 0.78f) * 1.1f + gesturePitch * gestureWeight,
                    sway + gestureYaw * gestureWeight, 0f);
                head.localRotation = Quaternion.Slerp(head.localRotation, target, dt);
            }

            if (chest != null)
            {
                var breath = Mathf.Sin(t * breathSpeed * Mathf.PI * 2f) * 0.002f;
                chest.localPosition = Vector3.Lerp(chest.localPosition,
                    chestBase + new Vector3(0f, breath, 0f), dt);
            }

            float jawTarget = activity == Activity.Speaking ? amplitude * 100f : 0f;
            currentJaw = Mathf.Lerp(currentJaw, jawTarget, dt);
            float smile = emotion == Emotion.Happy || emotion == Emotion.Laughing ? 85f :
                emotion == Emotion.Warm || emotion == Emotion.Excited ? 48f : 0f;
            currentSmile = Mathf.Lerp(currentSmile, smile, dt);
            currentBrowUp = Mathf.Lerp(currentBrowUp,
                emotion == Emotion.Surprised ? 85f : emotion == Emotion.Curious ? 35f : 0f, dt);
            currentBrowDown = Mathf.Lerp(currentBrowDown,
                emotion == Emotion.Angry ? 80f : emotion == Emotion.Sad ? 35f : 0f, dt);

            Blend(jawOpen, currentJaw);
            Blend(blinkLeft, blink * 100f);
            Blend(blinkRight, blink * 100f);
            Blend(smileLeft, currentSmile);
            Blend(smileRight, currentSmile);
            Blend(browUp, currentBrowUp);
            Blend(browDown, currentBrowDown);
        }

        private void Blend(int index, float value)
        {
            if (face == null || face.sharedMesh == null || index < 0 ||
                index >= face.sharedMesh.blendShapeCount) return;
            face.SetBlendShapeWeight(index, Mathf.Clamp(value, 0f, 100f));
        }
    }
}
