/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class ComboboxTableCellRenderer extends JComboBox implements TableCellRenderer {

    public ComboboxTableCellRenderer(List<String> items) {
        super();
        for (String name : items) {
            addItem(name);
        }
        setSelectedIndex(-1);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if (isSelected) {
//            setForeground(table.getSelectionForeground());
//            super.setBackground(table.getSelectionBackground());
//        } else {
//            setForeground(table.getForeground());
//            setBackground(table.getBackground());
//        }
        for (int i= 0; i < getItemCount(); i++) {
            if (getItemAt(i).equals(value)){
                setSelectedIndex(i);
            }
        }
        return this;
    }
}
