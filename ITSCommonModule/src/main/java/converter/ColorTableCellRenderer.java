/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class ColorTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            ((JLabel) cell).setForeground(Color.RED);
        }
        return cell;
    }
}
