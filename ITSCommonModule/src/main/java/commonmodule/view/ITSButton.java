/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import javax.swing.JButton;

/**
 *
 * @author vincze.attila
 */
public class ITSButton extends JButton {

    public ITSButton() {
    }

    public boolean isComponentVisible() {
        return isVisible();
    }

    public void setComponentVisible(boolean componentVisible) {
        setVisible(componentVisible);
    }
}
