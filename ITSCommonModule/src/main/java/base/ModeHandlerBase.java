/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author vincze.attila
 */
public class ModeHandlerBase {

    protected PropertyChangeSupport propertySupport;
    private boolean deleteButtonEnabled;
    private DisplayMode mode;
    private boolean newButtonEnabled;

    public ModeHandlerBase() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public DisplayMode getMode() {
        return mode;
    }

    public boolean isDeleteButtonEnabled() {
        return deleteButtonEnabled;
    }

    public boolean isNewButtonEnabled() {
        return newButtonEnabled;
    }

    public void setDeleteButtonEnabled(boolean deleteButtonEnabled) {
        boolean oldValue = this.deleteButtonEnabled;
        this.deleteButtonEnabled = deleteButtonEnabled;
        propertySupport.firePropertyChange("deleteButtonEnabled", oldValue, this.deleteButtonEnabled);
    }

    public final void setMode(DisplayMode mode) {
        this.mode = mode;
        setDefaultStateAppearance(mode);
    }

    public void setNewButtonEnabled(boolean newButtonEnabled) {
        boolean oldValue = this.newButtonEnabled;
        this.newButtonEnabled = newButtonEnabled;
        propertySupport.firePropertyChange("newButtonEnabled", oldValue, this.newButtonEnabled);
    }

    protected void setDefaultStateAppearance(DisplayMode mode) {
        switch (mode) {
            case Insert: {
                setNewButtonEnabled(false);
                setDeleteButtonEnabled(false);
            }
            break;
            case Edit: {
                setNewButtonEnabled(false);
                setDeleteButtonEnabled(false);
            }
            break;
            case View: {
                setNewButtonEnabled(true);
                setDeleteButtonEnabled(true);
            }
            break;
            case Empty: {
                setNewButtonEnabled(true);
                setDeleteButtonEnabled(false);
            }
            break;
            case Disabled: {
                setNewButtonEnabled(false);
                setDeleteButtonEnabled(false);
            }
            break;
        }
    }
}
