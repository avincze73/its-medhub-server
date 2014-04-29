/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class TDSTableCellRenderer extends DefaultTableCellRenderer {

    private Icon viewCheckedIcon;
    private Icon viewUncheckedIcon;

    public TDSTableCellRenderer() {
        super();
        viewUncheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxviewunchecked.PNG"));
        viewCheckedIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/checkboxviewchecked.PNG"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((JLabel) cell).setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 4, 0, 0));
        if (value instanceof Date && value != null) {
            ((JLabel) cell).setText(new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss").format(value));
        } else if (value instanceof Boolean) {
            ((JLabel) cell).setText(null);
            ((JLabel) cell).setIconTextGap(0);
            ((JLabel) cell).setIcon( ((Boolean)value) ? viewCheckedIcon : viewUncheckedIcon);
        } else if (value instanceof Double) {
            ((JLabel) cell).setText(value.toString());
        } else if (value instanceof Integer) {
            ((JLabel) cell).setText(value.toString());
        } else if (value instanceof Long) {
            ((JLabel) cell).setText(value.toString());
        }
        return cell;
    }
}
