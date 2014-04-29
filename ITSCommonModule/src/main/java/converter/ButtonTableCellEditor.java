/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import event.OpenCaseEvent;
import event.ITSEventManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author vincze.attila
 */
public class ButtonTableCellEditor extends DefaultCellEditor {

    protected JButton button;
    protected String label;
    private String caseId;
    protected boolean isPushed;
    private String openType;

    public ButtonTableCellEditor(String label) {
        super(new JCheckBox());
        this.label = label;
        button = new JButton();
        button.setOpaque(true);
        this.openType = OpenCaseEvent.type.all.name();
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    public ButtonTableCellEditor(String label, String openType) {
        super(new JCheckBox());
        this.label = label;
        button = new JButton();
        button.setOpaque(true);
        this.openType = openType;
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        caseId = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;

        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            ITSEventManager.fireOpenCaseEvent(new OpenCaseEvent(caseId, this.openType));
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
