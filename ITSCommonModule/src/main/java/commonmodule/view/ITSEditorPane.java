/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JEditorPane;

/**
 *
 * @author vincze.attila
 */
public class ITSEditorPane extends JEditorPane {

    public ITSEditorPane() {
        super();
        setPreferredSize(new Dimension(425, 80));
        setBackground(ITSColor.color3);
    }

    public void setLineNumber(int lineNumber) {
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), lineNumber * 20));
    }

    public int getLineNumber() {
        return (int) getPreferredSize().getHeight() / 20;
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
//        setBorder(b
//                ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
//                : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, TDSColor.color2)));
        setBackground(b ? Color.WHITE : ITSColor.color2);
    }
}
