/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutoFunctionLogView.java
 *
 * Created on 2011.02.11., 18:43:26
 */

package reportingmodule.view;

import converter.DateTimeTableCellRenderer;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import reportingmodule.iview.IAutoFunctionLogListView;
import reportingmodule.viewmodel.AutoFunctionLogListViewModel;

/**
 *
 * @author vincze.attila
 */
public class AutoFunctionLogView extends javax.swing.JInternalFrame implements IAutoFunctionLogListView {

    private AutoFunctionLogListViewModel viewModel;
    /** Creates new form AutoFunctionLogView */
    public AutoFunctionLogView() {
        viewModel = new AutoFunctionLogListViewModel(this);
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
        pnlListHeader.getBtnNew().setVisible(false);
        pnlSearchHeader.getBtnHide().addActionListener(new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                pnlSearch.setCollapsed(!pnlSearch.isCollapsed());
            }
        });
    }

    public AutoFunctionLogListViewModel getViewModel() {
        return viewModel;
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        pnlSearchHeader = new commonmodule.view.ITSHeaderPanel();
        pnlSearch = new org.jdesktop.swingx.JXCollapsiblePane();
        jPanel2 = new javax.swing.JPanel();
        pnlListHeader = new commonmodule.view.ITSListPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntity = new org.jdesktop.swingx.JXTable();

        setBackground(new java.awt.Color(243, 248, 254));
        setBorder(javax.swing.BorderFactory.createMatteBorder(8, 8, 8, 8, new java.awt.Color(243, 248, 254)));

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(pnlSearchHeader, java.awt.BorderLayout.PAGE_START);

        pnlSearch.setPreferredSize(new java.awt.Dimension(0, 80));
        jPanel1.add(pnlSearch, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(pnlListHeader, java.awt.BorderLayout.PAGE_START);

        tblEntity.setGridColor(java.awt.Color.lightGray);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.entityList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblEntity);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Long.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${added}"));
        columnBinding.setColumnName("Added");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${autoFunction.name}"));
        columnBinding.setColumnName("Típus név");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${autoFunction.typeClass}"));
        columnBinding.setColumnName("Típus osztály");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(tblEntity);
        tblEntity.getColumnModel().getColumn(0).setMinWidth(20);
        tblEntity.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblEntity.getColumnModel().getColumn(0).setMaxWidth(40);
        tblEntity.getColumnModel().getColumn(1).setCellRenderer(new DateTimeTableCellRenderer());

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private commonmodule.view.ITSListPanel pnlListHeader;
    private org.jdesktop.swingx.JXCollapsiblePane pnlSearch;
    private commonmodule.view.ITSHeaderPanel pnlSearchHeader;
    private org.jdesktop.swingx.JXTable tblEntity;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}