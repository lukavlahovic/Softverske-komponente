package main;
import model.Entity;
import model.TableModel;
import org.reflections.Reflections;
import repository.DataRepository;

import java.util.ArrayList;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        //Instance reflection class
        Reflections reflections = new Reflections(".*");
        //Find all sub types for given type
        Set<Class<? extends DataRepository>> subTypes =
                reflections.getSubTypesOf(DataRepository.class);
        //Get first from set
        /*Object implementation1[] =  subTypes.toArray();
        for(Object o:implementation1){
            System.out.println(o);
        }*/

        Class<? extends DataRepository> implementation = (Class<? extends DataRepository>) subTypes.toArray()[0];
        DataRepository dataRepository = implementation.newInstance();
        //Call method using specification

        MainFrame.getInstance();

        MainFrame.getInstance().setDataRepository(dataRepository);
        MainFrame.getInstance().setPathToConfig("D:\\app.properties");
        MainFrame.getInstance().load();
        //tableModel.loadRepository();
    }
}
