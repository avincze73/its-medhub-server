/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package base;

/**
 *
 * @author vincze.attila
 */
public class ListActionModeHandler extends ModeHandlerBase {

    private DisplayMode mode;
    private boolean selectButtonEnabled;

    public ListActionModeHandler() {
        super();
        setMode(DisplayMode.View);
    }


    @Override
    protected void setDefaultStateAppearance(DisplayMode mode) {
        super.setDefaultStateAppearance(mode);
        switch (mode) {
            case Insert: {
                setSelectButtonEnabled(false);
            }
            break;
            case Edit: {
                setSelectButtonEnabled(false);
            }
            break;
            case View: {
                setSelectButtonEnabled(true);
            }
            break;
            case Empty: {
                setSelectButtonEnabled(false);
            }
            break;
            case Disabled: {
                setSelectButtonEnabled(false);
            }
            break;
        }
    }


    public boolean isSelectButtonEnabled() {
        return selectButtonEnabled;
    }

    public void setSelectButtonEnabled(boolean selectButtonEnabled) {
        boolean oldValue = this.selectButtonEnabled;
        this.selectButtonEnabled = selectButtonEnabled;
        propertySupport.firePropertyChange("selectButtonEnabled", oldValue, this.selectButtonEnabled);
    }
    
}
