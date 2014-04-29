/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;

/**
 *
 * @author vincze.attila
 */
public class TDSNumericField extends JTextField implements
        NumericPlainDocument.InsertErrorListener {

    private boolean optional;
    private boolean error;

    public TDSNumericField() {
        this(null, 0, null);
    }

    public TDSNumericField(String text, int columns, DecimalFormat format) {
        super(null, text, columns);

        NumericPlainDocument numericDoc = (NumericPlainDocument) getDocument();
        numericDoc.setFormat(new java.text.DecimalFormat("#,###.####"));
        if (format != null) {
            numericDoc.setFormat(format);
        }

        numericDoc.addInsertErrorListener(this);
        setPreferredSize(new Dimension(60, 20));
    }

    public TDSNumericField(int columns, DecimalFormat format) {
        this(null, columns, format);
    }

    public TDSNumericField(String text) {
        this(text, 0, null);
    }

    public TDSNumericField(String text, int columns) {
        this(text, columns, null);
    }

    public void setFormat(DecimalFormat format) {
        ((NumericPlainDocument) getDocument()).setFormat(format);
    }

    public DecimalFormat getFormat() {
        return ((NumericPlainDocument) getDocument()).getFormat();
    }

    public void formatChanged() {
        // Notify change of format attributes.
        setFormat(getFormat());
    }

    // Methods to get the field value
    public Long getLongValue() throws ParseException {
        return ((NumericPlainDocument) getDocument()).getLongValue();
    }

    public Double getDoubleValue() throws ParseException {
        System.out.println("itt");
        return ((NumericPlainDocument) getDocument()).getDoubleValue();
    }

    public Number getNumberValue() throws ParseException {
        return ((NumericPlainDocument) getDocument()).getNumberValue();
    }

    @Override
    public void setText(String t) {
        super.setText(getFormat().format(Double.parseDouble(t)));
    }


    // Methods to install numeric values
    public void setValue(Number number) {
        setText(getFormat().format(number));
    }

    public void setValue(long l) {
        setText(getFormat().format(l));
    }

    public void setValue(double d) {
        
        setText(getFormat().format(d));
    }

    public void setDoubleValue(Double d) {
        System.out.println("set " + d);
        setText(getFormat().format(d));
    }


    public void normalize() throws ParseException {
        // format the value according to the format string
        setText(getFormat().format(getNumberValue()));
    }

    // Override to handle insertion error
    public void insertFailed(NumericPlainDocument doc, int offset, String str,
            AttributeSet a) {
        // By default, just beep
        Toolkit.getDefaultToolkit().beep();
    }

    // Method to create default model
    @Override
    protected Document createDefaultModel() {
        return new NumericPlainDocument();
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
