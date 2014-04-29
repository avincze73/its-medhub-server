/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import masterdatamodule.dto.RegLicQualDTO;

/**
 *
 * @author vincze.attila
 */
public class RegLicQualListCellRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component cell = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        RegLicQualDTO c = (RegLicQualDTO) value;
        if (c != null) {
            setText(c.getName());
        }
        return this;
    }


}
