/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.beans.VetoableChangeListener;
import java.util.ResourceBundle;

/**
 *
 * @author vincze.attila
 */
public abstract class BaseConstraint implements VetoableChangeListener {
   
    
    protected java.util.ResourceBundle bundle;

    public BaseConstraint(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    
}
