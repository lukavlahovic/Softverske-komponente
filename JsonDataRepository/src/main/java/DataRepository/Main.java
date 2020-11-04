package DataRepository;

import model.Entity;
import model.SearchParameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JsonDataRepository jdr = new JsonDataRepository();
        String putanja = "D:\\json_baza\\test.json";
//        Entity entity = new Entity("student",0);
//        Entity entity1 = new Entity("univerzitet",1);
//        Entity entity2 = new Entity("student",2);
//        Entity entity21 = new Entity("univerzitet",3);
//        entity1.getAttributes().put("name","union");
//        entity1.getAttributes().put("founded","2000");
//        entity.getAttributes().put("firstname","Luka");
//        entity.getAttributes().put("age","22");
//        entity.getAttributes().put("lastname","Vlahovic");
//        entity.getAttributes().put("univerzitet",entity1);
//        entity21.getAttributes().put("name","singi");
//        entity21.getAttributes().put("founded","2005");
//        entity2.getAttributes().put("firstname","Andrija");
//        entity2.getAttributes().put("lastname","Petrovic");
//        entity2.getAttributes().put("age","23");
//        entity2.getAttributes().put("univerzitet",entity21);
//        jdr.save(putanja,entity);
//        jdr.save(putanja,entity2);
//        Entity entity = new Entity("profesor",4);
//        Entity entity1 = new Entity("predmet",5);
//        entity.getAttributes().put("firstname","Branko");
//        entity.getAttributes().put("lastname","Perisic");
//        entity1.getAttributes().put("naziv","Softversko Inzinjerstvo");
//        entity1.getAttributes().put("espb",8);
//        entity.getAttributes().put("predmet",entity1);
//        jdr.save(putanja,entity);
//        jdr.save(putanja,entity1);

        SearchParameters sp = new SearchParameters();
        sp.setAndOr("and");
        sp.setNot("entityname!student");
        sp.setGreaterThan("id:0");
        ArrayList<Entity> list =  jdr.find(sp);
        for (Entity e: list)
        {
            System.out.println(e);
        }
    }
}
