/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package commonmodule.view;

import java.awt.Dimension;
import javax.swing.JSpinner;

/**
 *
 * @author vincze.attila
 */
public class TDSSpinner extends JSpinner {

    public TDSSpinner() {
        super();
        setPreferredSize(new Dimension(60, 20));
    }

    public boolean isComponentVisible() {
        return isVisible();
    }

    public void setComponentVisible(boolean componentVisible) {
        setVisible(componentVisible);
    }
    
}
