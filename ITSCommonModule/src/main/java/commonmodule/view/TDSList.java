/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JList;

/**
 *
 * @author vincze.attila
 */
public class TDSList extends JList {

    public TDSList() {
        super();
        setSelectionMode(0);
        setPreferredSize(new Dimension(200, 80));
        setEnabled(true);
    }

    @Override
    public final void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBorder(enabled
                ? javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, java.awt.Color.white))
                : javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray), javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, ITSColor.color2)));
        setBackground(enabled ? Color.WHITE : ITSColor.color2);
    }


}
