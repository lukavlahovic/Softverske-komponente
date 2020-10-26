package model;

public class Attribute {

    private String name;
    private Entity entity;

    public Attribute(String name)
    {
        this.name = name;
    }

    public Attribute(Entity entity)
    {
        this.entity = entity;
    }



}
