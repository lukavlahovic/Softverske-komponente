package controller;

import main.MainFrame;
import model.Entity;
import model.TableModel;
import view.TablePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AddNewEntityListener implements ActionListener {

    private TableModel tableModel;
    private TablePanel tablePanel;

    public AddNewEntityListener(TableModel tableModel, TablePanel tablePanel){
        this.tableModel = tableModel;
        this.tablePanel = tablePanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Properties p = MainFrame.getInstance().getProperty();
        int id;
        if(p.getProperty("autoGenerateId").equals("false"))
            id = Integer.parseInt(tablePanel.getIdField().getText());
        else
            id = Integer.parseInt(p.getProperty("lastAvailableId"))+1;
        String name = tablePanel.getNameField().getText();
        Entity entity = new Entity(name,id);
        //entry je po ",", a kljuc-vrednost po =
        //firstname=Luka, age=22, lastname=Vlahovic ovo ide u levi textarea(attributesTextField
        //glavni entitet
        String[] values = tablePanel.getAttributesField().getText().split(",");
        HashMap<Object,Object> attributes = new HashMap<Object, Object>();
        for(String keysValues : values){
            String[] keyValue = keysValues.split("=");
            attributes.put(keyValue[0],keyValue[1]);
        }
        System.out.println("id="+entity.getId()+" name="+entity.getName());
        System.out.println(attributes);
        //ugnjezdeni entitet
        if(!tablePanel.getUgnjezdeniNameField().getText().equals("")) {
            int ugnjezdeniId;
            if (p.getProperty("autoGenerateId").equals("false"))
                ugnjezdeniId = Integer.parseInt(tablePanel.getUgnjezdeniIdField().getText());
            else
                ugnjezdeniId = Integer.parseInt(p.getProperty("lastAvailableId"))+2;
            String ugnjezdeniName = tablePanel.getUgnjezdeniNameField().getText();
            Entity ugnjezdeniEntity = new Entity(ugnjezdeniName, ugnjezdeniId);
            String[] values1 = tablePanel.getUgnjezdeniAttributesField().getText().split(",");
            HashMap<Object, Object> ugnjezdeniAttributes = new HashMap<Object, Object>();
            for (String keysValues : values1) {
                String[] keyValue = keysValues.split("=");
                ugnjezdeniAttributes.put(keyValue[0], keyValue[1]);
            }
            ugnjezdeniEntity.setAttributes(ugnjezdeniAttributes);
            System.out.println("id="+ugnjezdeniEntity.getId()+" name="+ugnjezdeniEntity.getName());
            //dodavanje ugnjezdenog u atribute
            attributes.put(ugnjezdeniName, ugnjezdeniEntity);
        }
        entity.setAttributes(attributes);

        String pathToDirectory = p.getProperty("pathToDirectory");
        String pathToConfig = MainFrame.getInstance().getPathToConfig();
        System.out.println(attributes);
        if(MainFrame.getInstance().getDataRepository().save(pathToDirectory,entity,pathToConfig))
            tableModel.addRow(new Object[]{entity.getId(),entity.getName(),entity.getAttributes()});
        else
            System.out.println("ID nije jedinstven");
    }
}
