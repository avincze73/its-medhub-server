/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.constaint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;
import masterdatamodule.dto.RegLicQualTypeDTO;
import masterdatamodule.dto.RegionDTO;

/**
 *
 * @author vincze.attila
 */
public class RegLicQualConstraint extends base.BaseConstraint {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("name".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("nameMustBeFilled"), evt);
            }
        } else if ("region".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals( ( (RegionDTO) evt.getNewValue()).getName() )) {
                throw new PropertyVetoException(bundle.getString("regionMustBeFilled"), evt);
            }
        } else if ("type".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals( ( (RegLicQualTypeDTO) evt.getNewValue()).getName() )) {
                throw new PropertyVetoException(bundle.getString("typeMustBeFilled"), evt);
            }
        }

    }

    public RegLicQualConstraint(ResourceBundle bundle) {
        super(bundle);
    }
}
