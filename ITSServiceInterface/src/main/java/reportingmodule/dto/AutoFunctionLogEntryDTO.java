/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class AutoFunctionLogEntryDTO extends BaseDTO {

    private Date added;
    private AutoFunctionTypeDTO autoFunction;

    public AutoFunctionLogEntryDTO(long id) {
        this(id, null, null);
    }

    public AutoFunctionLogEntryDTO() {
        this(0, null, null);
    }

    public AutoFunctionLogEntryDTO(long id, Date added, AutoFunctionTypeDTO autoFunction) {
        super(id);
        this.added = added;
        this.autoFunction = autoFunction;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AutoFunctionLogEntryDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public AutoFunctionTypeDTO getAutoFunction() {
        return autoFunction;
    }

    public void setAutoFunction(AutoFunctionTypeDTO autoFunction) {
        AutoFunctionTypeDTO oldValue = this.autoFunction;
        this.autoFunction = autoFunction;
        propertyChangeSupport.firePropertyChange("autoFunction", oldValue, this.autoFunction);
    }
}
