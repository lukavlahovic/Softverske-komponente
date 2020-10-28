package DataRepository;

import model.Attribute;
import model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JsonDataRepository jdr = new JsonDataRepository();
        String putanja = "D:\\json_baza\\test.json";
        Entity entity = new Entity("student",0);
        Entity entity1 = new Entity("univerzitet",1);
        HashMap<Object,Object> updatedMap = new HashMap<>();
        updatedMap.put("univerzitet",entity1);
//        entity.getAttributes().put("firstname","Luka");
//        entity.getAttributes().put("lastname","Vlahovic");
//        jdr.save(putanja,entity);
//        jdr.delete(putanja,1);
//        entity.setAttributes(newAttributes);
        jdr.update(putanja, 0,updatedMap);
    }
}
