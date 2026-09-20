package dev.myclient.mixin;

import dev.myclient.module.CustomSky;
import dev.myclient.module.ModuleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Inject(method = "getSkyColor", at = @At("RETURN"), cancellable = true)
    private void myclient$sky(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> cir) {
        CustomSky sky = ModuleManager.SKY;
        if (!sky.isEnabled()) return;
        cir.setReturnValue(cir.getReturnValue().lerp(sky.color(), sky.strength.get()));
    }
}
