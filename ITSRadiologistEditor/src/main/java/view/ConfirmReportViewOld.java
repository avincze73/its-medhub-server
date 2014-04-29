/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConfirmReportView.java
 *
 * Created on 2010.12.15., 11:34:10
 */
package view;

import converter.ColorTableCellRenderer;
import iview.IConfirmReportView;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import viewmodel.ConfirmReportViewModel;

/**
 *
 * @author vincze.attila
 */
public class ConfirmReportViewOld extends javax.swing.JFrame implements IConfirmReportView,
        TableModelListener {

    private ConfirmReportViewModel viewModel;

    /** Creates new form ConfirmReportView */
    public ConfirmReportViewOld(javax.swing.JFrame parent) {
        viewModel = new ConfirmReportViewModel(this);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConfirmReportViewOld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ConfirmReportViewOld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ConfirmReportViewOld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ConfirmReportViewOld.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        setSize(600, 800);
        if (parent != null) {
            Rectangle rect = new Rectangle(parent.getLocationOnScreen(), parent.getSize());
            Dimension windSize = this.getSize();
            int x = ((rect.width - windSize.width) / 2) + rect.x;
            int y = ((rect.height - windSize.height) / 2) + rect.y;

            //int x = rect.x;
            //int y = rect.y;
            if (y < rect.y) {
                y = rect.y;
            }
            this.setLocation(x, y);
        }
        setVisible(true);
        //tblCaseFlag.getModel().addTableModelListener(this);
        //tblCaseFlag.setTableHeader(null);
        //setTree();
    }

    public ConfirmReportViewModel getViewModel() {
        return viewModel;
    }

//    private void setTree() {
//        Icon leafIcon = new javax.swing.ImageIcon(getClass().getResource("/image/treechild.png"));
//        Icon openIcon = new javax.swing.ImageIcon(getClass().getResource("/image/treeparent.png"));
//        Icon closedIcon = new javax.swing.ImageIcon(getClass().getResource("/image/treeparent.png"));
//        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) treBodyRegion.getCellRenderer();
//        renderer.setLeafIcon(leafIcon);
//        renderer.setClosedIcon(closedIcon);
//        renderer.setOpenIcon(openIcon);
//        //treBodyRegion.addTreeSelectionListener(this);
//        expandAll(treBodyRegion, true);
//    }
     // If expand is true, expands all nodes in the tree.
    // Otherwise, collapses all nodes in the tree.
    private void expandAll(JTree tree, boolean expand) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();

        // Traverse tree from root
        expandAll(tree, new TreePath(root), expand);
    }

    private void expandAll(JTree tree, TreePath parent, boolean expand) {
        // Traverse children
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }

        // Expansion or collapse must be done bottom-up
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel2 = new javax.swing.JPanel();
        pnlScenarioInstance = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblScenarioInstance = new org.jdesktop.swingx.JXTable();
        jPanel3 = new javax.swing.JPanel();
        btnModifyScenario = new javax.swing.JButton();
        pnlBodyRegion = new org.jdesktop.swingx.JXTitledPanel();
        jPanel4 = new javax.swing.JPanel();
        btnModifyBodyRegion = new javax.swing.JButton();
        cbAgree = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        epBodyRegion = new javax.swing.JEditorPane();
        pnlComment = new org.jdesktop.swingx.JXTitledPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        pnlButton = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eset verifikáció");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setLayout(new java.awt.BorderLayout());

        pnlScenarioInstance.setTitle("Scenariók");
        pnlScenarioInstance.setPreferredSize(new java.awt.Dimension(31, 200));
        pnlScenarioInstance.getContentContainer().setLayout(new java.awt.BorderLayout());

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.scenarioInstanceList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblScenarioInstance);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane4.setViewportView(tblScenarioInstance);

        pnlScenarioInstance.getContentContainer().add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnModifyScenario.setAction(viewModel.getOpenTroubleShootAction());
        btnModifyScenario.setText("Scenariók módosítása");
        jPanel3.add(btnModifyScenario, new java.awt.GridBagConstraints());

        pnlScenarioInstance.getContentContainer().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(pnlScenarioInstance, java.awt.BorderLayout.PAGE_START);

        pnlBodyRegion.setTitle("Test régiók");
        pnlBodyRegion.getContentContainer().setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnModifyBodyRegion.setAction(viewModel.getModifyBodyRegionAction());
        btnModifyBodyRegion.setText("Testrégiók módosítása");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(btnModifyBodyRegion, gridBagConstraints);

        cbAgree.setText("Egyetértek");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel4.add(cbAgree, gridBagConstraints);

        pnlBodyRegion.getContentContainer().add(jPanel4, java.awt.BorderLayout.PAGE_END);

        epBodyRegion.setContentType("text/html");
        epBodyRegion.setEditable(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.bodyRegions}"), epBodyRegion, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(epBodyRegion);

        pnlBodyRegion.getContentContainer().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(pnlBodyRegion, java.awt.BorderLayout.CENTER);

        pnlComment.setTitle("Adatkezelési log");
        pnlComment.getContentContainer().setLayout(new java.awt.BorderLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.dataProcLogEntry}"), jTextArea1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTextArea1);

        pnlComment.getContentContainer().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(pnlComment, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pnlButton.setPreferredSize(new java.awt.Dimension(400, 40));
        pnlButton.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnSave.setAction(viewModel.getVerifyAction());
        btnSave.setText("Megerősítés");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cbAgree, org.jdesktop.beansbinding.ELProperty.create("${selected}"), btnSave, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(btnSave, gridBagConstraints);

        btnCancel.setAction(viewModel.getCancelAction());
        btnCancel.setText("Mégsem");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(btnCancel, gridBagConstraints);

        pnlButton.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlButton, java.awt.BorderLayout.PAGE_END);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (viewModel.isSaveButtonEnabled()) {
            if (JOptionPane.showConfirmDialog(this, "Save?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            viewModel.getVerifyAction().actionPerformed(null);
            
        }
    }//GEN-LAST:event_formWindowClosing
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnModifyBodyRegion;
    private javax.swing.JButton btnModifyScenario;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbAgree;
    private javax.swing.JEditorPane epBodyRegion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private org.jdesktop.swingx.JXTitledPanel pnlBodyRegion;
    private javax.swing.JPanel pnlButton;
    private org.jdesktop.swingx.JXTitledPanel pnlComment;
    private org.jdesktop.swingx.JXTitledPanel pnlScenarioInstance;
    private org.jdesktop.swingx.JXTable tblScenarioInstance;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public void tableChanged(TableModelEvent e) {
        viewModel.setSaveButtonEnabled(true);
    }

    public void close() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
}
