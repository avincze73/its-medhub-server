/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;

/**
 *
 * @author vincze.attila
 */
public class ScenarioButtonTableCellRenderer extends ButtonTableCellRenderer {

    public ScenarioButtonTableCellRenderer(String text) {
        super(text);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //System.out.println(table.getValueAt(row, column - 1 ));
        //setVisible(false);
        if (isSelected) {
            if (isEnabled()) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            }
            repaint();
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
            //setBackground(table.getBackground());
            repaint();
        }
        setText(text);
        //setEnabled(false);
//        if (value instanceof CaseDTO) {
//            //setText( (value == null) ? "" : ((String)(CaseDTO) value).getId());
//            int id = ((CaseDTO) value).getId();
//            setText(String.valueOf(id));
//        }
        return this;
    }
}
