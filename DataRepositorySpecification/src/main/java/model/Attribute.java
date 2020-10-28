package model;

public class Attribute {

    private String name;
    private Entity entity;
    private String value;

    public Attribute(){
        super();
    }

    public Attribute(String name,String value)
    {
        this.name = name;
        this.value = value;
    }

    public Attribute(Entity entity)
    {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
