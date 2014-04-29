/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.constaint;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.util.ResourceBundle;

/**
 *
 * @author vincze.attila
 */
public class CaseFlagConstraint extends base.BaseConstraint {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("hungarianName".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("hungarianNameMustBeFilled"), evt);
            }
        } else if ("englishName".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("englishNameMustBeFilled"), evt);
            }
        }
    }

    public CaseFlagConstraint(ResourceBundle bundle) {
        super(bundle);
    }
}
