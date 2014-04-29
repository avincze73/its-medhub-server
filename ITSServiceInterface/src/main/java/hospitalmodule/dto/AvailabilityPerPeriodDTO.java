/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class AvailabilityPerPeriodDTO extends AvailabilityPerWeekDTO {

    private Date started;
    private Date ended;
    private HospitalContractDTO hospitalContract;

    public AvailabilityPerPeriodDTO() {
        super();
    }

    public AvailabilityPerPeriodDTO(long id) {
        super(id);
    }

    public AvailabilityPerPeriodDTO(long id, double sunday, double monday,
            double tuesday, double wednesday, double thursday, double friday, double saturday) {
        super(id, sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    public AvailabilityPerPeriodDTO(double sunday, double monday, double tuesday,
            double wednesday, double thursday, double friday, double saturday) {
        super(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AvailabilityPerPeriodDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        Date oldValue = this.ended;
        this.ended = ended;
        propertyChangeSupport.firePropertyChange("ended", oldValue, this.ended);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }
}
