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
public class ModeOfAcquisitionConstraint extends base.BaseConstraint {

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if ("name".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("nameMustBeFilled"), evt);
            }
        } else if ("code".equals(evt.getPropertyName())) {
            if (evt.getNewValue() == null || "".equals(evt.getNewValue().toString())) {
                throw new PropertyVetoException(bundle.getString("codeMustBeFilled"), evt);
            }
        }
    }

    public ModeOfAcquisitionConstraint(ResourceBundle bundle) {
        super(bundle);
    }
}
