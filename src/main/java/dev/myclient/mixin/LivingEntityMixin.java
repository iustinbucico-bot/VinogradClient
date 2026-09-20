package dev.myclient.mixin;

import dev.myclient.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "getHandSwingDuration", at = @At("RETURN"), cancellable = true)
    private void myclient$swing(CallbackInfoReturnable<Integer> cir) {
        if (!ModuleManager.SWING.isEnabled()) return;
        if ((Object) this != MinecraftClient.getInstance().player) return;
        cir.setReturnValue(ModuleManager.SWING.duration.asInt());
    }
}
