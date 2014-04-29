/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pm;

import base.BaseDTO;
import masterdatamodule.dto.CaseFlagDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseFlagPM extends BaseDTO {

    private CaseFlagDTO caseFlag;
    private boolean checked;

    public CaseFlagPM(CaseFlagDTO caseFlag, boolean checked) {
        super();
        this.caseFlag = caseFlag;
        this.checked = checked;
    }

    public CaseFlagDTO getCaseFlag() {
        return caseFlag;
    }

    public void setCaseFlag(CaseFlagDTO caseFlag) {
        CaseFlagDTO oldValue = this.caseFlag;
        this.caseFlag = caseFlag;
        propertyChangeSupport.firePropertyChange("caseFlag", oldValue, this.caseFlag);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        boolean oldValue = this.checked;
        this.checked = checked;
        propertyChangeSupport.firePropertyChange("checked", oldValue, this.checked);
    }
}
