/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author vincze.attila
 */
public class DisplayModeHandler extends ModeHandlerBase {

    private boolean editButtonEnabled;
    private boolean saveButtonEnabled;
    private boolean cancelButtonEnabled;

    public DisplayModeHandler() {
        super();
        setMode(DisplayMode.View);
    }

    @Override
    protected void setDefaultStateAppearance(DisplayMode mode) {
        super.setDefaultStateAppearance(mode);
        switch (mode) {
            case Insert: {
                setSaveButtonEnabled(true);
                setCancelButtonEnabled(true);
                setEditButtonEnabled(false);
            }
            break;
            case Edit: {
                setSaveButtonEnabled(true);
                setCancelButtonEnabled(true);
                setEditButtonEnabled(false);
            }
            break;
            case View: {
                setSaveButtonEnabled(false);
                setCancelButtonEnabled(false);
                setEditButtonEnabled(true);
            }
            break;
            case Empty: {
                setSaveButtonEnabled(false);
                setCancelButtonEnabled(false);
                setEditButtonEnabled(false);
            }
            break;
            case Disabled: {
                setSaveButtonEnabled(false);
                setCancelButtonEnabled(false);
                setEditButtonEnabled(false);
            }
            break;
        }
    }

    public boolean isCancelButtonEnabled() {
        return cancelButtonEnabled;
    }

    public void setCancelButtonEnabled(boolean cancelButtonEnabled) {
        boolean oldValue = this.cancelButtonEnabled;
        this.cancelButtonEnabled = cancelButtonEnabled;
        propertySupport.firePropertyChange("cancelButtonEnabled", oldValue, this.cancelButtonEnabled);
    }

    public boolean isSaveButtonEnabled() {
        return saveButtonEnabled;
    }

    public void setSaveButtonEnabled(boolean saveButtonEnabled) {
        boolean oldValue = this.saveButtonEnabled;
        this.saveButtonEnabled = saveButtonEnabled;
        propertySupport.firePropertyChange("saveButtonEnabled", oldValue, this.saveButtonEnabled);
    }

    public boolean isEditButtonEnabled() {
        return editButtonEnabled;
    }

    public void setEditButtonEnabled(boolean editButtonEnabled) {
        boolean oldValue = this.editButtonEnabled;
        this.editButtonEnabled = editButtonEnabled;
        propertySupport.firePropertyChange("editButtonEnabled", oldValue, this.editButtonEnabled);
    }
}
