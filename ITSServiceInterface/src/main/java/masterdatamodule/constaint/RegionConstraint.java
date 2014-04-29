/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.constaint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;
import masterdatamodule.dto.CountryDTO;

/**
 *
 * @author vincze.attila
 */
public class RegionConstraint extends base.BaseConstraint {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("name".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("nameMustBeFilled"), evt);
            }
        } else if ("country".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals( ( (CountryDTO) evt.getNewValue()).getHungarianName() )) {
                throw new PropertyVetoException(bundle.getString("countryMustBeFilled"), evt);
            }
        }
    }

    public RegionConstraint(ResourceBundle bundle) {
        super(bundle);
    }

    
}
