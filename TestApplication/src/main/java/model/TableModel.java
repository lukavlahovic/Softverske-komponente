package model;

import main.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class TableModel extends DefaultTableModel {


 /*   private Schedule schedule;
    private EventFactory eventFactory;
*/
    public TableModel() {
        super(new String[]{"ID", "Name", "Attributes"}, 0);
        //loadRepository();
        //this.schedule = new Schedule();
        //this.eventFactory = new EventFactory();
    }

    public void loadRepository(){
        Properties p = MainFrame.getInstance().getProperty();
        ArrayList<Entity> entityArrayList = MainFrame.getInstance().getDataRepository().loadRepository(p.getProperty("pathToDirectory"));
        for(Entity entity : entityArrayList){
            //Object[] objects = new Object[]{entity.getId(),entity.getName(),entity.getAttributes()};
//            for(Object o:objects)
//                System.out.println(o);
            this.addRow(new Object[]{entity.getId(),entity.getName(),entity.getAttributes()});
        }
    }
/*
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
*/
    @Override
    public void addRow(Object[] arg0) {
        for(Object o : arg0){
            System.out.println(o);
        }
        super.addRow(arg0);

        //staviti u save akciju
//        Entity entity = new Entity();
//        entity.setId(Integer.parseInt((String)arg0[0]));
//        entity.setName((String)arg0[1]);
//        entity.setAttributes((HashMap)arg0[2]);
//
//        Properties p = MainFrame.getInstance().getProperty();
//
//        String pathToProperty = MainFrame.getInstance().getPathToConfig();
//
//        String pathToDirectory = p.getProperty("pathToDirectory");
//
//        MainFrame.getInstance().getDataRepository().save(pathToDirectory,entity,pathToProperty);
    }

    @Override
    public void removeRow(int arg0) {
        super.removeRow(arg0);

    }

}
