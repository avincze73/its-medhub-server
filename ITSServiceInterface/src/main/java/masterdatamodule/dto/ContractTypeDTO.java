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
public class ContractTypeDTO extends BaseDTO {

   private String englishName;
    private String hungarianName;

    public ContractTypeDTO() {
        super();
    }

    public ContractTypeDTO(String englishName, String hungarianName) {
        super();
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    public ContractTypeDTO(long id, String englishName, String hungarianName) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ContractTypeDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        String oldValue = this.englishName;
        this.englishName = englishName;
        propertyChangeSupport.firePropertyChange("englishName", oldValue, this.englishName);
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        String oldValue = this.hungarianName;
        this.hungarianName = hungarianName;
        propertyChangeSupport.firePropertyChange("hungarianName", oldValue, this.hungarianName);
    }
}
