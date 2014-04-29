/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author vincze.attila
 */
public class ITSTextArea extends JTextArea {

    public ITSTextArea() {
        super();
        //setPreferredSize(new Dimension(200, 100));
        //setBorder(javax.swing.BorderFactory.createEmptyBorder());
        setFont(new java.awt.Font("Tahoma", 0, 11));
    }

    @Override
    public void setEditable(boolean b) {
        super.setEditable(b);
        setBackground(b ? Color.WHITE : ITSColor.color2);
    }
}
