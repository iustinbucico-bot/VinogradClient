package dev.myclient.module;

public class ViewModel extends Module {
    public final Setting x = add(new Setting("X", -1.0, 1.0, 0.0));
    public final Setting y = add(new Setting("Y", -1.0, 1.0, 0.0));
    public final Setting z = add(new Setting("Z", -1.0, 1.0, 0.0));
    public final Setting scale = add(new Setting("Scale", 0.4, 1.6, 1.0));

    public ViewModel() {
        super("ViewModel");
    }
                                             }
