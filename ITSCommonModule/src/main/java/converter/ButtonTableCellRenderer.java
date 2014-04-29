/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author vincze.attila
 */
public class ButtonTableCellRenderer extends JButton implements TableCellRenderer {

    protected String text;

    public ButtonTableCellRenderer(String text) {
        setOpaque(true);
        //setVisible(false);
        //this.setEnabled(false);
        this.text = text;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
            //setEnabled(false);
        }
        setText(text);
//        if (value instanceof CaseDTO) {
//            //setText( (value == null) ? "" : ((String)(CaseDTO) value).getId());
//            int id = ((CaseDTO) value).getId();
//            setText(String.valueOf(id));
//        }
        return this;
    }
}
