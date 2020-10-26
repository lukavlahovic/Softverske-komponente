package model;

import java.util.ArrayList;

public class Entity {

    private String name;
    private ArrayList<Attribute> attributes;

    public Entity(String name)
    {
        this.name = name;
        attributes = new ArrayList<Attribute>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return name;
    }
}
