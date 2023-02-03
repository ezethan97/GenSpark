package org.example;

public class Equipment {
    private String name;
    private String type;
    private int value;

    public Equipment(String name, String type, int value)
    {
        this.name = name;
        this.type = type;
        this.value = value;
    }
    public String getType()
    {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        String type = this.type == "weapon"?"dmg":"res";
        return String.format("%s: %s (+%d%s)", this.type, name, value, type);
    }
}
