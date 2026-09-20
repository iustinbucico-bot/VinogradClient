package dev.myclient.gui;

import dev.myclient.module.Module;
import dev.myclient.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MenuScreen extends Screen {

    public MenuScreen() {
        super(Text.literal("MyClient"));
    }

    private static Text label(Module m) {
        return Text.literal(m.name + ": " + (m.isEnabled() ? "ON" : "OFF"));
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int y = 36;
        for (Module m : ModuleManager.ALL) {
            addDrawableChild(ButtonWidget.builder(label(m), b -> {
                m.toggle();
                b.setMessage(label(m));
            }).dimensions(cx - 100, y, 160, 20).build());

            addDrawableChild(ButtonWidget.builder(Text.literal("..."),
                    b -> this.client.setScreen(new SettingsScreen(this, m)))
                    .dimensions(cx + 64, y, 36, 20).build());
            y += 24;
        }
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 14, 0xFFFFFFFF);
        super.render(ctx, mouseX, mouseY, delta);
    }
}
