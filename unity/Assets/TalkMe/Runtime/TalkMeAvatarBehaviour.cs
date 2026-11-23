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
        private float gestureStarted;
        private string gestureCue;
        private Quaternion headBase;
        private Vector3 chestBase;
        private float currentJaw;
        private float currentSmile;
        private float currentBrowUp;
        private float currentBrowDown;
        private bool rigBound;

        public void BindRig(Animator body, SkinnedMeshRenderer mesh)
        {
            if (rigBound || body == null || !body.isHuman || mesh == null || mesh.sharedMesh == null) return;
            head = body.GetBoneTransform(HumanBodyBones.Head);
            chest = body.GetBoneTransform(HumanBodyBones.Chest);
            face = mesh;
            jawOpen = mesh.sharedMesh.GetBlendShapeIndex("jawOpen");
            blinkLeft = mesh.sharedMesh.GetBlendShapeIndex("eyeBlinkLeft");
            blinkRight = mesh.sharedMesh.GetBlendShapeIndex("eyeBlinkRight");
            smileLeft = mesh.sharedMesh.GetBlendShapeIndex("mouthSmileLeft");
            smileRight = mesh.sharedMesh.GetBlendShapeIndex("mouthSmileRight");
            browUp = mesh.sharedMesh.GetBlendShapeIndex("browInnerUp");
            browDown = mesh.sharedMesh.GetBlendShapeIndex("browDownLeft");
            if (head != null) headBase = head.localRotation;
            if (chest != null) chestBase = chest.localPosition;
            rigBound = true;
        }

        private void OnDisable()
        {
            amplitude = 0f;
            currentJaw = currentSmile = currentBrowUp = currentBrowDown = 0f;
            activity = Activity.Idle;
            if (!rigBound) return;
            if (head != null) head.localRotation = headBase;
            if (chest != null) chest.localPosition = chestBase;
            Blend(jawOpen, 0f);
            Blend(blinkLeft, 0f);
            Blend(blinkRight, 0f);
        }

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
            gestureCue = (cue ?? "").ToUpperInvariant();
            gestureStarted = Time.time;
            switch (gestureCue)
            {
                case "NOD": case "DOUBLE_NOD": gestureYaw = 0f; gesturePitch = 7f; break;
                case "TILT_LEFT": gestureYaw = -6f; gesturePitch = -2f; break;
                case "TILT_RIGHT": gestureYaw = 6f; gesturePitch = -2f; break;
                case "GLANCE_UP": gestureYaw = 2f; gesturePitch = -5f; break;
                case "GLANCE_DOWN": gestureYaw = -2f; gesturePitch = 5f; break;
                case "LEAN_IN": gestureYaw = 0f; gesturePitch = 4f; break;
                case "EASE_BACK": gestureYaw = 0f; gesturePitch = -3f; break;
                case "SHAKE": gestureYaw = -7f; gesturePitch = 0f; break;
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

        private void LateUpdate()
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
                gestureCue = "STEADY";
                gestureStarted = t;
                gestureUntil = t + 1.3f;
            }

            float activityMultiplier = activity == Activity.Thinking ? 0.6f : 1f;
            float sway = Mathf.Sin(t * 0.55f) * headSwayDegrees * activityMultiplier;
            float gesturePhase = Mathf.Clamp01((t - gestureStarted) / 1.3f);
            float gestureWeight = t < gestureUntil ? Mathf.Sin(gesturePhase * Mathf.PI) : 0f;
            float pitch = gesturePitch * gestureWeight;
            float yaw = gestureYaw * gestureWeight;
            if (gestureCue == "NOD" || gestureCue == "DOUBLE_NOD")
                pitch *= Mathf.Sin(gesturePhase * Mathf.PI * 2f * (gestureCue == "DOUBLE_NOD" ? 2f : 1f));
            if (gestureCue == "SHAKE") yaw *= Mathf.Sin(gesturePhase * Mathf.PI * 4f);
            if (head != null)
            {
                var target = headBase * Quaternion.Euler(
                    Mathf.Sin(t * 0.78f) * 1.1f + pitch,
                    sway + yaw, 0f);
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
