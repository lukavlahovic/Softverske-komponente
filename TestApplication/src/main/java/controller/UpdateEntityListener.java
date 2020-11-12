package controller;


import main.MainFrame;
import model.TableModel;
import view.Table;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UpdateEntityListener implements ActionListener {

    private Table table;
    private TableModel tableModel;
    private JDialog dialog;

    public UpdateEntityListener(TableModel tableModel,Table table){
        this.table = table;
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        Properties p = MainFrame.getInstance().getProperty();
//        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(),0).toString());
//        HashMap<Object,Object> updatedValue = (HashMap)table.getValueAt(table.getSelectedRow(),2);
//        String pathToDirectory = p.getProperty("pathToDirectory");
//        dialog = new JDialog();
//        dialog.setSize(150,300);
//        dialog.setVisible(true);
//        JPanel panel = new JPanel(new BorderLayout());
//        JTextField textField = new JTextField(50);
//        panel.add(textField,BorderLayout.CENTER);
//        String[] izmena = textField.getText().split("=");
//        System.out.println(izmena[0]+izmena[1]);
//        if(updatedValue.containsKey(izmena[0])){
//            updatedValue.put(izmena[0],izmena[1]);
//        }
//        else {
//            for (Map.Entry entry : updatedValue.entrySet()) {
//                if(entry instanceof HashMap){
//                    HashMap map = (HashMap)((HashMap) entry).get("attributes");
//                    map.put(izmena[0],izmena[1]);
//                }
//            }
//        }
//        System.out.println(updatedValue);
        //MainFrame.getInstance().getDataRepository().update(pathToDirectory,id,updatedValue);
    }
}
