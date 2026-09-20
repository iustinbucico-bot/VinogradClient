package dev.myclient.module;

public class SwingSpeed extends Module {
    // в ванилле 6 тиков; больше = медленнее взмах
    public final Setting duration = add(new Setting("Swing ticks", 2, 20, 12));

    public SwingSpeed() {
        super("SwingAnimation");
    }
}
