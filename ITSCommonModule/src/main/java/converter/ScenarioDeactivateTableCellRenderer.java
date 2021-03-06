/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class ScenarioDeactivateTableCellRenderer implements TableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String idWithBehaviour = (String) value;
        String[] array = idWithBehaviour.split(";");
        int id = Integer.parseInt(array[0]);
        int behaviour = Integer.parseInt(array[1]);
        boolean started = Boolean.parseBoolean(array[2]);
        if (behaviour == 1) {
            if (started) {
                return new JButton("Deactivate");
            } else {
                JButton button = new JButton("Deactivate");
                button.setEnabled(false);
                return button;
            }
        } else {
            return new JLabel();
        }
    }
}
