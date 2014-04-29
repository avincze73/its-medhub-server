/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;

/**
 *
 * @author vincze.attila
 */
public final class ITSTextField extends JTextField {

    private boolean optional;
    private boolean error;

    public ITSTextField() {
        super();
        setPreferredSize(new Dimension(200, 20));
        setBackground(Color.WHITE);
        optional = false;
        error = false;
    }

    public boolean isComponentVisible() {
        return isVisible();
    }

    public void setComponentVisible(boolean componentVisible) {
        setVisible(componentVisible);
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        if (error) {
            if (!optional) {
                setBorder(isEditable()
                        ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
                        : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, ITSColor.color2)));
            } else {
                setBorder(isEditable()
                        ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
                        : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, ITSColor.color2)));
            }
        } else {
            if (!optional) {
                setBorder(isEditable()
                        ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
                        : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, ITSColor.color2)));
            } else {
                setBorder(isEditable()
                        ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
                        : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, ITSColor.color2)));
            }
        }
        setBackground(isEditable() ? Color.WHITE : ITSColor.color2);
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
        setEditable(isEditable());
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
        setEditable(isEditable());
    }
}
