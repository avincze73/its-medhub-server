/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.constraint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;
import masterdatamodule.dto.GoverningAuthorityDTO;
import masterdatamodule.dto.RegionDTO;

/**
 *
 * @author vincze.attila
 */
public class HospitalConstraint extends base.BaseConstraint {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("officialName".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("officialNameMustBeFilled"), evt);
            }
        } else if ("shortName".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("shortNameMustBeFilled"), evt);
            }
        } else if ("address".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("addressMustBeFilled"), evt);
            }
        } else if ("region".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals( ( (RegionDTO) evt.getNewValue()).getName() )) {
                throw new PropertyVetoException(bundle.getString("regionMustBeFilled"), evt);
            }
        } else if ("govBody".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("govBodyMustBeFilled"), evt);
            }
        } else if ("govAuthority".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals( ( (GoverningAuthorityDTO) evt.getNewValue()).getName() )) {
                throw new PropertyVetoException(bundle.getString("govAuthorityMustBeFilled"), evt);
            }
        } else if ("qualityRequirements".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("qualityRequirementsMustBeFilled"), evt);
            }
        }
    }

    public HospitalConstraint(ResourceBundle bundle) {
        super(bundle);
    }

    
}
