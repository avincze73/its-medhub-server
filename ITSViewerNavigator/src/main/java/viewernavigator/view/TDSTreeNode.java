/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewernavigator.view;

/**
 *
 * @author vincze.attila
 */
public class TDSTreeNode {

    private String label;

    public TDSTreeNode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
