/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import masterdatamodule.dto.GoverningAuthorityDTO;

/**
 *
 * @author vincze.attila
 */
public class GovAuthorityListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        GoverningAuthorityDTO c = (GoverningAuthorityDTO) value;
        if (c != null) {
            setText(c.getName());
        }
        return this;
    }
}