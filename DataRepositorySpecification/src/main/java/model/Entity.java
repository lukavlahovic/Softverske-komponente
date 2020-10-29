package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Entity {

    private String name;
    private int id;
    private HashMap<Object,Object> attributes;

    public Entity()
    {
        super();
    }
    public Entity(String name, int id)
    {
        this.name = name;
        this.id = id;
        attributes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Object, Object> getAttributes() {
        return attributes;
    }

    public int getId(){ return id; }

    public void setAttributes(HashMap attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
