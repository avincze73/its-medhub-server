/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author vincze.attila
 */
public class ModeHandler implements Serializable {

    private boolean saveButtonEnabled;
    private boolean cancelButtonEnabled;
    private boolean editButtonEnabled;
    private DisplayMode mode;

    public ModeHandler() {
        setDefaultStateAppearance(DisplayMode.View);
    }

    protected final void setDefaultStateAppearance(DisplayMode mode) {
        switch (mode) {
            case Edit: {
                setEditButtonEnabled(false);
                setCancelButtonEnabled(true);
                setSaveButtonEnabled(true);
            }
            break;
            case Insert: {
                setEditButtonEnabled(false);
                setCancelButtonEnabled(true);
                setSaveButtonEnabled(true);
            }
            break;
            case View: {
                setEditButtonEnabled(true);
                setCancelButtonEnabled(false);
                setSaveButtonEnabled(false);
            }
            break;
        }
    }

    public boolean isCancelButtonEnabled() {
        return cancelButtonEnabled;
    }

    public void setCancelButtonEnabled(boolean cancelButtonEnabled) {
        this.cancelButtonEnabled = cancelButtonEnabled;
    }

    public boolean isEditButtonEnabled() {
        return editButtonEnabled;
    }

    public void setEditButtonEnabled(boolean editButtonEnabled) {
        this.editButtonEnabled = editButtonEnabled;
    }

    public DisplayMode getMode() {
        return mode;
    }

    public void setMode(DisplayMode mode) {
        this.mode = mode;
        setDefaultStateAppearance(mode);
    }

    public boolean isSaveButtonEnabled() {
        return saveButtonEnabled;
    }

    public void setSaveButtonEnabled(boolean saveButtonEnabled) {
        this.saveButtonEnabled = saveButtonEnabled;
    }
}
