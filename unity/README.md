# TalkMe Unity + Blender source project

Open this folder in Unity 6000.0.65f1. It contains an authoring scene, full-body
camera framing, lighting, tutor selection, procedural head/breath/blink/expression
controls, Android messages and a guarded Android-library export command.

## Actual status

| Component | Status |
|---|---|
| Unity project structure and source scripts | Added |
| Blender reference workspace | Added; no character meshes |
| Approved rigged Lera/Alex models | **Missing** |
| Likeness against the original picture | **Not achieved or approved** |
| Unity Editor execution/export here | **Unavailable and unverified** |
| Exported unityLibrary | **Missing** |
| Android UnityPlayer initialization/display | **Still required** |

The APK continues to render its existing native tutor. Source files and library
linkage alone do not display a Unity character. This is not a completed,
pixel-perfect avatar implementation. Rejected automatic meshes and billboards
are not included as substitute characters.

## Blender authoring

Open `../blender/TalkMe-Reference-Authoring.blend`. Its original image is a viewport
reference, not a texture wrapped around a fake character. Model the actual people
in the `Lera` and `Alex` collections, preserving faces, bodies, hair, clothing,
accessories and full shoes. Each collection needs one armature and skinned meshes.
An empty collection, reference image and camera are not character models.

Required face shapes: `jawOpen`, `eyeBlinkLeft`, `eyeBlinkRight`, `mouthSmileLeft`,
`mouthSmileRight`, `browInnerUp`. Mouth interiors and additional phoneme shapes
must be authored as needed. Amplitude drives approximate jaw opening, not
accurate phoneme/viseme alignment.

Review front, side, back, talking and portrait framing before setting the scene's
`lera_likeness_approved` and `alex_likeness_approved` properties to true.
They are deliberately false in the provided reference workspace.

From the repository root:

```bash
blender --background blender/TalkMe-Reference-Authoring.blend \
  --python blender/export_approved_tutors.py -- \
  --output-dir unity/Assets/TalkMe/Characters
```

The exporter refuses missing/unapproved people, missing rigs and missing face
shapes. It preserves actual geometry, skeletons, shapes and animations in FBX.

## Unity preparation and export

Import both FBX files, set Rig to Humanoid, verify bone mappings, and save:

- `Assets/TalkMe/Resources/Characters/lera.prefab`
- `Assets/TalkMe/Resources/Characters/alex.prefab`

After reviewing their actual Unity renders, update `character-approval.json`.
`TalkMe > Validate approved characters` rejects invalid or missing assets.
The Android build preprocessor also enforces validation for normal Build/Run.

`Assets/TalkMe/Scenes/Tutor.unity` boots approved prefabs and frames the complete
figure for the current aspect ratio. `TalkMe > Create authoring scene` recreates
that scene. Install Android Build Support, then use `TalkMe > Export Android library`,
or invoke `TalkMe.Editor.TalkMeBuildPipeline.ExportAndroidLibrary` in licensed
batch mode. The output is `../unity-export/`, which Git ignores.

Android accepts `-PtalkmeUnity=true` only when `unity-export/unityLibrary` exists.
This links the dependency; it does not create or display UnityPlayer. A full-screen
native host and Unity lifecycle handling still need implementation and testing
against the actual export. Keep the working conversation/microphone ownership.

Android sends `OnSelectTutor` with `lera`/`alex`, and `OnAvatarCommand` with
`STATE|MOOD|GESTURE|AMPLITUDE` to `TalkMeAvatarBridge`. Tutor playback drives mouth
amplitude. Frequent amplitude updates do not restart state/gesture animations.
Procedural bone updates run after Animator evaluation.

No Unity license, signing credentials or generated runtime binaries are stored.
Unity C# compilation and on-device rendering have not been verified here.

## Checks performed on 2026-11-23

- Android Gradle debug build succeeded; 70 unit/Robolectric tests passed.
- Blender 4.3.2 created and reopened the reference workspace; it contains zero
  character meshes and false likeness approvals, as declared above.
- Blender export rejected that unapproved workspace with exit code 2.
- Android's Unity opt-in rejected the missing library export as intended.
- C# syntax parsed without errors; scene script GUID references resolve. This is
  a syntax/reference check, not Unity Editor compilation or a rendered result.
- `compile_applet` is not installed here; Android Gradle performed compilation.

Official references:

- https://unity.com/releases/editor/whats-new/6000.0.65f1
- https://docs.unity3d.com/6000.0/Documentation/Manual/UnityasaLibrary-Android.html
