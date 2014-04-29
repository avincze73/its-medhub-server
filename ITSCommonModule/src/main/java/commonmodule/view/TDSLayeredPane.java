/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import javax.swing.JLayeredPane;

/**
 *
 * @author vincze.attila
 */
public class TDSLayeredPane extends JLayeredPane {

    private String series;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
        System.out.println(series);
    }
}
