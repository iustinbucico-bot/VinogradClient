package dev.myclient.module;

import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    public final String name;
    public final List<Setting> settings = new ArrayList<>();
    public int width = -1;
    private boolean enabled;

    protected Module(String name) {
        this.name = name;
    }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean v) { enabled = v; }
    public void toggle() { enabled = !enabled; }

    protected Setting add(Setting s) {
        settings.add(s);
        return s;
    }
}
