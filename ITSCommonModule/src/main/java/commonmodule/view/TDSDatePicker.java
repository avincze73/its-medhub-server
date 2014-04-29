/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author vincze.attila
 */
public class TDSDatePicker extends JXDatePicker {

    private boolean optional;
    private boolean error;

    public TDSDatePicker() {
        super();
        setPreferredSize(new Dimension(150, 20));
        setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
        getEditor().setBorder(javax.swing.BorderFactory.createEmptyBorder(0,5,0,0));
        optional = false;
        error = false;
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        if (error) {
            if (!optional) {
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
            } else {
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
            }
        } else {
            if (!optional) {
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
            } else {
                setBorder(javax.swing.BorderFactory.createLineBorder(Color.gray));
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
