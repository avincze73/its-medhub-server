/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;

/**
 *
 * @author vincze.attila
 */
public class TDSTableCellEditor extends DefaultCellEditor {

    public TDSTableCellEditor(JCheckBox checkBox) {
        super(checkBox);
    }
}
