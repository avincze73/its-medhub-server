/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import event.DeactivateScenarioEvent;
import event.StartScenarioEvent;
import event.ITSEventManager;
import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author vincze.attila
 */
public class ScenarioDeactivateTableCellEditor extends ButtonTableCellEditor {

    private String scenarioId;

    public ScenarioDeactivateTableCellEditor(String label) {
        super(label);
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            ITSEventManager.fireDeactivateScenarioEvent(new DeactivateScenarioEvent(scenarioId));
        }
        isPushed = false;
        return label;
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
        scenarioId = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
//    @Override
//    public boolean isCellEditable(EventObject anEvent) {
//        return false;
//    }
}
