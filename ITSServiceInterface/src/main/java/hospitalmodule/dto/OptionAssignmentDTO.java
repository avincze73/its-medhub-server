/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.HospContrOptionDTO;

/**
 *
 * @author vincze.attila
 */
public class OptionAssignmentDTO extends BaseDTO {

    private Date validStart;
    private Date inactivation;
    private HospContrOptionDTO option;
    private HospitalContractDTO hospitalContract;

    public OptionAssignmentDTO(long id) {
        super(id);
    }

    public OptionAssignmentDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OptionAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getInactivation() {
        return inactivation;
    }

    public void setInactivation(Date inactivation) {
        Date oldValue = this.inactivation;
        this.inactivation = inactivation;
        propertyChangeSupport.firePropertyChange("inactivation", oldValue, this.inactivation);
    }

    public HospContrOptionDTO getOption() {
        return option;
    }

    public void setOption(HospContrOptionDTO option) {
        HospContrOptionDTO oldValue = this.option;
        this.option = option;
        propertyChangeSupport.firePropertyChange("option", oldValue, this.option);
    }

    public Date getValidStart() {
        return validStart;
    }

    public void setValidStart(Date validStart) {
        Date oldValue = this.validStart;
        this.validStart = validStart;
        propertyChangeSupport.firePropertyChange("validStart", oldValue, this.validStart);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }
}
