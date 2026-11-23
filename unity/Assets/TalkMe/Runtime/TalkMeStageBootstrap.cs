using System.Collections.Generic;
using UnityEngine;

namespace TalkMe.Avatar
{
    /// <summary>Loads artist-approved rigged prefabs. Never creates substitute people or image billboards.</summary>
    public sealed class TalkMeStageBootstrap : MonoBehaviour
    {
        private readonly Dictionary<string, GameObject> tutors = new Dictionary<string, GameObject>();
        private TalkMeFullBodyCamera framing;
        private string selected;

        private void Start()
        {
            Application.targetFrameRate = 30;
            Screen.orientation = ScreenOrientation.Portrait;
            var camera = Camera.main;
            if (camera == null)
            {
                var cameraObject = new GameObject("TutorCamera");
                cameraObject.tag = "MainCamera";
                camera = cameraObject.AddComponent<Camera>();
            }
            camera.fieldOfView = 32f;
            camera.clearFlags = CameraClearFlags.SolidColor;
            camera.backgroundColor = new Color(.12f, .13f, .16f);
            framing = camera.GetComponent<TalkMeFullBodyCamera>() ?? camera.gameObject.AddComponent<TalkMeFullBodyCamera>();
            CreateLight("KeyLight", new Vector3(30f, -25f, 0f), 1.1f);
            CreateLight("FillLight", new Vector3(15f, 140f, 0f), .45f);
            foreach (string id in new[] { "lera", "alex" })
            {
                var prefab = Resources.Load<GameObject>("Characters/" + id);
                if (prefab == null)
                {
                    Debug.LogError("Missing approved tutor prefab: Characters/" + id);
                    continue;
                }
                var model = Instantiate(prefab);
                model.name = id;
                model.SetActive(false);
                tutors.Add(id, model);
            }
            SelectTutor("lera");
        }

        public void SelectTutor(string id)
        {
            if (selected == id || !tutors.TryGetValue(id, out var model)) return;
            foreach (var tutor in tutors.Values) tutor.SetActive(false);
            model.SetActive(true);
            var animator = model.GetComponentInChildren<Animator>();
            var behaviour = model.GetComponent<TalkMeAvatarBehaviour>() ?? model.AddComponent<TalkMeAvatarBehaviour>();
            var face = FindFace(model);
            behaviour.BindRig(animator, face);
            var bridge = GetComponent<TalkMeAvatarBridge>() ?? gameObject.AddComponent<TalkMeAvatarBridge>();
            bridge.BindRig(animator, behaviour, face);
            framing.SetTarget(model.transform);
            selected = id;
        }

        public static SkinnedMeshRenderer FindFace(GameObject model)
        {
            foreach (var face in model.GetComponentsInChildren<SkinnedMeshRenderer>(true))
                if (face.sharedMesh != null && face.sharedMesh.GetBlendShapeIndex("jawOpen") >= 0) return face;
            return null;
        }

        private static void CreateLight(string name, Vector3 rotation, float intensity)
        {
            var go = new GameObject(name);
            go.transform.rotation = Quaternion.Euler(rotation);
            var light = go.AddComponent<Light>();
            light.type = LightType.Directional;
            light.intensity = intensity;
            light.shadows = LightShadows.Soft;
        }
    }
}
