package dev.myclient.module;

public class Setting {
    public final String name;
    public final double min;
    public final double max;
    private double value;

    public Setting(String name, double min, double max, double def) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.value = def;
    }

    public double get() { return value; }
    public float f() { return (float) value; }
    public int asInt() { return (int) Math.round(value); }
    public double norm() { return (value - min) / (max - min); }
    public void setNorm(double n) { value = min + n * (max - min); }
}
