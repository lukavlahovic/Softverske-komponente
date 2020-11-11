package model;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel {


 /*   private Schedule schedule;
    private EventFactory eventFactory;
*/
    public TableModel() {
        super(new String[]{"ID", "Name", "Attributes"}, 0);
        //this.schedule = new Schedule();
        //this.eventFactory = new EventFactory();
    }
/*
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public void addRow(Object[] arg0) {
        super.addRow(arg0);
        Event event = eventFactory.getEvent((arg0[arg0.length - 1]).toString());
        event.setTitle(arg0[0].toString());
        event.setStartActivity((LocalDateTime) arg0[2]);
        event.setEndActivity((LocalDateTime) arg0[3]);
        schedule.add(event);
    }

    @Override
    public void removeRow(int arg0) {
        super.removeRow(arg0);
        this.schedule.getEvents().remove(arg0);
    }
*/
}
