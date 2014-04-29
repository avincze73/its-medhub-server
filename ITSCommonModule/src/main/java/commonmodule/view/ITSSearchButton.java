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
public class ITSSearchButton extends JButton {

    public ITSSearchButton() {
        super();
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/search.png")));
        setPreferredSize(new Dimension(25, 25));
        setMinimumSize(new Dimension(25, 25));
        setMaximumSize(new Dimension(25, 25));
        setText(null);
        setOpaque(false);
        setActionCommand(null);
    }

}
