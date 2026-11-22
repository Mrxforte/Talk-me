using UnityEngine;

namespace TalkMe.Avatar
{
    /// <summary>
    /// Attach to a GameObject named TalkMeAvatarBridge.
    /// Configure Animator states: Idle, Listening, Thinking, Speaking, Error.
    /// An optional float parameter SpeechAmplitude drives facial blendshapes.
    /// </summary>
    public sealed class TalkMeAvatarBridge : MonoBehaviour
    {
        [SerializeField] private Animator animator;
        [SerializeField] private TalkMeAvatarBehaviour behaviour;
        [SerializeField] private SkinnedMeshRenderer faceRenderer;
        [SerializeField] private int jawBlendShapeIndex = -1;

        public void OnAvatarCommand(string payload)
        {
            if (string.IsNullOrWhiteSpace(payload)) return;
            var fields = payload.Split('|');
            if (fields.Length != 4) return;

            string state = fields[0];
            if (state != "IDLE" && state != "LISTENING" &&
                state != "THINKING" && state != "SPEAKING" && state != "ERROR")
                return;

            if (!float.TryParse(fields[3],
                System.Globalization.NumberStyles.Float,
                System.Globalization.CultureInfo.InvariantCulture,
                out float amplitude)) return;

            amplitude = Mathf.Clamp01(amplitude);
            if (behaviour != null)
            {
                if (System.Enum.TryParse<TalkMeAvatarBehaviour.Activity>(state, true, out var activity))
                    behaviour.SetActivity(activity);
                behaviour.SetSpeechAmplitude(amplitude);
                if (System.Enum.TryParse<TalkMeAvatarBehaviour.Emotion>(fields[1], true, out var emotion))
                    behaviour.SetEmotion(emotion);
                behaviour.TriggerTopicGesture(fields[2]);
            }
            if (animator != null)
            {
                animator.CrossFade(state, 0.15f);
                animator.SetFloat("SpeechAmplitude", amplitude);
            }
            if (faceRenderer != null && jawBlendShapeIndex >= 0 &&
                jawBlendShapeIndex < faceRenderer.sharedMesh.blendShapeCount)
            {
                faceRenderer.SetBlendShapeWeight(jawBlendShapeIndex, amplitude * 100f);
            }
        }
    }
}
