package com.example.pokemonapi;

public class Card {

    private String name;
    private String type;
    private String race = "None";
    private String desc;
    private String archetype = "None";

    @Override
    public String toString(){
        return "Card{" +
                "\n\tname='" + name + '\'' +
                ",\n\ttype='" + type + '\'' +
                ",\n\trace='" + race + '\'' +
                ",\n\tdesc='" + desc + '\'' +
                ",\n\tarchetype='" + archetype + '\'' +
                "\n}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }
}
