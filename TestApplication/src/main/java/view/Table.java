package view;

import model.TableModel;

import javax.swing.*;

public class Table extends JTable {

    public Table(){
        super(new TableModel());
    }

}
