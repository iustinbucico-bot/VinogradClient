package dev.myclient.module;

import java.util.List;

public final class ModuleManager {
    public static final ViewModel VIEW_MODEL = new ViewModel();
    public static final SwingSpeed SWING = new SwingSpeed();
    public static final CustomSky SKY = new CustomSky();
    public static final TargetHud TARGET_HUD = new TargetHud();

    public static final List<Module> ALL = List.of(VIEW_MODEL, SWING, SKY, TARGET_HUD);

    private ModuleManager() {}
}
