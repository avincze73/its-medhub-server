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
public class BandInfoDTO extends BaseDTO {

    private int bandNumber;
    private double workTimeMinute;
    private double priceForNormalWork;
    private double priceForPayAsYouGoWork;
    private double priceForExtraWork;
    private WorkBandTableDTO workBandTable;

    public BandInfoDTO(long id) {
        super(id);
    }

    public BandInfoDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BandInfoDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public int getBandNumber() {
        return bandNumber;
    }

    public void setBandNumber(int bandNumber) {
        int oldValue = this.bandNumber;
        this.bandNumber = bandNumber;
        propertyChangeSupport.firePropertyChange("bandNumber", oldValue, this.bandNumber);
    }

    public double getPriceForExtraWork() {
        return priceForExtraWork;
    }

    public void setPriceForExtraWork(double priceForExtraWork) {
        double oldValue = this.priceForExtraWork;
        this.priceForExtraWork = priceForExtraWork;
        propertyChangeSupport.firePropertyChange("priceForExtraWork", oldValue, this.priceForExtraWork);
    }

    public double getPriceForNormalWork() {
        return priceForNormalWork;
    }

    public void setPriceForNormalWork(double priceForNormalWork) {
        double oldValue = this.priceForNormalWork;
        this.priceForNormalWork = priceForNormalWork;
        propertyChangeSupport.firePropertyChange("priceForNormalWork", oldValue, this.priceForNormalWork);
    }

    public double getPriceForPayAsYouGoWork() {
        return priceForPayAsYouGoWork;
    }

    public void setPriceForPayAsYouGoWork(double priceForPayAsYouGoWork) {
        double oldValue = this.priceForPayAsYouGoWork;
        this.priceForPayAsYouGoWork = priceForPayAsYouGoWork;
        propertyChangeSupport.firePropertyChange("priceForPayAsYouGoWork", oldValue, this.priceForPayAsYouGoWork);
    }

    public WorkBandTableDTO getWorkBandTable() {
        return workBandTable;
    }

    public void setWorkBandTable(WorkBandTableDTO workBandTable) {
        WorkBandTableDTO oldValue = this.workBandTable;
        this.workBandTable = workBandTable;
        propertyChangeSupport.firePropertyChange("workBandTable", oldValue, this.workBandTable);
    }

    public double getWorkTimeMinute() {
        return workTimeMinute;
    }

    public void setWorkTimeMinute(double workTimeMinute) {
        double oldValue = this.workTimeMinute;
        this.workTimeMinute = workTimeMinute;
        propertyChangeSupport.firePropertyChange("workTimeMinute", oldValue, this.workTimeMinute);
    }
}
