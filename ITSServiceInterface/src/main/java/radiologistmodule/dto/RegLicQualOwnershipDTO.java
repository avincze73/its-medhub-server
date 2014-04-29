/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.RegLicQualDTO;

/**
 *
 * @author vincze.attila
 */
public class RegLicQualOwnershipDTO extends BaseDTO {

    private Date validBegin;
    private Date validEnd;
    private String certificateNumber;
    private RegLicQualDTO regLicQual;
    private boolean active;
    private TDSRadiologistDTO tdsRadiologist;

    public RegLicQualOwnershipDTO(long id) {
        super(id);
    }

    public RegLicQualOwnershipDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegLicQualOwnershipDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        String oldValue = this.certificateNumber;
        this.certificateNumber = certificateNumber;
        propertyChangeSupport.firePropertyChange("certificateNumber", oldValue, this.certificateNumber);
    }

    public RegLicQualDTO getRegLicQual() {
        return regLicQual;
    }

    public void setRegLicQual(RegLicQualDTO regLicQual) {
        RegLicQualDTO oldValue = this.regLicQual;
        this.regLicQual = regLicQual;
        propertyChangeSupport.firePropertyChange("regLicQual", oldValue, this.regLicQual);
    }

    public Date getValidBegin() {
        return validBegin;
    }

    public void setValidBegin(Date validBegin) {
        Date oldValue = this.validBegin;
        this.validBegin = validBegin;
        propertyChangeSupport.firePropertyChange("validBegin", oldValue, this.validBegin);
    }

    public Date getValidEnd() {
        return validEnd;
    }

    public void setValidEnd(Date validEnd) {
        Date oldValue = this.validEnd;
        this.validEnd = validEnd;
        propertyChangeSupport.firePropertyChange("validEnd", oldValue, this.validEnd);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }
}
