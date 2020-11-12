package controller;

import main.MainFrame;
import model.TableModel;
import view.Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class DeleteEntityListener implements ActionListener {

    private Table table;
    private TableModel tableModel;

    public DeleteEntityListener(TableModel tableModel, Table table){
        this.table = table;
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Properties p = MainFrame.getInstance().getProperty();
        int id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(),0).toString());
        String pathToDirectory = p.getProperty("pathToDirectory");
        MainFrame.getInstance().getDataRepository().delete(pathToDirectory,id);
        tableModel.removeRow(table.getSelectedRow());
    }
}
