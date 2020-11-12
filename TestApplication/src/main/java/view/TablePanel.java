package view;

import controller.AddNewEntityListener;
import controller.DeleteEntityListener;
import controller.SearchListener;
import controller.UpdateEntityListener;
import model.TableModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TablePanel extends JPanel {


    private Table table;
    private JScrollPane scrollPane;
    private JPanel editingPanel;

    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton importButton;
    private JButton exportButton;
    private JButton queryButton;

    private JTextArea query;

    private JTextField idField;
    private JTextField nameField;
    private JTextArea attributesField;

    private JTextField ugnjezdeniIdField;
    private JTextField ugnjezdeniNameField;
    private JTextArea ugnjezdeniAttributesField;

    public TablePanel() {
        super();
        this.setSize(400, 400);
        table = new Table();
        //((TableModel)table.getModel()).loadRepository();
        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.NORTH);

        this._initEditPanel();

    }

    private void _initEditPanel() {
        this.scrollPane = new JScrollPane();
        this.editingPanel = new JPanel();
        this.editingPanel.setLayout(new GridLayout(5,5));

        this.addButton = new JButton("ADD");
        this.deleteButton = new JButton("DELETE");
        this.updateButton = new JButton("UPDATE");
        this.importButton = new JButton("IMPORT");
        this.exportButton = new JButton("EXPORT");
        this.queryButton = new JButton("SEARCH");
/*
        EventService eventService = new EventServiceImpl();
        ScheduleImportExport<File> scheduleImportExport = new SheduleImportExportJsonImplementation();
        ScheduleService scheduleService = new ScheduleServiceImpl(scheduleImportExport, eventService);

        addButton.addActionListener(new AddNewEventListener((ScheduleTableModel) this.scheduleTable.getModel(), this));
        deleteButton.addActionListener(new DeleteEvenListener((ScheduleTableModel) this.scheduleTable.getModel(), this));
        exportButton.addActionListener(new ExportDataActionListener((ScheduleTableModel) scheduleTable.getModel(), scheduleService));
*/
        addButton.addActionListener(new AddNewEntityListener((TableModel)table.getModel(),this));
        queryButton.addActionListener(new SearchListener((TableModel)table.getModel(),this));
        deleteButton.addActionListener(new DeleteEntityListener((TableModel)table.getModel(),table));
        updateButton.addActionListener(new UpdateEntityListener((TableModel)table.getModel(),table));


        JPanel searchPanel = new JPanel();
        JLabel queryLabel = new JLabel("Pretraga:");
        this.query = new JTextArea(5,50);
        searchPanel.add(queryLabel);
        searchPanel.add(query);
        searchPanel.add(queryButton);
        // Add panel for buttons
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(updateButton);
        buttonsPanel.add(importButton);
        buttonsPanel.add(exportButton);

        JPanel fieldsPanel = new JPanel();
        this.idField = new JTextField("ID", 20);
        this.nameField = new JTextField("Name", 20);
        this.attributesField = new JTextArea(10, 50);
        fieldsPanel.add(idField);
        fieldsPanel.add(nameField);
        fieldsPanel.add(attributesField);

        JPanel fieldsPanel1 = new JPanel();
        this.ugnjezdeniIdField = new JTextField("Ugnjezdeni ID", 20);
        this.ugnjezdeniNameField = new JTextField("Ugnjezdeni Name", 20);
        this.ugnjezdeniAttributesField = new JTextArea(10,50);
        fieldsPanel1.add(ugnjezdeniIdField);
        fieldsPanel1.add(ugnjezdeniNameField);
        fieldsPanel1.add(ugnjezdeniAttributesField);

        //obojili ivice textarea u crna
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        attributesField.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        ugnjezdeniAttributesField.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        this.editingPanel.add(buttonsPanel, BorderLayout.NORTH);
        this.editingPanel.add(searchPanel, BorderLayout.NORTH);
        this.editingPanel.add(fieldsPanel, BorderLayout.CENTER);
        this.editingPanel.add(fieldsPanel1, BorderLayout.SOUTH);
        //this.scrollPane.add(editingPanel);
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
        return updateButton;
    }

    public void setShowDetailsButton(JButton showDetailsButton) {
        this.updateButton = showDetailsButton;
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

    public JTextArea getAttributesField() {
        return attributesField;
    }

    public void setAttributesField(JTextArea attributesField) {
        this.attributesField = attributesField;
    }

    public JTextArea getUgnjezdeniAttributesField() {
        return ugnjezdeniAttributesField;
    }

    public void setUgnjezdeniAttributesField(JTextArea ugnjezdeniAttributesField) {
        this.ugnjezdeniAttributesField = ugnjezdeniAttributesField;
    }

    public JTextField getUgnjezdeniIdField() {
        return ugnjezdeniIdField;
    }

    public void setUgnjezdeniIdField(JTextField ugnjezdeniIdField) {
        this.ugnjezdeniIdField = ugnjezdeniIdField;
    }

    public JTextField getUgnjezdeniNameField() {
        return ugnjezdeniNameField;
    }

    public void setUgnjezdeniNameField(JTextField ugnjezdeniNameField) {
        this.ugnjezdeniNameField = ugnjezdeniNameField;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JButton getQueryButton() {
        return queryButton;
    }

    public void setQueryButton(JButton queryButton) {
        this.queryButton = queryButton;
    }

    public JTextArea getQuery() {
        return query;
    }

    public void setQuery(JTextArea query) {
        this.query = query;
    }
}
