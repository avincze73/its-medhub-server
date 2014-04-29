/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import converter.TDSTableCellRenderer;
import java.awt.Color;
import java.util.Date;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author vincze.attila
 */
public class ITSTable extends JXTable {

    public ITSTable() {
        super();
        addHighlighter(HighlighterFactory.createSimpleStriping());
        //addHighlighter(HighlighterFactory.createAlternateStriping(Color.white, TDSColor.colorAlternate));
        setSelectionBackground(ITSColor.colorSelection);
        setForeground(Color.black);
        setBackground(Color.WHITE);
        setShowVerticalLines(false);
        setShowHorizontalLines(false);
        setGridColor(Color.LIGHT_GRAY);
        setColumnMargin(0);
        setDefaultRenderer(String.class, new TDSTableCellRenderer());
        setDefaultRenderer(Date.class, new TDSTableCellRenderer());
        setDefaultRenderer(Boolean.class, new TDSTableCellRenderer());
        setDefaultRenderer(Double.class, new TDSTableCellRenderer());
        setDefaultRenderer(Integer.class, new TDSTableCellRenderer());
        setDefaultRenderer(Long.class, new TDSTableCellRenderer());
    }

    public Integer getSelectedIndex() {
        return getSelectedRow();
    }

    public void setSelectedIndex(Integer selectedIndex) {
        if (selectedIndex == null || selectedIndex == -1) {
            clearSelection();
        } else {
            getSelectionModel().setSelectionInterval(selectedIndex, selectedIndex);
        }
    }
}
