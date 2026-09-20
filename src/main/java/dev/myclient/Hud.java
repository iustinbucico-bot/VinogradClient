package dev.myclient;

import dev.myclient.module.Module;
import dev.myclient.module.ModuleManager;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

public final class Hud {
    private static final int ACCENT = 0xFFB388FF;
    private static int lastFps = -1;
    private static String watermark = "MyClient";

    public static void register() {
        HudRenderCallback.EVENT.register((ctx, tickDelta) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.options.hudHidden) return;
            TextRenderer font = mc.textRenderer;

            int fps = mc.getCurrentFps();
            if (fps != lastFps) {
                lastFps = fps;
                watermark = "MyClient | " + fps + " FPS";
            }
            ctx.drawTextWithShadow(font, watermark, 4, 4, ACCENT);

            int right = ctx.getScaledWindowWidth() - 4;
            int y = 4;
            for (Module m : ModuleManager.ALL) {
                if (!m.isEnabled()) continue;
                if (m.width < 0) m.width = font.getWidth(m.name);
                ctx.drawTextWithShadow(font, m.name, right - m.width, y, ACCENT);
                y += 10;
            }

            ModuleManager.TARGET_HUD.render(ctx, mc);
        });
    }

    private Hud() {}
}
