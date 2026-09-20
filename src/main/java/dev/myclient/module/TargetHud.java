package dev.myclient.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class TargetHud extends Module {
    private static final EquipmentSlot[] SLOTS = {
            EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS,
            EquipmentSlot.FEET, EquipmentSlot.MAINHAND
    };

    private LivingEntity target;

    public TargetHud() {
        super("TargetESP");
    }

    // раз в тик выбираем цель, в рендере только рисуем
    public void tick(MinecraftClient mc) {
        target = null;
        if (!isEnabled() || mc.player == null) return;

        if (mc.targetedEntity instanceof LivingEntity le) {
            target = le;
        } else {
            LivingEntity last = mc.player.getAttacking();
            if (last != null && last.isAlive() && mc.player.age - mc.player.getLastAttackTime() < 100) {
                target = last;
            }
        }
    }

    public void render(DrawContext ctx, MinecraftClient mc) {
        LivingEntity t = target;
        if (t == null || !t.isAlive()) return;

        TextRenderer font = mc.textRenderer;
        int x = ctx.getScaledWindowWidth() / 2 + 20;
        int y = ctx.getScaledWindowHeight() / 2 + 20;
        int w = 100;
        int h = 60;

        ctx.fill(x, y, x + w, y + h, 0x99000000);
        ctx.drawText(font, t.getName().getString(), x + 4, y + 4, 0xFFFFFFFF, true);

        float hp = t.getHealth();
        float max = Math.max(1f, t.getMaxHealth());
        int barW = (int) ((w - 8) * Math.min(1f, hp / max));
        ctx.fill(x + 4, y + 15, x + w - 4, y + 19, 0xFF333333);
        ctx.fill(x + 4, y + 15, x + 4 + barW, y + 19, 0xFFB388FF);

        for (int i = 0; i < SLOTS.length; i++) {
            ItemStack stack = t.getEquippedStack(SLOTS[i]);
            if (stack.isEmpty()) continue;
            int ix = x + 4 + i * 18;
            int iy = y + 24;
            ctx.drawItem(stack, ix, iy);
            ctx.drawItemInSlot(font, stack, ix, iy);

            if (stack.isDamageable()) {
                int maxDmg = stack.getMaxDamage();
                int pct = (maxDmg - stack.getDamage()) * 100 / maxDmg;
                int color = pct > 50 ? 0xFF55FF55 : pct > 25 ? 0xFFFFFF55 : 0xFFFF5555;
                ctx.getMatrices().push();
                ctx.getMatrices().scale(0.5f, 0.5f, 1f);
                ctx.drawText(font, pct + "%", ix * 2, (iy + 18) * 2, color, false);
                ctx.getMatrices().pop();
            }
        }
    }
}
