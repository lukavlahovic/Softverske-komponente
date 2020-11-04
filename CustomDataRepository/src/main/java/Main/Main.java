package Main;

import CusstomDataRepositoryParser.CustomParser;
import CustomDataRepository.CustomDataRepository;
import model.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        CustomParser parser = new CustomParser();
        CustomDataRepository repository = new CustomDataRepository();
        File f = new File("D:\\json_baza\\custom.txt");

        Entity entity = new Entity("student",0);
        Entity entity1 = new Entity("univerzitet",1);
        Entity entity2 = new Entity("student",2);
        Entity entity21 = new Entity("univerzitet",3);
        entity1.getAttributes().put("name","union");
        entity1.getAttributes().put("founded","2000");
        entity.getAttributes().put("firstname","Luka");
        entity.getAttributes().put("age","22");
        entity.getAttributes().put("lastname","Vlahovic");
        entity.getAttributes().put("univerzitet",entity1);
        entity21.getAttributes().put("name","singi");
        entity21.getAttributes().put("founded","2005");
        entity2.getAttributes().put("firstname","Andrija");
        entity2.getAttributes().put("lastname","Petrovic");
        entity2.getAttributes().put("age","23");
        entity2.getAttributes().put("univerzitet",entity21);
        ArrayList<Entity> lista = new ArrayList<>();
        lista.add(entity);
        lista.add(entity1);
        parser.writer(f,lista);













//        Entity entity = new Entity("student",0);
//        Entity entity1 = new Entity("univerzitet",1);
//        ArrayList<Entity> lista = new ArrayList<>();
//        lista.add(entity);
//        lista.add(entity1);
//        parser.writer(f,lista);
//        ArrayList<Entity> l = (ArrayList)parser.reader(f);
//        for(Entity e: l)
//        {
//            System.out.println(e.getName() + e.getId() + e.getAttributes());
//        }
        //entity.getAttributes().put("name","Luka");
        //entity.getAttributes().put("lastname","jelic");

//        HashMap<Object,Object> updatedMap = new HashMap<>();
//        updatedMap.put("name","Andrija");
//        repository.update("/home/luka/Desktop/custom.txt", 0,updatedMap);
        //repository.save("D:\\custom.txt",entity);
        //repository.delete("/home/luka/Desktop/custom.txt",0);
//        System.out.println(entity1.getName() + entity1.getId() + entity1.getAttributes());
//        ArrayList<Entity> l = (ArrayList)parser.reader(f);
//        for(Entity e: l)
//        {
//            System.out.println(e.getName() + e.getId() + e.getAttributes());
//        }
    }
}
