/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CaseManagementView.java
 *
 * Created on 2011.04.06., 10:54:16
 */
package casemodule.view;

import base.ITDSRefresh;
import casemodule.iview.ICaseManagementView;
import casemodule.viewmodel.CaseManagementViewModel;
import commonmodule.view.TDSColor;
import java.util.ResourceBundle;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;

/**
 *
 * @author vincze.attila
 */
public class CaseManagementView extends javax.swing.JInternalFrame implements ICaseManagementView, ITDSRefresh {

    private CaseManagementViewModel viewModel;

    /** Creates new form CaseManagementView */
    public CaseManagementView() {
        viewModel = new CaseManagementViewModel(this);
        initComponents();
        initBindings();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
        thpAssignedSearch.getBtnHide().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpnAssignedSearch.setCollapsed(!cpnAssignedSearch.isCollapsed());
            }
        });
        cpnAssignedSearch.getContentPane().setBackground(TDSColor.color3);

        thpQueueSearch.getBtnHide().addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpnQueueSearch.setCollapsed(!cpnQueueSearch.isCollapsed());
            }
        });
        cpnQueueSearch.getContentPane().setBackground(TDSColor.color3);
    }

    public CaseManagementViewModel getViewModel() {
        return viewModel;
    }

    private void initBindings() {
        ResourceBundle bundle = viewModel.getBundle();
        BindingGroup bindingGroup = new BindingGroup();
        //assigned case list
        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.assignedCaseList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblAssignedCase);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.abbrevName}"));
        columnBinding.setColumnName("Hospital Contract.hospital.official Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${assignedRadiologist.userInfo.name}"));
        columnBinding.setColumnName("Assigned Radiologist.user Info.name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${modalities}"));
        columnBinding.setColumnName("Modalities");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bodyRegions}"));
        columnBinding.setColumnName("Body Regions");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dicomPatientData.patientName}"));
        columnBinding.setColumnName("Patient And Referral Info.dicom Patient Data.patient Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tdsCaseUniqueId}"));
        columnBinding.setColumnName("Tds Case Unique Id");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${caseStatus.englishName}"));
        columnBinding.setColumnName("Case Status.english Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${urgency}"));
        columnBinding.setColumnName("Urgency");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${scenarios}"));
        columnBinding.setColumnName("Scenarios");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedAssignedCase}"), tblAssignedCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedAssignedCaseIndex}"), tblAssignedCase, org.jdesktop.beansbinding.BeanProperty.create("selectedIndex"));
        bindingGroup.addBinding(binding);
        tblAssignedCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title0")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title1")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title2")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title3")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title4")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title5")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title6")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title7")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("CaseManagementView.tblAssignedCase.columnModel.title8")); // NOI18N

        //normal waiting case list
        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.normalWaitingCaseList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblWaitingNormalCase);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.abbrevName}"));
        columnBinding.setColumnName("Hospital Contract.hospital.abbrev name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${modalities}"));
        columnBinding.setColumnName("Modalities");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bodyRegions}"));
        columnBinding.setColumnName("Body Regions");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tdsCaseUniqueId}"));
        columnBinding.setColumnName("Tds Case Unique Id");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dicomPatientData.patientName}"));
        columnBinding.setColumnName("Patient And Referral Info.dicom Patient Data.patient Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${caseStatus.englishName}"));
        columnBinding.setColumnName("Case Status.english Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${urgency}"));
        columnBinding.setColumnName("Urgency");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${scenarios}"));
        columnBinding.setColumnName("Scenarios");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedNormalWaitingCase}"), tblWaitingNormalCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedNormalWaitingCaseIndex}"), tblWaitingNormalCase, org.jdesktop.beansbinding.BeanProperty.create("selectedIndex"));
        bindingGroup.addBinding(binding);

        tblWaitingNormalCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title0")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title1")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title2")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title3")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title4")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title5")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title6")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingNormalCase.columnModel.title7")); // NOI18N

        //urgent waiting
        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.urgentWaitingCaseList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblWaitingUrgentCase);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.abbrevName}"));
        columnBinding.setColumnName("Hospital Contract.hospital.Abbrev name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${modalities}"));
        columnBinding.setColumnName("Modalities");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${bodyRegions}"));
        columnBinding.setColumnName("Body Regions");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tdsCaseUniqueId}"));
        columnBinding.setColumnName("Tds Case Unique Id");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${dicomPatientData.patientName}"));
        columnBinding.setColumnName("dicom Patient Data.patient Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${caseStatus.englishName}"));
        columnBinding.setColumnName("Case Status.english Name");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${urgency}"));
        columnBinding.setColumnName("Urgency");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${scenarios}"));
        columnBinding.setColumnName("Scenarios");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedUrgentWaitingCase}"), tblWaitingUrgentCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);
        binding = Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedUrgentWaitingCaseIndex}"), tblWaitingUrgentCase, org.jdesktop.beansbinding.BeanProperty.create("selectedIndex"));
        bindingGroup.addBinding(binding);



        tblWaitingUrgentCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title0")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title1")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title2")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title3")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title4")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title5")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title6")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseManagementView.tblWaitingUrgentCase.columnModel.title7")); // NOI18N


        bindingGroup.bind();

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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tDSSeparatorPanel1 = new commonmodule.view.TDSSeparatorPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnTaken = new javax.swing.JButton();
        btnTakenAway = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btnAssign = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        thpAssignedSearch = new commonmodule.view.TDSHeaderPanel();
        jPanel7 = new javax.swing.JPanel();
        cpnAssignedSearch = new org.jdesktop.swingx.JXCollapsiblePane();
        btnAll = new javax.swing.JButton();
        btnSearch = new commonmodule.view.TDSSearchButton();
        tDSTextField1 = new commonmodule.view.TDSTextField();
        lblSearchName = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAssignedCase = new commonmodule.view.TDSTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        thpQueueSearch = new commonmodule.view.TDSHeaderPanel();
        jPanel8 = new javax.swing.JPanel();
        cpnQueueSearch = new org.jdesktop.swingx.JXCollapsiblePane();
        btnAll1 = new javax.swing.JButton();
        btnSearch1 = new commonmodule.view.TDSSearchButton();
        tDSTextField2 = new commonmodule.view.TDSTextField();
        lblSearchName1 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblWaitingNormalCase = new commonmodule.view.TDSTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWaitingUrgentCase = new commonmodule.view.TDSTable();

        setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(20, 20, 20, 20, new java.awt.Color(207, 224, 242)), javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray)));

        jPanel1.setBackground(new java.awt.Color(238, 243, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(25, 30, 30, 30, new java.awt.Color(238, 243, 250)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(146, 40));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(977, 35));
        jPanel2.setLayout(new java.awt.BorderLayout());

        tDSSeparatorPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        tDSSeparatorPanel1.setPreferredSize(new java.awt.Dimension(0, 4));

        javax.swing.GroupLayout tDSSeparatorPanel1Layout = new javax.swing.GroupLayout(tDSSeparatorPanel1);
        tDSSeparatorPanel1.setLayout(tDSSeparatorPanel1Layout);
        tDSSeparatorPanel1Layout.setHorizontalGroup(
            tDSSeparatorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1134, Short.MAX_VALUE)
        );
        tDSSeparatorPanel1Layout.setVerticalGroup(
            tDSSeparatorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel2.add(tDSSeparatorPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(0, 25));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jPanel13.setLayout(new java.awt.GridBagLayout());

        btnTaken.setAction(viewModel.getTakenAction());
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("casemodule/bundle/CaseManagementBundle"); // NOI18N
        btnTaken.setText(bundle.getString("CaseManagementView.btnTaken.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel13.add(btnTaken, gridBagConstraints);

        btnTakenAway.setAction(viewModel.getTakenAwayAction());
        btnTakenAway.setText(bundle.getString("CaseManagementView.btnTakenAway.text")); // NOI18N
        jPanel13.add(btnTakenAway, new java.awt.GridBagConstraints());

        jPanel6.add(jPanel13);

        jPanel14.setLayout(new java.awt.GridBagLayout());

        btnAssign.setAction(viewModel.getAssignAction());
        btnAssign.setText(bundle.getString("CaseManagementView.btnAssign.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel14.add(btnAssign, gridBagConstraints);

        jPanel6.add(jPanel14);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 5, new java.awt.Color(238, 243, 250)));
        jPanel4.setMinimumSize(new java.awt.Dimension(300, 113));
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel11.setOpaque(false);
        jPanel11.setLayout(new java.awt.BorderLayout());

        thpAssignedSearch.setInfo(bundle.getString("CaseManagementView.thpAssignedSearch.info")); // NOI18N
        jPanel11.add(thpAssignedSearch, java.awt.BorderLayout.PAGE_START);

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.BorderLayout());

        cpnAssignedSearch.setAnimated(false);
        cpnAssignedSearch.setBackground(new java.awt.Color(245, 249, 254));
        cpnAssignedSearch.setPreferredSize(new java.awt.Dimension(562, 50));
        cpnAssignedSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        btnAll.setText(bundle.getString("CaseManagementView.btnAll.text")); // NOI18N
        btnAll.setPreferredSize(new java.awt.Dimension(90, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 35);
        cpnAssignedSearch.getContentPane().add(btnAll, gridBagConstraints);

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casemodule/image/search.png"))); // NOI18N
        btnSearch.setText(bundle.getString("CaseManagementView.btnSearch.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnAssignedSearch.getContentPane().add(btnSearch, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnAssignedSearch.getContentPane().add(tDSTextField1, gridBagConstraints);

        lblSearchName.setText(bundle.getString("CaseManagementView.lblSearchName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnAssignedSearch.getContentPane().add(lblSearchName, gridBagConstraints);

        jPanel7.add(cpnAssignedSearch, java.awt.BorderLayout.PAGE_START);

        jPanel30.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(190, 200));
        jPanel30.setLayout(new java.awt.BorderLayout());

        tblAssignedCase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAssignedCase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tblAssignedCase);

        jPanel30.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel30, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel11, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText(bundle.getString("CaseManagementView.jLabel1.text")); // NOI18N
        jPanel4.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText(bundle.getString("CaseManagementView.jLabel2.text")); // NOI18N
        jPanel5.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel12.setMinimumSize(new java.awt.Dimension(400, 151));
        jPanel12.setLayout(new java.awt.BorderLayout());

        thpQueueSearch.setInfo(bundle.getString("CaseManagementView.thpQueueSearch.info")); // NOI18N
        jPanel12.add(thpQueueSearch, java.awt.BorderLayout.PAGE_START);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.BorderLayout());

        cpnQueueSearch.setAnimated(false);
        cpnQueueSearch.setBackground(new java.awt.Color(245, 249, 254));
        cpnQueueSearch.setPreferredSize(new java.awt.Dimension(562, 50));
        cpnQueueSearch.getContentPane().setLayout(new java.awt.GridBagLayout());

        btnAll1.setText(bundle.getString("CaseManagementView.btnAll1.text")); // NOI18N
        btnAll1.setPreferredSize(new java.awt.Dimension(90, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 35);
        cpnQueueSearch.getContentPane().add(btnAll1, gridBagConstraints);

        btnSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casemodule/image/search.png"))); // NOI18N
        btnSearch1.setText(bundle.getString("CaseManagementView.btnSearch1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnQueueSearch.getContentPane().add(btnSearch1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnQueueSearch.getContentPane().add(tDSTextField2, gridBagConstraints);

        lblSearchName1.setText(bundle.getString("CaseManagementView.lblSearchName1.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        cpnQueueSearch.getContentPane().add(lblSearchName1, gridBagConstraints);

        jPanel8.add(cpnQueueSearch, java.awt.BorderLayout.PAGE_START);

        jPanel31.setOpaque(false);
        jPanel31.setPreferredSize(new java.awt.Dimension(190, 200));
        jPanel31.setLayout(new java.awt.GridLayout(2, 1));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel9.setLayout(new java.awt.BorderLayout());

        tblWaitingNormalCase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tblWaitingNormalCase);

        jPanel9.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel31.add(jPanel9);

        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(238, 243, 250)));
        jPanel10.setLayout(new java.awt.BorderLayout());

        tblWaitingUrgentCase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblWaitingUrgentCase.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tblWaitingUrgentCase);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel31.add(jPanel10);

        jPanel8.add(jPanel31, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnAll1;
    private javax.swing.JButton btnAssign;
    private commonmodule.view.TDSSearchButton btnSearch;
    private commonmodule.view.TDSSearchButton btnSearch1;
    private javax.swing.JButton btnTaken;
    private javax.swing.JButton btnTakenAway;
    private org.jdesktop.swingx.JXCollapsiblePane cpnAssignedSearch;
    private org.jdesktop.swingx.JXCollapsiblePane cpnQueueSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblSearchName;
    private javax.swing.JLabel lblSearchName1;
    private commonmodule.view.TDSSeparatorPanel tDSSeparatorPanel1;
    private commonmodule.view.TDSTextField tDSTextField1;
    private commonmodule.view.TDSTextField tDSTextField2;
    private commonmodule.view.TDSTable tblAssignedCase;
    private commonmodule.view.TDSTable tblWaitingNormalCase;
    private commonmodule.view.TDSTable tblWaitingUrgentCase;
    private commonmodule.view.TDSHeaderPanel thpAssignedSearch;
    private commonmodule.view.TDSHeaderPanel thpQueueSearch;
    // End of variables declaration//GEN-END:variables
}
