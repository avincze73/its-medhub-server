/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author vincze.attila
 */
public class ITSComboBox extends JComboBox {

    private boolean error;

    public ITSComboBox() {
        super();
        setPreferredSize(new Dimension(200, 20));
        setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        //((BasicComboBoxRenderer) getRenderer()).setBorder(new EmptyBorder(0, 3, 0, 0));
        error = false;
    }
  
    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
        if (error) {
            setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        } else {
            setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        }
    }
}
