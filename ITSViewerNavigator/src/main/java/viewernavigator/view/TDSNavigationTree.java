/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TDSNavigationTree.java
 *
 * Created on 2011.04.29., 14:17:38
 */
package viewernavigator.view;

import casemodule.downloading.CaseDownloader;
import casemodule.dto.CaseDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author vincze.attila
 */
public class TDSNavigationTree extends javax.swing.JPanel {

    private DefaultTreeModel navigationTreeModel;
    private Color infoColor;
    private Color iconBorderColor;
    private Color viewerBackgroundColor;
    private Color studyNodeColor;
    private Font studyNodeFont;
    private Border studyNodeBorder;
    private Color rootNodeColor;
    private Font rootNodeFont;
    private Border rootNodeBorder;


    /** Creates new form TDSNavigationTree */
    public TDSNavigationTree() {
        this(Color.CYAN, Color.RED, Color.YELLOW);
    }

    public TDSNavigationTree(Color infoColor, Color iconBorderColor, Color viewerBackgroundColor) {
        this.infoColor = infoColor;
        this.iconBorderColor = iconBorderColor;
        this.viewerBackgroundColor = viewerBackgroundColor;
        loadNavigationTree();
        initComponents();
        expandAll(treNavigation, true);
    }

    public TDSNavigationTree(Color iconBorderColor, Color viewerBackgroundColor, Color infoColor,
            Color studyNodeColor, Font studyNodeFont, Border studyNodeBorder,
            Color rootNodeColor, Font rootNodeFont, Border rootNodeBorder) {
        this.iconBorderColor = iconBorderColor;
        this.viewerBackgroundColor = viewerBackgroundColor;
        this.studyNodeColor = studyNodeColor;
        this.studyNodeFont = studyNodeFont;
        this.studyNodeBorder = studyNodeBorder;
        this.rootNodeColor = rootNodeColor;
        this.rootNodeFont = rootNodeFont;
        this.rootNodeBorder = rootNodeBorder;
        this.infoColor = infoColor;
        loadNavigationTree();
        initComponents();
        expandAll(treNavigation, true);
    }

    private void expandAll(JTree tree, boolean expand) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        expandAll(tree, new TreePath(root), expand);
    }

    private void expandAll(JTree tree, TreePath parent, boolean expand) {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

    public JTree getTreNavigation() {
        return treNavigation;
    }

    public void setTreNavigation(JTree treNavigation) {
        this.treNavigation = treNavigation;
    }

    public final void loadNavigationTree() {
        CaseDTO tdsMainCase = CaseDownloader.getInstance().getTdsMainCase();

        int studyNumber = 0;
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(new TDSRootNode("CASE FOR REPORTING"));

        for (StudyDTO study : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
            DefaultMutableTreeNode studyNode = new DefaultMutableTreeNode(new TDSStudyNode("Study " + ++studyNumber));
            rootNode.add(studyNode);
            for (SeriesDTO series : study.getSeriesList()) {
                DefaultMutableTreeNode seriesNode = new DefaultMutableTreeNode(series.getInstanceUid());
                studyNode.add(seriesNode);
            }
        }

        navigationTreeModel = new javax.swing.tree.DefaultTreeModel(rootNode);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        treNavigation = new javax.swing.JTree();

        setLayout(new java.awt.BorderLayout());

        treNavigation.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, viewerBackgroundColor));
        treNavigation.setModel(navigationTreeModel);
        treNavigation.setBackground(viewerBackgroundColor);
        treNavigation.setCellRenderer(new NavigationTreeCellRenderer(iconBorderColor, viewerBackgroundColor, infoColor, studyNodeColor, studyNodeFont, studyNodeBorder, rootNodeColor, rootNodeFont, rootNodeBorder));
        treNavigation.setDragEnabled(true);
        add(treNavigation, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree treNavigation;
    // End of variables declaration//GEN-END:variables
}
