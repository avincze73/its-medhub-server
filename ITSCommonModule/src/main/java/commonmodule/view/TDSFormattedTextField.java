/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author vincze.attila
 */
public class TDSFormattedTextField extends JFormattedTextField {

    private boolean optional;
    private boolean error;
    private int decimalPlaces;

    public TDSFormattedTextField() {
        super();
        setPreferredSize(new Dimension(60, 20));
        setBackground(Color.WHITE);
        NumberFormatter nf = new NumberFormatter();
        nf.setValueClass(Double.class);
        setFormatter(nf);
        //setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.####"))));

        optional = false;
        error = false;
    }

    public boolean isComponentVisible() {
        return isVisible();
    }

    public void setComponentVisible(boolean componentVisible) {
        setVisible(componentVisible);
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
//        if (decimalPlaces == 1){
//            setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.####"))));
//        } else {
//            setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.####"))));
//        }
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
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
