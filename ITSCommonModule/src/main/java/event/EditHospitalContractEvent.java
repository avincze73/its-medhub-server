/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.EventObject;

/**
 *
 * @author vincze.attila
 */
public class EditHospitalContractEvent extends EventObject {

    private long hospitalId;
    private String hospitalOfficialName;

    public EditHospitalContractEvent(Object source, long hospitalId, String hospitalOfficialName) {
        super(source);
        this.hospitalId = hospitalId;
        this.hospitalOfficialName = hospitalOfficialName;
    }

    public EditHospitalContractEvent(Object source) {
        super(source);
    }

    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalOfficialName() {
        return hospitalOfficialName;
    }

    public void setHospitalOfficialName(String hospitalOfficialName) {
        this.hospitalOfficialName = hospitalOfficialName;
    }
}
