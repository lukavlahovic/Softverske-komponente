package main;

import model.Entity;
import model.TableModel;
import repository.DataRepository;
import view.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private DataRepository dataRepository;
    private String pathToConfig;
    private TablePanel tablePanel;

    public MainFrame() {
        this.setTitle("Test Gui Component");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());
        tablePanel = new TablePanel();
        this.add(tablePanel, BorderLayout.NORTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void load(){
        TableModel tableModel = (TableModel)this.tablePanel.getTable().getModel();
        tableModel.loadRepository();
    }

//    private static class InstanceHolder {
//        private static MainFrame instance = new MainFrame();
//    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public static MainFrame getInstance() {

        if(instance == null) {
            instance = new MainFrame();
            System.out.println("null je");
        }
        return instance;

    }

    public void setPathToConfig(String pathToConfig) {
        this.pathToConfig = pathToConfig;
    }

    public String getPathToConfig() {
        return pathToConfig;
    }

    public Properties getProperty(){
        Properties properties = new Properties();
        FileReader reader= null;
        try {
            System.out.println(getPathToConfig());
            reader = new FileReader(getPathToConfig());
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
