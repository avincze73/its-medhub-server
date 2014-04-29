/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;
import java.beans.PropertyVetoException;

/**
 *
 * @author vincze.attila
 */
public class CaseFlagDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;

    public CaseFlagDTO() {
        super();
    }

    public CaseFlagDTO(String englishName, String hungarianName) {
        super();
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    public CaseFlagDTO(long id, String englishName, String hungarianName) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseFlagDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) throws PropertyVetoException {
        String oldValue = this.englishName;
        vetoableChangeSupport.fireVetoableChange("englishName", oldValue, englishName);
        this.englishName = englishName;
        propertyChangeSupport.firePropertyChange("englishName", oldValue, this.englishName);
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) throws PropertyVetoException {
        String oldValue = this.hungarianName;
        vetoableChangeSupport.fireVetoableChange("hungarianName", oldValue, hungarianName);
        this.hungarianName = hungarianName;
        propertyChangeSupport.firePropertyChange("hungarianName", oldValue, this.hungarianName);
    }
}
