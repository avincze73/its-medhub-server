/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StudyWithBodyRegionsPanel.java
 *
 * Created on 2011.04.12., 12:39:57
 */
package view;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import casemodule.helper.StudyWithBodyRegionPM;
import converter.ComboboxTableCellRenderer;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import masterdatamodule.dto.BodyRegionDTO;

/**
 *
 * @author vincze.attila
 */
public class StudyWithBodyRegionsPanel extends javax.swing.JPanel {

    private ObservableList<StudyWithBodyRegionPM> studyWithBodyRegionList;
    private JComboBox bodyRegionEditor = new JComboBox();
    private List<String> bodyRegionNameList;

    /** Creates new form StudyWithBodyRegionsPanel */
    public StudyWithBodyRegionsPanel(List<BodyRegionDTO> bodyRegionList) {
        studyWithBodyRegionList = ObservableCollections.observableList(new ArrayList<StudyWithBodyRegionPM>());
        StudyWithBodyRegionPM pm = new StudyWithBodyRegionPM();
        bodyRegionNameList = new ArrayList<String>();
        for (BodyRegionDTO bodyRegion : bodyRegionList) {
            bodyRegionNameList.add(bodyRegion.getEnglishName());
            bodyRegionEditor.addItem(bodyRegion.getEnglishName());
        }
//        bodyRegionNameList.add("Back");
//        bodyRegionNameList.add("Head");
//        bodyRegionEditor.addItem("Back");
//        bodyRegionEditor.addItem("Head");
        initComponents();
        initBindings();
    }

    private void initBindings() {
        org.jdesktop.beansbinding.BindingGroup bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${studyWithBodyRegionList}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tblBodyRegion);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${seriesNumber}"));
        columnBinding.setColumnName("Series Number");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${modalityName}"));
        columnBinding.setColumnName("Modality Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${hospitalBodyRegionName}"));
        columnBinding.setColumnName("Hospital Body Region Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${info}"));
        columnBinding.setColumnName("Info");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${radiologistBodyRegionName}"));
        columnBinding.setColumnName("Radiologist Body Region Name");
        columnBinding.setColumnClass(String.class);

        columnBinding.setEditable(true);
        jTableBinding.setSourceUnreadableValue(java.util.Collections.emptyList());
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        tblBodyRegion.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(bodyRegionEditor));
        tblBodyRegion.getColumnModel().getColumn(4).setCellRenderer(new ComboboxTableCellRenderer(bodyRegionNameList));
        tblBodyRegion.getColumnModel().getColumn(4).setMinWidth(150);
        bindingGroup.bind();
    }

    public void setStudy(String study) {
        lblStudy.setText(study);
    }

    public String getStudy() {
        return lblStudy.getText();
    }

    public void setStudyDescription(String studyDescription) {
        lblStudyDescription.setText(studyDescription);
    }

    public String getStudyDescription() {
        return lblStudyDescription.getText();
    }

    public ObservableList<StudyWithBodyRegionPM> getStudyWithBodyRegionList() {
        return studyWithBodyRegionList;
    }

    public void setStudyWithBodyRegionList(ObservableList<StudyWithBodyRegionPM> studyWithBodyRegionList) {
        this.studyWithBodyRegionList = studyWithBodyRegionList;
    }

    public JComboBox getBodyRegionEditor() {
        return bodyRegionEditor;
    }

    public void setNumberOfRows(int rows) {
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), rows * 20 + 30));
    }

    public int getNumberOfRows() {
        return (int) getPreferredSize().getHeight();
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

        lblStudy = new javax.swing.JLabel();
        lblStudyDescription = new javax.swing.JLabel();
        tblBodyRegion = new org.jdesktop.swingx.JXTable();

        setBackground(new java.awt.Color(245, 249, 254));
        setLayout(new java.awt.GridBagLayout());

        lblStudy.setText("Vizsgálat 1");
        lblStudy.setPreferredSize(new java.awt.Dimension(60, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        add(lblStudy, gridBagConstraints);

        lblStudyDescription.setForeground(java.awt.Color.gray);
        lblStudyDescription.setText("Accession number...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(lblStudyDescription, gridBagConstraints);

        tblBodyRegion.setBackground(new java.awt.Color(245, 249, 254));
        tblBodyRegion.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tblBodyRegion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBodyRegion.setGridColor(new java.awt.Color(245, 249, 254));
        tblBodyRegion.setRowSelectionAllowed(false);
        tblBodyRegion.setShowHorizontalLines(false);
        tblBodyRegion.setShowVerticalLines(false);
        tblBodyRegion.setTableHeader(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        add(tblBodyRegion, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblStudy;
    private javax.swing.JLabel lblStudyDescription;
    private org.jdesktop.swingx.JXTable tblBodyRegion;
    // End of variables declaration//GEN-END:variables
}
