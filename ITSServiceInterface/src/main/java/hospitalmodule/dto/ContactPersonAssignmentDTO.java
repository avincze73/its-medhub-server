/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class ContactPersonAssignmentDTO extends BaseDTO {

    private HospitalContractDTO hospitalContract;
    private ContactPersonDTO contactPerson;
    private String contactPosition;
    private String contactType;
    private Date started;
    private Date finished;
    private Date inactivation;

    public ContactPersonAssignmentDTO(long id) {
        super(id);
    }

    public ContactPersonAssignmentDTO() {
        super();
    }

    public ContactPersonAssignmentDTO(HospitalContractDTO hospitalContract,
            ContactPersonDTO contactPerson, String contactPosition,
            String contactType, Date started, Date finished, Date inactivation) {
        super();
        this.hospitalContract = hospitalContract;
        this.contactPerson = contactPerson;
        this.contactPosition = contactPosition;
        this.contactType = contactType;
        this.started = started;
        this.finished = finished;
        this.inactivation = inactivation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ContactPersonAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }

    public ContactPersonDTO getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPersonDTO contactPerson) {
        ContactPersonDTO oldValue = this.contactPerson;
        this.contactPerson = contactPerson;
        propertyChangeSupport.firePropertyChange("contactPerson", oldValue, this.contactPerson);
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        String oldValue = this.contactPosition;
        this.contactPosition = contactPosition;
        propertyChangeSupport.firePropertyChange("contactPosition", oldValue, this.contactPosition);
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        String oldValue = this.contactType;
        this.contactType = contactType;
        propertyChangeSupport.firePropertyChange("contactType", oldValue, this.contactType);
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        Date oldValue = this.finished;
        this.finished = finished;
        propertyChangeSupport.firePropertyChange("finished", oldValue, this.finished);
    }

    public Date getInactivation() {
        return inactivation;
    }

    public void setInactivation(Date inactivation) {
        Date oldValue = this.inactivation;
        this.inactivation = inactivation;
        propertyChangeSupport.firePropertyChange("inactivation", oldValue, this.inactivation);
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
