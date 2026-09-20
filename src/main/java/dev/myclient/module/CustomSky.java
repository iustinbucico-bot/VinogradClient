package dev.myclient.module;

import net.minecraft.util.math.Vec3d;

public class CustomSky extends Module {
    public final Setting r = add(new Setting("Red", 0, 1, 0.55));
    public final Setting g = add(new Setting("Green", 0, 1, 0.35));
    public final Setting b = add(new Setting("Blue", 0, 1, 0.9));
    public final Setting strength = add(new Setting("Strength", 0, 1, 0.3));

    public CustomSky() {
        super("Sky");
    }

    public Vec3d color() {
        return new Vec3d(r.get(), g.get(), b.get());
    }
}
