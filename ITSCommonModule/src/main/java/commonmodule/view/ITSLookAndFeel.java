/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import javax.swing.UIDefaults;

/**
 *
 * @author vincze.attila
 */
//GTKLookAndFeel { // WindowsLookAndFeel {
public class ITSLookAndFeel extends  WindowsLookAndFeel {

    public ITSLookAndFeel() {
        super();
    }

    @Override
    protected void initComponentDefaults(UIDefaults table) {
        super.initComponentDefaults(table);
        Object[] defaults = {
            "Label.disabledForeground", Color.BLACK,
            //"Tree.fixedRowHeight", false
            "Tree.rowHeight", 0
        };

        table.putDefaults(defaults);
    }
}
