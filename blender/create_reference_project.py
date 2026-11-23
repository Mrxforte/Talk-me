"""Create a Blender authoring workspace from the supplied reference, not a finished avatar."""
import argparse
import math
import sys
from pathlib import Path

import bpy
from mathutils import Vector


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--reference", required=True, type=Path)
    parser.add_argument("--output", required=True, type=Path)
    args = parser.parse_args(sys.argv[sys.argv.index("--") + 1:])
    if not args.reference.is_file():
        raise FileNotFoundError(args.reference)
    bpy.ops.wm.read_factory_settings(use_empty=True)
    scene = bpy.context.scene
    scene.unit_settings.system = "METRIC"
    scene.unit_settings.scale_length = 1
    scene["asset_status"] = "REFERENCE_ONLY_NO_CHARACTER_MESHES"
    scene["lera_likeness_approved"] = False
    scene["alex_likeness_approved"] = False
    image = bpy.data.images.load(str(args.reference.resolve()))
    reference = bpy.data.objects.new("TalkMe original likeness reference", None)
    reference.empty_display_type = "IMAGE"
    reference.data = image
    reference.empty_display_size = 2.85
    reference.empty_image_depth = "BACK"
    reference.location = (0, .25, .98)
    reference.rotation_euler = (math.pi / 2, 0, 0)
    scene.collection.objects.link(reference)
    for name in ("Lera", "Alex"):
        scene.collection.children.link(bpy.data.collections.new(name))
    camera_data = bpy.data.cameras.new("ReferenceCamera")
    camera = bpy.data.objects.new("ReferenceCamera", camera_data)
    scene.collection.objects.link(camera)
    camera.location = (0, -4, .98)
    camera.rotation_euler = (Vector((0, .25, .98)) - camera.location).to_track_quat("-Z", "Y").to_euler()
    camera_data.type = "ORTHO"
    camera_data.ortho_scale = 2.2
    scene.camera = camera
    scene.render.resolution_x = 1536
    scene.render.resolution_y = 1024
    for screen in bpy.data.screens:
        for area in screen.areas:
            if area.type == "VIEW_3D":
                area.spaces.active.region_3d.view_rotation = camera.rotation_euler.to_quaternion()
                area.spaces.active.region_3d.view_location = Vector((0, 0, .98))
                area.spaces.active.region_3d.view_distance = 3
                area.spaces.active.region_3d.view_perspective = "ORTHO"
    args.output.parent.mkdir(parents=True, exist_ok=True)
    image.filepath = "//References/" + args.reference.name
    bpy.ops.wm.save_as_mainfile(filepath=str(args.output.resolve()), compress=True)
    print("Created reference-only authoring workspace; no character meshes are present.")


if __name__ == "__main__":
    main()
