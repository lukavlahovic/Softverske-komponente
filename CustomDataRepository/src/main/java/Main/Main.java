package Main;

import CusstomDataRepositoryParser.CustomParser;
import CustomDataRepository.CustomDataRepository;
import model.Entity;
import model.SearchParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        CustomParser parser = new CustomParser();
        CustomDataRepository repository = new CustomDataRepository();
        File f = new File("D:\\custom.txt");

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
//
//        repository.save("D:\\custom.txt",entity);
//        repository.save("D:\\custom.txt",entity2);

        Entity entity = new Entity("profesor",4);
        Entity entity1 = new Entity("predmet",5);
        entity.getAttributes().put("firstname","");
        entity.getAttributes().put("lastname","Perisic");
        entity1.getAttributes().put("naziv","Softversko Inzinjerstvo");
        entity1.getAttributes().put("espb",8);
        entity.getAttributes().put("predmet",entity1);
        repository.save("D:\\custom.txt",entity);
        repository.save("D:\\custom.txt",entity1);

        SearchParameters sp = new SearchParameters();
        sp.setAndOr("and");
        sp.setNot("entityname!student");
        sp.setGreaterThan("id:0");
        ArrayList<Entity> list =  repository.find(sp);
        for (Entity e: list)
        {
            System.out.println(e);
        }


    }
}
