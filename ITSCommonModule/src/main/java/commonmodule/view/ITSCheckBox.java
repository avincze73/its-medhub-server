/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 *
 * @author vincze.attila
 */
public class ITSCheckBox extends JCheckBox {

    private Icon viewCheckedIcon;
    private Icon viewUncheckedIcon;
    private Icon editCheckedIcon;
    private Icon editUncheckedIcon;

    public ITSCheckBox() {
        super();
        setBorder(null);
        editUncheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxeditunchecked.PNG"));
        editCheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxeditchecked.PNG"));
        viewUncheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxviewunchecked.PNG"));
        viewCheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxviewchecked.PNG"));
        setIcon(editUncheckedIcon);
        setSelectedIcon(editCheckedIcon);
        setDisabledIcon(viewUncheckedIcon);
        setDisabledSelectedIcon(viewCheckedIcon);
        setOpaque(false);
    }
}
