/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 *
 * @author vincze.attila
 */
public class ViewModelBase implements Serializable {
    protected PropertyChangeSupport propertyChangeSupport;
    protected VetoableChangeSupport vetoableChangeSupport;
    protected java.util.ResourceBundle bundle;

    public ViewModelBase(ResourceBundle bundle) {
        this.bundle = bundle;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        vetoableChangeSupport = new VetoableChangeSupport(this);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }


    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }
    public ResourceBundle getBundle() {
        return bundle;
    }

    

}
