/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class AvailabilityPerWeekDTO extends BaseDTO {

    private double sunday;
    private double monday;
    private double tuesday;
    private double wednesday;
    private double thursday;
    private double friday;
    private double saturday;

    public AvailabilityPerWeekDTO() {
        super();
    }

    public AvailabilityPerWeekDTO(long id) {
        super(id);
    }

    public AvailabilityPerWeekDTO(long id, double sunday, double monday, double tuesday,
            double wednesday, double thursday, double friday, double saturday) {
        super(id);
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public AvailabilityPerWeekDTO(double sunday, double monday, double tuesday,
            double wednesday, double thursday, double friday, double saturday) {
        super();
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AvailabilityPerWeekDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public double getFriday() {
        return friday;
    }

    public void setFriday(double friday) {
        double oldValue = this.friday;
        this.friday = friday;
        propertyChangeSupport.firePropertyChange("friday", oldValue, this.friday);
    }

    public double getMonday() {
        return monday;
    }

    public void setMonday(double monday) {
        double oldValue = this.monday;
        this.monday = monday;
        propertyChangeSupport.firePropertyChange("monday", oldValue, this.monday);

    }

    public double getSaturday() {
        return saturday;
    }

    public void setSaturday(double saturday) {
        double oldValue = this.saturday;
        this.saturday = saturday;
        propertyChangeSupport.firePropertyChange("saturday", oldValue, this.saturday);
    }

    public double getSunday() {
        return sunday;
    }

    public void setSunday(double sunday) {
        double oldValue = this.sunday;
        this.sunday = sunday;
        propertyChangeSupport.firePropertyChange("sunday", oldValue, this.sunday);
    }

    public double getThursday() {
        return thursday;
    }

    public void setThursday(double thursday) {
        double oldValue = this.thursday;
        this.thursday = thursday;
        propertyChangeSupport.firePropertyChange("thursday", oldValue, this.thursday);
    }

    public double getTuesday() {
        return tuesday;
    }

    public void setTuesday(double tuesday) {
        double oldValue = this.tuesday;
        this.tuesday = tuesday;
        propertyChangeSupport.firePropertyChange("tuesday", oldValue, this.tuesday);
    }

    public double getWednesday() {
        return wednesday;
    }

    public void setWednesday(double wednesday) {
        double oldValue = this.wednesday;
        this.wednesday = wednesday;
        propertyChangeSupport.firePropertyChange("wednesday", oldValue, this.wednesday);
    }
}
