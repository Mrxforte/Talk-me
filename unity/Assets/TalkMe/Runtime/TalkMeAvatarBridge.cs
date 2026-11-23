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
        private string lastState;
        private string lastMood;
        private string lastGesture;
        private bool hasAmplitudeParameter;

        private void Awake()
        {
            hasAmplitudeParameter = false;
            if (animator == null) return;
            foreach (var parameter in animator.parameters)
                if (parameter.name == "SpeechAmplitude" && parameter.type == AnimatorControllerParameterType.Float)
                    hasAmplitudeParameter = true;
        }

        public void BindRig(Animator body, TalkMeAvatarBehaviour motion, SkinnedMeshRenderer face)
        {
            animator = body;
            behaviour = motion;
            faceRenderer = face;
            jawBlendShapeIndex = face != null && face.sharedMesh != null
                ? face.sharedMesh.GetBlendShapeIndex("jawOpen") : -1;
            lastState = lastMood = lastGesture = null;
            Awake();
        }

        public void OnSelectTutor(string id)
        {
            GetComponent<TalkMeStageBootstrap>()?.SelectTutor(id);
        }

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
                out float amplitude) || float.IsNaN(amplitude) || float.IsInfinity(amplitude)) return;

            amplitude = state == "SPEAKING" ? Mathf.Clamp01(amplitude) : 0f;
            bool stateChanged = lastState != state;
            if (behaviour != null)
            {
                if (System.Enum.TryParse<TalkMeAvatarBehaviour.Activity>(state, true, out var activity))
                    behaviour.SetActivity(activity);
                behaviour.SetSpeechAmplitude(amplitude);
                string mood = fields[1] == "JOYFUL" ? "Happy" :
                    fields[1] == "AMUSED" ? "Happy" :
                    fields[1] == "CONCERNED" ? "Sad" :
                    fields[1] == "FIRM" ? "Angry" : fields[1];
                if ((stateChanged || lastMood != fields[1]) &&
                    System.Enum.TryParse<TalkMeAvatarBehaviour.Emotion>(mood, true, out var emotion))
                    behaviour.SetEmotion(emotion);
                // Audio callbacks update amplitude frequently; never restart a gesture on every sample.
                if (state == "SPEAKING" && (stateChanged || lastGesture != fields[2]))
                    behaviour.TriggerTopicGesture(fields[2]);
            }
            if (animator != null)
            {
                int stateHash = Animator.StringToHash(state);
                if (stateChanged && animator.HasState(0, stateHash)) animator.CrossFade(stateHash, 0.15f);
                if (hasAmplitudeParameter) animator.SetFloat("SpeechAmplitude", amplitude);
            }
            if (behaviour == null && faceRenderer != null && faceRenderer.sharedMesh != null && jawBlendShapeIndex >= 0 &&
                jawBlendShapeIndex < faceRenderer.sharedMesh.blendShapeCount)
            {
                faceRenderer.SetBlendShapeWeight(jawBlendShapeIndex, amplitude * 100f);
            }
            lastState = state;
            lastMood = fields[1];
            lastGesture = fields[2];
        }
    }
}
