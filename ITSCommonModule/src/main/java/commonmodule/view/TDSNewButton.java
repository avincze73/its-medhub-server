/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package commonmodule.view;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author vincze.attila
 */
public class TDSNewButton extends JButton {

    public TDSNewButton() {
        super();
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/new.png")));
        setPreferredSize(new Dimension(25, 25));
        setMinimumSize(new Dimension(25, 25));
        setMaximumSize(new Dimension(25, 25));
        setText("");
        setLabel("");
        setOpaque(false);
    }

    
}
