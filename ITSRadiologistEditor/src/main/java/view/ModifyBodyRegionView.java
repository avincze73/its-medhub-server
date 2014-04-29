/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModifyBodyRegionView.java
 *
 * Created on 2011.01.22., 8:02:00
 */
package view;

import casemodule.downloading.CaseDownloader;
import casemodule.dto.CaseDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import casemodule.helper.StudyWithBodyRegionPM;
import iview.IModifyBodyRegionView;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JComboBox;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.service.BodyRegionServiceRemote;
import viewmodel.ModifyBodyRegionViewModel;

/**
 *
 * @author vincze.attila
 */
public class ModifyBodyRegionView extends javax.swing.JFrame implements IModifyBodyRegionView {

    private ModifyBodyRegionViewModel viewModel;
    private List<StudyWithBodyRegionPM> studyWithBodyRegionList;
    private List<StudyWithBodyRegionsPanel> bodyRegionPanelList;
    protected List<BodyRegionDTO> bodyRegionList;

    /** Creates new form ModifyBodyRegionView */
    public ModifyBodyRegionView() {
        this(null);
    }

    public ModifyBodyRegionView(javax.swing.JFrame parent) {
        viewModel = new ModifyBodyRegionViewModel(this);
        this.studyWithBodyRegionList = new ArrayList<StudyWithBodyRegionPM>();
        bodyRegionPanelList = new ArrayList<StudyWithBodyRegionsPanel>();
        bodyRegionList = new ArrayList<BodyRegionDTO>();
        initComponents();
        loadBodyRegions();
        loadBodyRegionOfStudies();
        setSize(700, 400);
        if (parent != null) {

            Rectangle rect = new Rectangle(parent.getLocationOnScreen(), parent.getSize());
            Dimension windSize = this.getSize();
            int x = ((rect.width - windSize.width) / 2) + rect.x;
            int y = ((rect.height - windSize.height) / 2) + rect.y;
            if (y < rect.y) {
                y = rect.y;
            }
            this.setLocation(x, y);
        }
        setVisible(true);

    }

    private void loadBodyRegions() {
        try {
            BodyRegionServiceRemote service = (BodyRegionServiceRemote) new InitialContext().lookup("masterdatamodule.service.BodyRegionServiceRemote");
            List<BodyRegionDTO> res = service.getList();
            bodyRegionList.addAll(res);
        } catch (NamingException ex) {
            Logger.getLogger(ModifyBodyRegionViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<StudyWithBodyRegionsPanel> getBodyRegionPanelList() {
        return bodyRegionPanelList;
    }

    
    public ModifyBodyRegionViewModel getViewModel() {
        return viewModel;
    }

    public final void loadBodyRegionOfStudies() {
        int y = 0;
        int seriesNumber = 0;
        CaseDTO mainCase = CaseDownloader.getInstance().getTdsMainCase();
        for (StudyDTO studyDTO : mainCase.getReferralInfoList().get(0).getStudyList()) {
            StudyWithBodyRegionsPanel panel = new StudyWithBodyRegionsPanel(bodyRegionList);
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = y++;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
            gridBagConstraints.weightx = 1.0;
            if (y == mainCase.getReferralInfoList().get(0).getStudyList().size()) {
                gridBagConstraints.weighty = 1.0;
            }
            panel.setStudy("Study " + y);
            panel.setStudyDescription("(Accession number - " + studyDTO.getAccessionNumber() + ","
                    + " Study date and time - " + studyDTO.getStudyDate() + ")");
            //panel.setPreferredSize(new java.awt.Dimension(572, 100));
            panel.setNumberOfRows(studyDTO.getSeriesList().size());

            studyWithBodyRegionList.clear();
            seriesNumber = 0;
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                StudyWithBodyRegionPM pm = new StudyWithBodyRegionPM();
                pm.setStudyId(studyDTO.getId());
                pm.setStudyUid(studyDTO.getInstanceUid());
                pm.setSeriesId(seriesDTO.getId());
                pm.setSeriesUid(seriesDTO.getInstanceUid());
                pm.setSeriesNumberInList(++seriesNumber);
                pm.setModalityId(seriesDTO.getModality().getId());
                pm.setModalityName(seriesDTO.getModality().getName());
                pm.setHospitalBodyRegionId(seriesDTO.getHospitalBodyRegion().getId());
                pm.setHospitalBodyRegionName(seriesDTO.getHospitalBodyRegion().getEnglishName());
                if (seriesDTO.getRadiologistBodyRegion() != null) {
                    pm.setRadiologistBodyRegionId(seriesDTO.getRadiologistBodyRegion().getId());
                    pm.setRadiologistBodyRegionName(seriesDTO.getRadiologistBodyRegion().getEnglishName());
                }
                studyWithBodyRegionList.add(pm);
            }
            for (StudyWithBodyRegionPM studyWithBodyRegionPM : studyWithBodyRegionList) {
                panel.getStudyWithBodyRegionList().add(studyWithBodyRegionPM);
            }
            pnlBodyRegion.add(panel, gridBagConstraints);
            bodyRegionPanelList.add(panel);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBodyRegion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Test régió verifikáció");

        jPanel2.setBackground(new java.awt.Color(238, 243, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(25, 30, 10, 30, new java.awt.Color(238, 243, 250)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnSave.setAction(viewModel.getSaveAction());
        btnSave.setText("Mentés");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(btnSave, gridBagConstraints);

        btnCancel.setAction(viewModel.getCancelAction());
        btnCancel.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel1.add(btnCancel, gridBagConstraints);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Test régiók");
        jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(238, 243, 250));
        jScrollPane1.setBorder(null);

        pnlBodyRegion.setBackground(new java.awt.Color(245, 249, 254));
        pnlBodyRegion.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 10, 0, new java.awt.Color(238, 243, 250)));
        pnlBodyRegion.setLayout(new java.awt.GridBagLayout());
        jScrollPane1.setViewportView(pnlBodyRegion);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModifyBodyRegionView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlBodyRegion;
    // End of variables declaration//GEN-END:variables

    public void close() {
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }
}
