package Main;

import DataRepository.YamlDataRepository;
import model.Entity;
import model.SearchParameters;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        YamlDataRepository yaml = new YamlDataRepository();
        /*String putanja = "//home//luka//Desktop//yaml.yml";
        Entity entity = new Entity("student",0);
        Entity entity1 = new Entity("univerzitet",1);
        Entity entity2 = new Entity("student",2);
        Entity entity21 = new Entity("univerzitet",3);
        entity1.getAttributes().put("name","union");
        entity.getAttributes().put("firstname","Luka");
        entity.getAttributes().put("lastname","Vlahovic");
        entity.getAttributes().put("univerzitet",entity1);
        entity21.getAttributes().put("name","singi");
        entity2.getAttributes().put("firstname","Andrija");
        entity2.getAttributes().put("lastname","Petrovic");
        entity2.getAttributes().put("univerzitet",entity21);
        yaml.save(putanja,entity);
        yaml.save(putanja,entity2);
        //yaml.save(putanja,entity1);*/

        SearchParameters sp = new SearchParameters();
        //sp.setEntityName("student");
        sp.setEquals("univerzitet:name:union");
        ArrayList<Entity> list =  yaml.find(sp);
        for (Entity e: list)
        {
            System.out.println(e);
        }

        //HashMap<Object,Object> updatedMap = new HashMap<>();
        //updatedMap.put("firstname","Andrija");

        //yaml.save(putanja,entity);
        //entity.setAttributes(newAttributes);
        //yaml.update(putanja, 0,updatedMap);

    }

}
