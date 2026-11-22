# TalkMe Unity 3D Avatar — integration scaffold

This directory contains the **bridge contract**, not a complete Unity project
or an actual rigged 3D character.

## Character authoring in Blender
1. Model Lera with an Android-friendly polygon and texture budget.
2. Rig body and face; include a jaw-open blendshape and optional visemes.
3. Export FBX for Unity, preserving skeleton, materials and animations.
4. Validate asset licenses and mobile performance before shipping.

## Unity setup
1. Create a Unity URP Android project with a compatible Unity LTS version.
2. Import the FBX and animations.
3. Add an Animator with named states: Idle, Listening, Thinking, Speaking, Error.
4. Create a GameObject named `TalkMeAvatarBridge`, attach `TalkMeAvatarBridge.cs`.
5. Assign Animator, face mesh renderer, and jaw blendshape index in Inspector.
6. Export Unity as an Android library and integrate following Unity's
   Unity-as-a-Library instructions. UnityPlayer is NOT included in this branch.

## Android integration
- `Avatar3dCommand` encodes state, mood, gesture, and normalized amplitude.
- `UnityAvatarBridge` accepts an injected `UnityMessageSender`.
- Once a Unity Android library is available, implement the sender using the
  compatible UnityPlayer messaging API.
- Map Speaking UI states to avatar states and speech amplitude to audio level.
- Keep existing `ProAvatarView` as fallback if Unity is unavailable.
- Do not change existing microphone, STT, Gemini or TTS lifecycles.

## Status
This commit is an integration **scaffold only**. No .blend/.fbx/.glb assets,
Unity scene, Unity Android export, APK, or runtime integration is included.
Compilation and on-device performance have not been verified.
