/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.viewmodel;

import base.ViewModelBase;

/**
 *
 * @author vincze.attila
 */
public class DailyAvailabilityViewModel extends ViewModelBase {

    private String day;
    private double maxAvailability;
    private double normalAvailability;

    public DailyAvailabilityViewModel() {
        super(null);
        day = "12";
        maxAvailability = 1.1;
        normalAvailability = 2.2;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        String oldValue = this.day;
        this.day = day;
        propertyChangeSupport.firePropertyChange("day", oldValue, this.day);
    }

    public double getMaxAvailability() {
        return maxAvailability;
    }

    public void setMaxAvailability(double maxAvailability) {
        double oldValue = this.maxAvailability;
        this.maxAvailability = maxAvailability;
        propertyChangeSupport.firePropertyChange("maxAvailability", oldValue, this.maxAvailability);
    }

    public double getNormalAvailability() {
        return normalAvailability;
    }

    public void setNormalAvailability(double normalAvailability) {
        double oldValue = this.normalAvailability;
        this.normalAvailability = normalAvailability;
        propertyChangeSupport.firePropertyChange("normalAvailability", oldValue, this.normalAvailability);
    }
}
