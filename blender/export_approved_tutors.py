"""Validate and export artist-authored, likeness-approved tutors to Unity FBX."""
import argparse
import json
import sys
from pathlib import Path

import bpy

REQUIRED_SHAPES = {"jawOpen", "eyeBlinkLeft", "eyeBlinkRight", "mouthSmileLeft", "mouthSmileRight", "browInnerUp"}


def validate():
    characters = []
    for name in ("Lera", "Alex"):
        if not bpy.context.scene.get(name.lower() + "_likeness_approved", False):
            raise ValueError(name + " likeness has not been approved against the original reference.")
        collection = bpy.data.collections.get(name)
        objects = list(collection.all_objects) if collection else []
        rigs = [obj for obj in objects if obj.type == "ARMATURE"]
        meshes = [obj for obj in objects if obj.type == "MESH"]
        if len(rigs) != 1 or not meshes:
            raise ValueError(name + " needs one body armature and actual mesh geometry.")
        if not any(REQUIRED_SHAPES.issubset({key.name for key in obj.data.shape_keys.key_blocks})
                   for obj in meshes if obj.data.shape_keys):
            raise ValueError(name + " is missing required face shape keys.")
        characters.append((name, objects))
    return characters


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--output-dir", type=Path, required=True)
    parser.add_argument("--validate-only", action="store_true")
    args = parser.parse_args(sys.argv[sys.argv.index("--") + 1:])
    try:
        characters = validate()
    except ValueError as error:
        print(json.dumps({"exported": False, "reason": str(error)}))
        raise SystemExit(2)
    if args.validate_only:
        print(json.dumps({"validated": [name for name, _ in characters]}))
        return
    args.output_dir.mkdir(parents=True, exist_ok=True)
    for name, objects in characters:
        bpy.ops.object.select_all(action="DESELECT")
        for obj in objects:
            obj.hide_set(False)
            obj.select_set(True)
        bpy.context.view_layer.objects.active = objects[0]
        bpy.ops.export_scene.fbx(
            filepath=str((args.output_dir / (name + ".fbx")).resolve()),
            use_selection=True, object_types={"ARMATURE", "MESH"},
            add_leaf_bones=False, use_armature_deform_only=True,
            use_mesh_modifiers=False, axis_forward="-Z", axis_up="Y",
            apply_unit_scale=True, bake_anim=True, bake_anim_use_all_actions=False,
            path_mode="COPY", embed_textures=True,
        )
    print(json.dumps({"exported": [name for name, _ in characters]}))


if __name__ == "__main__":
    main()
