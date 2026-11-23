# TalkMe Blender authoring workspace

`TalkMe-Reference-Authoring.blend` is a reference-only workspace, **not** a finished
3D person. Its source image is `References/TalkMe.png`. That image is a viewport
Empty, never an exported avatar mesh or billboard. `Lera` and `Alex` collections
are empty. Likeness approvals are false. Actual character modeling and review
remain necessary.

Recreate this workspace from the repository root:

```bash
blender --background --python blender/create_reference_project.py -- \
  --reference blender/References/TalkMe.png \
  --output blender/TalkMe-Reference-Authoring.blend
```

`export_approved_tutors.py` validates authored collections and exports approved
FBX files to Unity. It refuses the included empty reference workspace.
See `../unity/README.md` for the authoring/export process and limitations.
