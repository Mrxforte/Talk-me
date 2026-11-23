using System;
using System.IO;
using TalkMe.Avatar;
using UnityEditor;
using UnityEditor.Build;
using UnityEditor.Build.Reporting;
using UnityEditor.SceneManagement;
using UnityEngine;

namespace TalkMe.Editor
{
    public static class TalkMeBuildPipeline
    {
        private const string ScenePath = "Assets/TalkMe/Scenes/Tutor.unity";
        public static readonly string[] RequiredFaceShapes = {
            "jawOpen", "eyeBlinkLeft", "eyeBlinkRight", "mouthSmileLeft", "mouthSmileRight", "browInnerUp"
        };

        [Serializable] private sealed class Approval { public bool leraApproved; public bool alexApproved; }

        [MenuItem("TalkMe/Create authoring scene")]
        public static void CreateAuthoringScene()
        {
            var scene = EditorSceneManager.NewScene(NewSceneSetup.EmptyScene, NewSceneMode.Single);
            var root = new GameObject("TalkMeAvatarBridge");
            root.AddComponent<TalkMeAvatarBridge>();
            root.AddComponent<TalkMeStageBootstrap>();
            Directory.CreateDirectory("Assets/TalkMe/Scenes");
            EditorSceneManager.SaveScene(scene, ScenePath);
            EditorBuildSettings.scenes = new[] { new EditorBuildSettingsScene(ScenePath, true) };
            AssetDatabase.SaveAssets();
        }

        [MenuItem("TalkMe/Validate approved characters")]
        public static void ValidateCharacters()
        {
            var approval = JsonUtility.FromJson<Approval>(File.ReadAllText("Assets/TalkMe/character-approval.json"));
            if (approval == null || !approval.leraApproved || !approval.alexApproved)
                throw new BuildFailedException("Both character likenesses must be reviewed and approved before export.");
            foreach (string id in new[] { "lera", "alex" })
            {
                var prefab = Resources.Load<GameObject>("Characters/" + id);
                if (prefab == null) throw new BuildFailedException("Missing approved prefab: " + id);
                var animator = prefab.GetComponentInChildren<Animator>(true);
                if (animator == null || animator.avatar == null || !animator.avatar.isValid || !animator.avatar.isHuman)
                    throw new BuildFailedException(id + " requires a valid Humanoid rig.");
                var face = TalkMeStageBootstrap.FindFace(prefab);
                if (face == null) throw new BuildFailedException(id + " has no rigged face mesh with jawOpen.");
                foreach (string shape in RequiredFaceShapes)
                    if (face.sharedMesh.GetBlendShapeIndex(shape) < 0)
                        throw new BuildFailedException(id + " is missing face shape " + shape);
            }
        }

        [MenuItem("TalkMe/Export Android library")]
        public static void ExportAndroidLibrary()
        {
            ValidateCharacters();
            CreateAuthoringScene();
            EditorUserBuildSettings.SwitchActiveBuildTarget(BuildTargetGroup.Android, BuildTarget.Android);
            PlayerSettings.SetApplicationIdentifier(NamedBuildTarget.Android, "com.aistudio.talkme.xkpqvr");
            PlayerSettings.SetScriptingBackend(NamedBuildTarget.Android, ScriptingImplementation.IL2CPP);
            PlayerSettings.Android.targetArchitectures = AndroidArchitecture.ARM64;
            PlayerSettings.Android.minSdkVersion = AndroidSdkVersions.AndroidApiLevel24;
            PlayerSettings.defaultInterfaceOrientation = UIOrientation.Portrait;
            EditorUserBuildSettings.exportAsGoogleAndroidProject = true;
            var output = Path.GetFullPath(Path.Combine(Application.dataPath, "../../unity-export"));
            var report = BuildPipeline.BuildPlayer(new BuildPlayerOptions {
                scenes = new[] { ScenePath }, locationPathName = output,
                target = BuildTarget.Android, options = BuildOptions.None
            });
            if (report.summary.result != BuildResult.Succeeded)
                throw new BuildFailedException("Unity Android export failed: " + report.summary.result);
        }
    }

    public sealed class TalkMeCharacterBuildGuard : IPreprocessBuildWithReport
    {
        public int callbackOrder => -1000;
        public void OnPreprocessBuild(BuildReport report)
        {
            if (report.summary.platform == BuildTarget.Android) TalkMeBuildPipeline.ValidateCharacters();
        }
    }
}
