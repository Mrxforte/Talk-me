using UnityEngine;

namespace TalkMe.Avatar
{
    /// <summary>Frames the entire approved figure, including hair, hands and shoe soles.</summary>
    [RequireComponent(typeof(Camera))]
    public sealed class TalkMeFullBodyCamera : MonoBehaviour
    {
        [SerializeField, Range(1.05f, 1.5f)] private float margin = 1.15f;
        private Transform target;
        private int lastWidth;
        private int lastHeight;

        public void SetTarget(Transform value) { target = value; Frame(); }

        private void LateUpdate()
        {
            if (Screen.width != lastWidth || Screen.height != lastHeight) Frame();
        }

        private void Frame()
        {
            if (target == null) return;
            var renderers = target.GetComponentsInChildren<Renderer>();
            if (renderers.Length == 0) return;
            var bounds = renderers[0].bounds;
            foreach (var renderer in renderers) bounds.Encapsulate(renderer.bounds);
            var camera = GetComponent<Camera>();
            float aspect = Mathf.Max(.2f, Screen.width / (float)Mathf.Max(1, Screen.height));
            float tangent = Mathf.Tan(camera.fieldOfView * Mathf.Deg2Rad * .5f);
            float distance = Mathf.Max(bounds.extents.y / tangent, bounds.extents.x / (tangent * aspect));
            distance = distance * margin + bounds.extents.z;
            transform.position = bounds.center + Vector3.forward * distance;
            transform.LookAt(bounds.center);
            camera.nearClipPlane = .01f;
            camera.farClipPlane = Mathf.Max(30f, distance + bounds.size.magnitude * 2f);
            lastWidth = Screen.width;
            lastHeight = Screen.height;
        }
    }
}
