/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class LocalDateTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            ((JLabel) cell).setText(new LocaleDateConverter().convertForward((Date) value));
            ((JLabel) cell).setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
        }
        return cell;
    }
}
