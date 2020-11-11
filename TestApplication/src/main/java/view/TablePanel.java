package view;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {


    private Table table;
    private JPanel editingPanel;

    private JButton addButton;
    private JButton deleteButton;
    private JButton showDetailsButton;
    private JButton importButton;
    private JButton exportButton;

    private JTextField idField;
    private JTextField nameField;
    private JTextField attributesField;


    public TablePanel() {
        super();
        this.setSize(400, 400);

        table = new Table();
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.NORTH);

        this._initEditPanel();

    }

    private void _initEditPanel() {
        this.editingPanel = new JPanel();
        this.editingPanel.setLayout(new BorderLayout());

        this.addButton = new JButton("ADD");
        this.deleteButton = new JButton("DELETE");
        this.showDetailsButton = new JButton("SHOW DETAILS");
        this.importButton = new JButton("IMPORT");
        this.exportButton = new JButton("EXPORT");
/*
        EventService eventService = new EventServiceImpl();
        ScheduleImportExport<File> scheduleImportExport = new SheduleImportExportJsonImplementation();
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleImportExport, eventService);

        addButton.addActionListener(new AddNewEventListener((ScheduleTableModel) this.scheduleTable.getModel(), this));
        deleteButton.addActionListener(new DeleteEvenListener((ScheduleTableModel) this.scheduleTable.getModel(), this));
        importButton.addActionListener(new ImportDataActionListener((ScheduleTableModel) scheduleTable.getModel(), scheduleService));
        exportButton.addActionListener(new ExportDataActionListener((ScheduleTableModel) scheduleTable.getModel(), scheduleService));
*/
        // Add panel for buttons
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(showDetailsButton);
        buttonsPanel.add(importButton);
        buttonsPanel.add(exportButton);

        JPanel fieldsPanel = new JPanel();
        this.idField = new JTextField("ID", 20);
        this.nameField = new JTextField("Name", 20);
        this.attributesField = new JTextField("Attributes", 20);

        fieldsPanel.add(idField);
        fieldsPanel.add(nameField);
        fieldsPanel.add(attributesField);


        this.editingPanel.add(buttonsPanel, BorderLayout.CENTER);
        this.editingPanel.add(fieldsPanel, BorderLayout.SOUTH);
        this.add(editingPanel, BorderLayout.CENTER);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public JPanel getEditingPanel() {
        return editingPanel;
    }

    public void setEditingPanel(JPanel editingPanel) {
        this.editingPanel = editingPanel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getShowDetailsButton() {
        return showDetailsButton;
    }

    public void setShowDetailsButton(JButton showDetailsButton) {
        this.showDetailsButton = showDetailsButton;
    }

    public JButton getImportButton() {
        return importButton;
    }

    public void setImportButton(JButton importButton) {
        this.importButton = importButton;
    }

    public JButton getExportButton() {
        return exportButton;
    }

    public void setExportButton(JButton exportButton) {
        this.exportButton = exportButton;
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getAttributesField() {
        return attributesField;
    }

    public void setAttributesField(JTextField attributesField) {
        this.attributesField = attributesField;
    }
}
