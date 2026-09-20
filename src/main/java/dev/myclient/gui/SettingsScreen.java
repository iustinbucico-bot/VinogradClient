package dev.myclient.gui;

import dev.myclient.module.Module;
import dev.myclient.module.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {
    private final Screen parent;
    private final Module module;

    public SettingsScreen(Screen parent, Module module) {
        super(Text.literal(module.name));
        this.parent = parent;
        this.module = module;
    }

    @Override
    protected void init() {
        int cx = this.width / 2;
        int y = 36;
        for (Setting s : module.settings) {
            addDrawableChild(new SettingSlider(cx - 100, y, 200, 20, s));
            y += 24;
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("Back"), b -> close())
                .dimensions(cx - 50, y + 8, 100, 20).build());
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        this.renderBackground(ctx);
        ctx.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 14, 0xFFFFFFFF);
        super.render(ctx, mouseX, mouseY, delta);
    }

    private static class SettingSlider extends SliderWidget {
        private final Setting setting;

        SettingSlider(int x, int y, int w, int h, Setting setting) {
            super(x, y, w, h, Text.empty(), setting.norm());
            this.setting = setting;
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            setMessage(Text.literal(setting.name + ": " + String.format("%.2f", setting.get())));
        }

        @Override
        protected void applyValue() {
            setting.setNorm(this.value);
        }
    }
}
