/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author vincze.attila
 */
public class TDSIntegerField extends JTextField {

    private boolean optional;
    private boolean error;

    public TDSIntegerField() {
        super();
        setPreferredSize(new Dimension(200, 20));
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

    public int getValue() {
        final String text = getText();
        if (text == null || text.length() == 0) {
            return 0;
        }
        return Integer.parseInt(text);
    }

    public void setValue(int value) {
        setText(String.valueOf(value));
    }

    @Override
    protected Document createDefaultModel() {
        return new IntegerDocument();
    }

    private class IntegerDocument extends PlainDocument {

        @Override
        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str != null) {
                try {
                    Integer.decode(str);
                    super.insertString(offs, str, a);
                } catch (NumberFormatException ex) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
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
