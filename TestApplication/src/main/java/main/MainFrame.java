package main;

import repository.DataRepository;
import view.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataRepository dataRepository;

    private MainFrame() {
        this.setTitle("Test Gui Component");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        TablePanel tablePanel = new TablePanel();
        this.add(tablePanel, BorderLayout.NORTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class InstanceHolder {
        private static MainFrame instance = new MainFrame();
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }

    public void setDataRepository(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public static MainFrame getInstance() {
        return InstanceHolder.instance;
    }

}
