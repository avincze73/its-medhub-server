/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AutoFunctionLogListView.java
 *
 * Created on 2011.03.28., 12:40:39
 */

package reportingmodule.view;

import commonmodule.view.ITSColor;
import java.util.Collections;
import reportingmodule.iview.IAutoFunctionLogListView;
import reportingmodule.viewmodel.AutoFunctionLogListViewModel;

/**
 *
 * @author vincze.attila
 */
public class AutoFunctionLogListView extends javax.swing.JInternalFrame implements IAutoFunctionLogListView {

    private AutoFunctionLogListViewModel viewModel;
    /** Creates new form AutoFunctionLogListView */
    public AutoFunctionLogListView() {
        viewModel = new AutoFunctionLogListViewModel(this);
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
        thpSearch.getBtnHide().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpnSearch.setCollapsed(!cpnSearch.isCollapsed());
            }
        });
        cpnSearch.getContentPane().setBackground(ITSColor.color3);
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
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        thpSearch = new commonmodule.view.ITSHeaderPanel();
        jPanel6 = new javax.swing.JPanel();
        cpnSearch = new org.jdesktop.swingx.JXCollapsiblePane();
        btnAll = new javax.swing.JButton();
        btnSearch = new commonmodule.view.ITSSearchButton();
        tDSTextField1 = new commonmodule.view.ITSTextField();
        lblSearchName = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEntities = new commonmodule.view.ITSTable();

        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(207, 224, 242)), javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray)));

        jPanel1.setBackground(new java.awt.Color(238, 243, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(25, 30, 30, 30, new java.awt.Color(238, 243, 250)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.info}"), thpSearch, org.jdesktop.beansbinding.BeanProperty.create("info"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.searchCriteria}"), thpSearch, org.jdesktop.beansbinding.BeanProperty.create("searchCriteria"));
        bindingGroup.addBinding(binding);

        jPanel1.add(thpSearch, java.awt.BorderLayout.PAGE_START);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        cpnSearch.setAnimated(false);
        cpnSearch.setBackground(new java.awt.Color(245, 249, 254));
        cpnSearch.setPreferredSize(new java.awt.Dimension(562, 50));
        cpnSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        btnAll.setAction(viewModel.getFindAllAction());
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("reportingmodule/bundle/AutoFunctionLogListBundle"); // NOI18N
        btnAll.setText(bundle.getString("AutoFunctionLogListView.btnAll.text")); // NOI18N
        btnAll.setPreferredSize(new java.awt.Dimension(90, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 35);
        cpnSearch.getContentPane().add(btnAll, gridBagConstraints);

        btnSearch.setAction(viewModel.getFindAction());
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reportingmodule/image/search.png"))); // NOI18N
        btnSearch.setText(bundle.getString("AutoFunctionLogListView.btnSearch.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnSearch.getContentPane().add(btnSearch, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnSearch.getContentPane().add(tDSTextField1, gridBagConstraints);

        lblSearchName.setText(bundle.getString("AutoFunctionLogListView.lblSearchName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnSearch.getContentPane().add(lblSearchName, gridBagConstraints);

        jPanel6.add(cpnSearch, java.awt.BorderLayout.PAGE_START);

        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(190, 200));
        jPanel30.setLayout(new java.awt.GridBagLayout());

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.entityList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblEntities);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${added}"));
        columnBinding.setColumnName("Added");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${autoFunction.typeClass}"));
        columnBinding.setColumnName("Auto Function.type Class");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${autoFunction.typeClass}"));
        columnBinding.setColumnName("Auto Function.type Class");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane2.setViewportView(tblEntities);
        tblEntities.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("AutoFunctionLogListView.tblEntities.columnModel.title0")); // NOI18N
        tblEntities.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("AutoFunctionLogListView.tblEntities.columnModel.title1")); // NOI18N
        tblEntities.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("AutoFunctionLogListView.tblEntities.columnModel.title2")); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 5, 0);
        jPanel30.add(jScrollPane2, gridBagConstraints);

        jPanel6.add(jPanel30, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private commonmodule.view.ITSSearchButton btnSearch;
    private org.jdesktop.swingx.JXCollapsiblePane cpnSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSearchName;
    private commonmodule.view.ITSTextField tDSTextField1;
    private commonmodule.view.ITSTable tblEntities;
    private commonmodule.view.ITSHeaderPanel thpSearch;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}