package Main;

import DataRepository.YamlDataRepository;
import model.Entity;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        YamlDataRepository yaml = new YamlDataRepository();
        String putanja = "D:\\yaml.yml";
        Entity entity = new Entity("student",0);
        Entity entity1 = new Entity("univerzitet",1);
        yaml.save(putanja,entity);
        yaml.save(putanja,entity1);
        HashMap<Object,Object> updatedMap = new HashMap<>();
        updatedMap.put("firstname","Andrija");
        //entity.getAttributes().put("firstname","Luka");
        //entity.getAttributes().put("lastname","Vlahovic");
        //yaml.save(putanja,entity);
        //entity.setAttributes(newAttributes);
        yaml.update(putanja, 0,updatedMap);

    }

}
