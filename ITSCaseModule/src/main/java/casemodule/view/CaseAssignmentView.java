/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CaseAssignmentView.java
 *
 * Created on 2010.12.15., 17:05:18
 */
package casemodule.view;

import casemodule.iview.ICaseManagementView;
import casemodule.viewmodel.CaseManagementViewModel;
import event.ShowEmptyViewEvent;
import event.TDSEventManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 *
 * @author vincze.attila
 */
public class CaseAssignmentView extends javax.swing.JInternalFrame implements ICaseManagementView {

    private CaseManagementViewModel viewModel;

    /** Creates new form CaseAssignmentView */
    public CaseAssignmentView() {
        viewModel = new CaseManagementViewModel(this);
        initComponents();
        tblAssignedCase.addHighlighter(HighlighterFactory.createSimpleStriping());
        tblWaitingNormalCase.addHighlighter(HighlighterFactory.createSimpleStriping());
        tblWaitingUrgentCase.addHighlighter(HighlighterFactory.createSimpleStriping());

        tblWaitingNormalCase.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                tblWaitingUrgentCase.clearSelection();
            }
        });

        tblWaitingUrgentCase.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                tblWaitingNormalCase.clearSelection();
            }
        });

    }

    public CaseManagementViewModel getViewModel() {
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

        pnlAssignedCase = new org.jdesktop.swingx.JXTitledPanel();
        jPanel4 = new javax.swing.JPanel();
        btnSearchAssigned = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnTaken = new javax.swing.JButton();
        btnTakenAway = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAssignedCase = new org.jdesktop.swingx.JXTable();
        pnlWaitingCase = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jXTitledPanel1 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel5 = new javax.swing.JPanel();
        btnSearchNormal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblWaitingNormalCase = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        btnAssignFromNormal = new javax.swing.JButton();
        jXTitledPanel2 = new org.jdesktop.swingx.JXTitledPanel();
        jPanel6 = new javax.swing.JPanel();
        btnSearchUrgent = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblWaitingUrgentCase = new org.jdesktop.swingx.JXTable();
        jPanel8 = new javax.swing.JPanel();
        btnAssignFromUrgent = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 239, 251));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"); // NOI18N
        setTitle(bundle.getString("CaseAssignmentView.title")); // NOI18N
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/casemodule/image/caseassignment16.png"))); // NOI18N
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        pnlAssignedCase.setTitle(bundle.getString("CaseAssignmentView.pnlAssignedCase.title")); // NOI18N
        pnlAssignedCase.getContentContainer().setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        jPanel4.setPreferredSize(new java.awt.Dimension(263, 40));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnSearchAssigned.setText(bundle.getString("CaseAssignmentView.btnSearchAssigned.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel4.add(btnSearchAssigned, gridBagConstraints);

        pnlAssignedCase.getContentContainer().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        jPanel1.setPreferredSize(new java.awt.Dimension(263, 30));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnTaken.setAction(viewModel.getTakenAction());
        btnTaken.setText(bundle.getString("CaseAssignmentView.btnTaken.text")); // NOI18N
        btnTaken.setPreferredSize(new java.awt.Dimension(110, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel1.add(btnTaken, gridBagConstraints);

        btnTakenAway.setAction(viewModel.getTakenAwayAction());
        btnTakenAway.setText(bundle.getString("CaseAssignmentView.btnTakenAway.text")); // NOI18N
        btnTakenAway.setPreferredSize(new java.awt.Dimension(110, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(btnTakenAway, gridBagConstraints);

        pnlAssignedCase.getContentContainer().add(jPanel1, java.awt.BorderLayout.SOUTH);

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.assignedCaseList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblAssignedCase);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.officialName}"));
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${patientAndReferralInfo.dicomPatientData.patientName}"));
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
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedAssignedCase}"), tblAssignedCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane4.setViewportView(tblAssignedCase);
        tblAssignedCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title0")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title1")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title2")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title3")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title4")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title5")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title6")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title7")); // NOI18N
        tblAssignedCase.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("CaseAssignmentView.tblAssignedCase.columnModel.title8")); // NOI18N

        pnlAssignedCase.getContentContainer().add(jScrollPane4, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlAssignedCase);

        pnlWaitingCase.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        pnlWaitingCase.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(2, 0));

        jXTitledPanel1.setTitle(bundle.getString("CaseAssignmentView.jXTitledPanel1.title")); // NOI18N
        jXTitledPanel1.getContentContainer().setLayout(new java.awt.BorderLayout());

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        jPanel5.setPreferredSize(new java.awt.Dimension(263, 40));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        btnSearchNormal.setText(bundle.getString("CaseAssignmentView.btnSearchNormal.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel5.add(btnSearchNormal, gridBagConstraints);

        jXTitledPanel1.getContentContainer().add(jPanel5, java.awt.BorderLayout.PAGE_START);

        tblWaitingNormalCase.setShowGrid(true);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.normalWaitingCaseList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblWaitingNormalCase);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.officialName}"));
        columnBinding.setColumnName("Hospital Contract.hospital.official Name");
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${patientAndReferralInfo.dicomPatientData.patientName}"));
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
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedNormalWaitingCase}"), tblWaitingNormalCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tblWaitingNormalCase);
        tblWaitingNormalCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title0")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title1")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title2")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title3")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title4")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title5")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title6")); // NOI18N
        tblWaitingNormalCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingNormalCase.columnModel.title7")); // NOI18N

        jXTitledPanel1.getContentContainer().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnAssignFromNormal.setAction(viewModel.getAssignFromNormalWaitingAction());
        btnAssignFromNormal.setText(bundle.getString("CaseAssignmentView.btnAssignFromNormal.text")); // NOI18N
        btnAssignFromNormal.setPreferredSize(new java.awt.Dimension(220, 23));
        jPanel2.add(btnAssignFromNormal, new java.awt.GridBagConstraints());

        jXTitledPanel1.getContentContainer().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jXTitledPanel1);

        jXTitledPanel2.setTitle(bundle.getString("CaseAssignmentView.jXTitledPanel2.title")); // NOI18N
        jXTitledPanel2.getContentContainer().setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.lightGray));
        jPanel6.setPreferredSize(new java.awt.Dimension(263, 40));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        btnSearchUrgent.setText(bundle.getString("CaseAssignmentView.btnSearchUrgent.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel6.add(btnSearchUrgent, gridBagConstraints);

        jXTitledPanel2.getContentContainer().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${viewModel.urgentWaitingCaseList}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblWaitingUrgentCase);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalContract.hospital.officialName}"));
        columnBinding.setColumnName("Hospital Contract.hospital.official Name");
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
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${patientAndReferralInfo.dicomPatientData.patientName}"));
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
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.selectedUrgentWaitingCase}"), tblWaitingUrgentCase, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(tblWaitingUrgentCase);
        tblWaitingUrgentCase.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title0")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title1")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title2")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title3")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title4")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title5")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title6")); // NOI18N
        tblWaitingUrgentCase.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("CaseAssignmentView.tblWaitingUrgentCase.columnModel.title7")); // NOI18N

        jXTitledPanel2.getContentContainer().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel8.setPreferredSize(new java.awt.Dimension(220, 30));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        btnAssignFromUrgent.setAction(viewModel.getAssignFromUrgentWaitingAction());
        btnAssignFromUrgent.setText(bundle.getString("CaseAssignmentView.btnAssignFromUrgent.text")); // NOI18N
        btnAssignFromUrgent.setPreferredSize(new java.awt.Dimension(220, 23));
        jPanel8.add(btnAssignFromUrgent, new java.awt.GridBagConstraints());

        jXTitledPanel2.getContentContainer().add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel7.add(jXTitledPanel2);

        pnlWaitingCase.add(jPanel7, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlWaitingCase);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        TDSEventManager.fireShowEmptyViewEvent(new ShowEmptyViewEvent(""));
    }//GEN-LAST:event_formInternalFrameClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssignFromNormal;
    private javax.swing.JButton btnAssignFromUrgent;
    private javax.swing.JButton btnSearchAssigned;
    private javax.swing.JButton btnSearchNormal;
    private javax.swing.JButton btnSearchUrgent;
    private javax.swing.JButton btnTaken;
    private javax.swing.JButton btnTakenAway;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel1;
    private org.jdesktop.swingx.JXTitledPanel jXTitledPanel2;
    private org.jdesktop.swingx.JXTitledPanel pnlAssignedCase;
    private javax.swing.JPanel pnlWaitingCase;
    private org.jdesktop.swingx.JXTable tblAssignedCase;
    private org.jdesktop.swingx.JXTable tblWaitingNormalCase;
    private org.jdesktop.swingx.JXTable tblWaitingUrgentCase;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
