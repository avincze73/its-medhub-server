/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import java.awt.Frame;
import pm.CaseFlagPM;
import base.ViewModelBase;
import casemodule.downloading.CaseDownloader;
import casemodule.dto.CaseDTO;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import casemodule.helper.SeriesGroup;
import event.CaseStatusModifiedEvent;
import event.ITSEventManager;
import iview.IConfirmReportView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import masterdatamodule.dto.BodyRegionDTO;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import casemodule.helper.StudyWithBodyRegionPM;
import commonmodule.view.ITSGlassPane;
import event.CloseFrameEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import view.ModifyBodyRegionView;
import view.StudyWithBodyRegionsPanel;
import view.TroubleshootView;
import view.TroubleshootViewOld;

/**
 *
 * @author vincze.attila
 */
public class ConfirmReportViewModel extends ViewModelBase {

    private class BodyRegionOfStudyComparator implements Comparator<StudyWithBodyRegionPM> {

        public int compare(StudyWithBodyRegionPM o1, StudyWithBodyRegionPM o2) {
            String o1BodyRegion = o1.getStudyId() + o1.getModalityName() + o1.getHospitalBodyRegionName();
            String o2BodyRegion = o2.getStudyId() + o2.getModalityName() + o2.getHospitalBodyRegionName();
            return o1BodyRegion.compareTo(o2BodyRegion);
        }
    }

    private class BodyRegionWithNumber {

        private String bodyRegion;
        private int number;

        public BodyRegionWithNumber(String bodyRegion) {
            this.bodyRegion = bodyRegion;
            this.number = 1;
        }

        @Override
        public String toString() {
            return bodyRegion + " (" + number + ")";
        }

        public String getBodyRegion() {
            return bodyRegion;
        }

        public void increaseNumber() {
            number++;
        }
    }
    private ObservableList<CaseFlagPM> caseFlagPMList;
    private ObservableList<StudyWithBodyRegionPM> studyWithBodyRegionList;
    private ObservableList<ScenarioInstanceDTO> scenarioInstanceList;
    private final IConfirmReportView view;
    private Action verifyAction;
    private Action cancelAction;
    private Action modifyBodyRegionAction;
    private boolean saveButtonEnabled;
    private DefaultTreeModel bodyRegionTree;
    private String bodyRegions;
    private Action openTroubleShootAction;
    private boolean isSaving;
    private ModifyBodyRegionView modifyBodyRegionView;
    private String dataProcLogEntry;

    public ConfirmReportViewModel(final IConfirmReportView view) {
        super(null);
        this.view = view;
        caseFlagPMList = ObservableCollections.observableList(new ArrayList<CaseFlagPM>());
        caseFlagPMList.addAll(CaseDownloader.getInstance().getCaseFlagPMList());
        studyWithBodyRegionList = ObservableCollections.observableList(new ArrayList<StudyWithBodyRegionPM>());
        scenarioInstanceList = ObservableCollections.observableList(new ArrayList<ScenarioInstanceDTO>());
        List<ScenarioInstanceDTO> scenarioInstances = CaseDownloader.getInstance().getTdsMainCase().getScenarioInstanceList();
        for (ScenarioInstanceDTO scenarioInstanceDTO : scenarioInstances) {
            if (scenarioInstanceDTO.getDeactivated() == null) {
                scenarioInstanceList.add(scenarioInstanceDTO);
            }
        }
        loadBodyRegions();

        verifyAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Verify?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }
//                List<CaseFlagDTO> caseFlagList = new ArrayList<CaseFlagDTO>();
//                for (CaseFlagPM caseFlagPM : caseFlagPMList) {
//                    if (caseFlagPM.isChecked()) {
//                        caseFlagList.add(caseFlagPM.getCaseFlag());
//                    }
//                }
//                CaseDownloader.getInstance().confirmReport(saveBodyRegions(), dataProcLogEntry);
//                setSaveButtonEnabled(false);
//                TDSEventManager.fireEvent(
//                        new CaseStatusModifiedEvent(
//                        "",
//                        CaseDownloader.getInstance().getTdsMainCase().getId(),
//                        "confirmed"));
//                view.close();
                new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        ((JFrame) view).setGlassPane(new ITSGlassPane());
                        ((JFrame) view).getGlassPane().setVisible(true);
                        CaseDownloader.getInstance().confirmReport(saveBodyRegions(), dataProcLogEntry);
                        ITSEventManager.fireEvent(
                                new CaseStatusModifiedEvent(
                                "",
                                CaseDownloader.getInstance().getTdsMainCase().getId(),
                                "confirmed"));
                        view.close();
                        ITSEventManager.fireEvent(new CloseFrameEvent(""));
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            super.done();
                            get();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            ((JFrame) view).getGlassPane().setVisible(false);
                        }
                    }
                }.execute();

            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                view.close();
                ITSEventManager.fireEvent(new CloseFrameEvent(""));
            }
        };

        modifyBodyRegionAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                modifyBodyRegionView = new ModifyBodyRegionView(null);
                modifyBodyRegionView.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        StringBuilder sb = new StringBuilder();

                        if (modifyBodyRegionView.getViewModel().isSaving()) {
                            for (StudyWithBodyRegionsPanel studyWithBodyRegionsPanel : modifyBodyRegionView.getBodyRegionPanelList()) {
                                for (StudyWithBodyRegionPM studyWithBodyRegionPM : studyWithBodyRegionsPanel.getStudyWithBodyRegionList()) {
                                    sb.append(studyWithBodyRegionPM.getSeriesNumber());
                                    sb.append("\t");
                                    sb.append(studyWithBodyRegionPM.getModalityName());
                                    sb.append("\t");
                                    sb.append(studyWithBodyRegionPM.getHospitalBodyRegionName());
                                    sb.append("\t");
                                    sb.append(studyWithBodyRegionPM.getRadiologistBodyRegionName());
                                    sb.append("\n");
                                }
                            }

                            setBodyRegions(sb.toString());
                            System.out.println("saving");
                            sb.append("<html><body>");
                            for (int i = 0; i < studyWithBodyRegionList.size(); i++) {
                                StudyWithBodyRegionPM pm = studyWithBodyRegionList.get(i);
                                sb.append("<tr>");
                                sb.append("<td>" + pm.getStudyId() + "</td>");
                                sb.append("<td>" + pm.getModalityName() + "</td>");

                                if (!pm.getHospitalBodyRegionName().equals(pm.getRadiologistBodyRegionName())) {
                                    sb.append("<td><strike>" + pm.getHospitalBodyRegionName() + "</strike></td>");
                                    sb.append("<td><font color=\"red\">" + pm.getRadiologistBodyRegionName() + "</font></td>");
                                } else {
                                    sb.append("<td>" + pm.getHospitalBodyRegionName() + "</td>");
                                }

                                sb.append("</tr>");
                            }
                            sb.append("</body></html>");

                            //setBodyRegions(sb.toString());
                        }
                    }
                });
                modifyBodyRegionView.loadBodyRegionOfStudies();
                modifyBodyRegionView.setVisible(true);
            }
        };

        openTroubleShootAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                TroubleshootView dialog = new TroubleshootView((Frame) view, true);
                List<ScenarioInstanceDTO> scenarioInstances = CaseDownloader.getInstance().getTdsMainCase().getScenarioInstanceList();
                scenarioInstanceList.clear();
                for (ScenarioInstanceDTO scenarioInstanceDTO : scenarioInstances) {
                    if (scenarioInstanceDTO.getDeactivated() == null) {
                        scenarioInstanceList.add(scenarioInstanceDTO);
                    }
                }
            }
        };

    }

    private List<SeriesDTO> saveBodyRegions() {
        List<SeriesDTO> dtos = new ArrayList<SeriesDTO>();
        for (int i = 0; i < studyWithBodyRegionList.size(); i++) {
            StudyWithBodyRegionPM pm = studyWithBodyRegionList.get(i);
            SeriesDTO dto = new SeriesDTO(pm.getSeriesId());
            if (!pm.getHospitalBodyRegionName().equals(pm.getRadiologistBodyRegionName())) {
                dto.setRadiologistBodyRegion(new BodyRegionDTO(pm.getRadiologistBodyRegionName(), "", "", ""));
                dtos.add(dto);
            }
        }
//        try {
//            RcCaseServiceRemote service = (RcCaseServiceRemote) new InitialContext().lookup("casemodule.service.RcCaseServiceRemote");
//            service.updateBodyRegions(dtos);
//        } catch (NamingException ex) {
//            Logger.getLogger(ConfirmReportViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return dtos;
    }

    private void loadBodyRegions() {
        CaseDTO mainCase = CaseDownloader.getInstance().getTdsMainCase();
        setBodyRegions(mainCase.getStudyStructure());
        for (StudyDTO studyDTO : mainCase.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                StudyWithBodyRegionPM pm = new StudyWithBodyRegionPM();
                pm.setStudyId(studyDTO.getId());
                pm.setStudyUid(studyDTO.getInstanceUid());
                pm.setStudyNumberInList(studyDTO.getNumberInList());
                pm.setSeriesId(seriesDTO.getId());
                pm.setSeriesUid(seriesDTO.getInstanceUid());
                pm.setSeriesNumberInList(seriesDTO.getNumberInList());
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
        }
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html><body>");
//        for (StudyDTO studyDTO : mainCase.getReferralInfoList().get(0).getStudyList()) {
//            sb.append("<tr>");
//            sb.append("<td>" + studyDTO.getStudyNumber() + "</td>");
//            sb.append("<td></td>");
//            sb.append("<td></td>");
//            sb.append("<td></td>");
//            sb.append("</tr>");
//            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
//                StudyWithBodyRegionPM pm = new StudyWithBodyRegionPM();
//                pm.setStudyId(studyDTO.getId());
//                pm.setStudyUid(studyDTO.getInstanceUid());
//                pm.setStudyNumberInList(studyDTO.getNumberInList());
//                pm.setSeriesId(seriesDTO.getId());
//                pm.setSeriesUid(seriesDTO.getInstanceUid());
//                pm.setSeriesNumberInList(seriesDTO.getNumberInList());
//                pm.setModalityId(seriesDTO.getModality().getId());
//                pm.setModalityName(seriesDTO.getModality().getName());
//                pm.setHospitalBodyRegionId(seriesDTO.getHospitalBodyRegion().getId());
//                pm.setHospitalBodyRegionName(seriesDTO.getHospitalBodyRegion().getEnglishName());
//                if (seriesDTO.getRadiologistBodyRegion() != null) {
//                    pm.setRadiologistBodyRegionId(seriesDTO.getRadiologistBodyRegion().getId());
//                    pm.setRadiologistBodyRegionName(seriesDTO.getRadiologistBodyRegion().getEnglishName());
//                }
//                sb.append("<tr>");
//                sb.append("<td></td>");
//                sb.append("<td>" + pm.getModalityName() + "</td>");
//                sb.append("<td>" + pm.getHospitalBodyRegionName() + "</td>");
//                sb.append("<td>" + pm.getSeriesNumber() + "</td>");
//                sb.append("</tr>");
//                studyWithBodyRegionList.add(pm);
//            }
//        }
//
//        Collections.sort(studyWithBodyRegionList, new BodyRegionOfStudyComparator());
//        String studyGroup = "";
//        String modalityGroup = "";
//        String studyWithModalityGroup = "";
//        List<SeriesGroup> seriesGroups = new ArrayList<SeriesGroup>();
//        for (StudyWithBodyRegionPM studyWithBodyRegionPM : studyWithBodyRegionList) {
//            SeriesGroup sg = new SeriesGroup(
//                    studyWithBodyRegionPM.getStudyUid(),
//                    studyWithBodyRegionPM.getModalityName(),
//                    studyWithBodyRegionPM.getHospitalBodyRegionName());
//            if (!seriesGroups.contains(sg)) {
//                seriesGroups.add(sg);
//            } else {
//                seriesGroups.get(seriesGroups.indexOf(sg)).setSeriesNumber(
//                        seriesGroups.get(seriesGroups.indexOf(sg)).getSeriesNumber() + 1);
//            }
//        }
////        for (int i = 0; i < studyWithBodyRegionList.size(); i++) {
////            StudyWithBodyRegionPM pm = studyWithBodyRegionList.get(i);
////            SeriesGroup sg = new SeriesGroup(pm.getStudyUid(), pm.getModalityName(), pm.getHospitalBodyRegionName());
////            if (!seriesGroups.contains(sg)) {
////                seriesGroups.add(sg);
////            }
//////            if (!studyWithModalityGroup.equals(pm.getStudyIdWithHospitalModalityName())) {
//////                studyWithModalityGroup = pm.getStudyIdWithHospitalModalityName();
//////                modalityGroup = pm.getModalityName();
//////                //studyGroup = pm.getStudyId();
//////            } else {
//////                if (studyGroup.equals(pm.getStudyId())) {
//////                    pm.setStudyId("");
//////                } else {
//////                    //studyGroup = pm.getStudyId();
//////                }
//////                if (modalityGroup.equals(pm.getModalityName())) {
//////                    pm.setModalityName("");
//////                } else {
//////                    modalityGroup = pm.getModalityName();
//////                }
//////            }
////        }
//
//
////        for (int i = 0; i < studyWithBodyRegionList.size(); i++) {
////            StudyWithBodyRegionPM pm = studyWithBodyRegionList.get(i);
////            sb.append("<tr>");
////            sb.append("<td>" + pm.getStudyId() + "</td>");
////            sb.append("<td>" + pm.getModalityName() + "</td>");
////            sb.append("<td>" + pm.getHospitalBodyRegionName() + "</td>");
////            sb.append("</tr>");
////        }
////        for (SeriesGroup seriesGroup : seriesGroups) {
////
////            if (!seriesGroup.getStudy().equals(studyGroup)) {
////                sb.append("<tr>");
////                sb.append("<td>" + seriesGroup.getStudy() + "</td>");
////                sb.append("<td></td>");
////                sb.append("<td></td>");
////                sb.append("<td></td>");
////                sb.append("</tr>");
////            }
////            sb.append("<tr>");
////            sb.append("<td></td>");
////            sb.append("<td>" + seriesGroup.getModality() + "</td>");
////            sb.append("<td>" + seriesGroup.getBodyRegion() + "</td>");
////            sb.append("<td>" + seriesGroup.getSeriesNumber() + "</td>");
////            sb.append("</tr>");
////        }
//        sb.append("</body></html>");
//        //setBodyRegions(sb.toString());
    }

    public ObservableList<CaseFlagPM> getCaseFlagPMList() {
        return caseFlagPMList;
    }

    public void setCaseFlagPMList(ObservableList<CaseFlagPM> caseFlagPMList) {
        this.caseFlagPMList = caseFlagPMList;
    }

    public Action getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(Action cancelAction) {
        this.cancelAction = cancelAction;
    }

    public Action getVerifyAction() {
        return verifyAction;
    }

    public void setVerifyAction(Action verifyAction) {
        this.verifyAction = verifyAction;
    }

    public boolean isSaveButtonEnabled() {
        return saveButtonEnabled;
    }

    public void setSaveButtonEnabled(boolean saveButtonEnabled) {
        boolean oldValue = this.saveButtonEnabled;
        this.saveButtonEnabled = saveButtonEnabled;
        propertyChangeSupport.firePropertyChange("saveButtonEnabled", oldValue, this.saveButtonEnabled);
    }

    private void createTree() {
        CaseDTO mainCase = CaseDownloader.getInstance().getTdsMainCase();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(mainCase.getTdsCaseUniqueId());

        for (StudyDTO studyDTO : mainCase.getReferralInfoList().get(0).getStudyList()) {
            DefaultMutableTreeNode studyNode = new DefaultMutableTreeNode(studyDTO.getInstanceUid());
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                boolean modalityAdded = false;
                int modalityIndex = 0;
                for (int i = 0; i < studyNode.getChildCount(); i++) {
                    if (((DefaultMutableTreeNode) studyNode.getChildAt(i)).getUserObject().equals(seriesDTO.getModality().getName())) {
                        modalityAdded = true;
                        modalityIndex = i;
                        break;
                    }
                }
                if (!modalityAdded) {
                    DefaultMutableTreeNode modalityNode = new DefaultMutableTreeNode(seriesDTO.getModality().getName());
                    DefaultMutableTreeNode bodyRegionNode =
                            new DefaultMutableTreeNode(new BodyRegionWithNumber(seriesDTO.getHospitalBodyRegion().getEnglishName()));
                    modalityNode.add(bodyRegionNode);
                    studyNode.add(modalityNode);
                } else {
                    DefaultMutableTreeNode modalityNode = (DefaultMutableTreeNode) studyNode.getChildAt(modalityIndex);
                    for (int i = 0; i < modalityNode.getChildCount(); i++) {
                        if (((BodyRegionWithNumber) ((DefaultMutableTreeNode) modalityNode.getChildAt(i)).getUserObject()).getBodyRegion().equals(seriesDTO.getHospitalBodyRegion().getEnglishName())) {
                            ((BodyRegionWithNumber) ((DefaultMutableTreeNode) modalityNode.getChildAt(i)).getUserObject()).increaseNumber();
                            break;
                        }
                    }
                }

            }
            rootNode.add(studyNode);
        }

        bodyRegionTree = new javax.swing.tree.DefaultTreeModel(rootNode);
    }

    public DefaultTreeModel getBodyRegionTree() {
        return bodyRegionTree;
    }

    public void setBodyRegionTree(DefaultTreeModel bodyRegionTree) {
        this.bodyRegionTree = bodyRegionTree;
    }

    public Action getModifyBodyRegionAction() {
        return modifyBodyRegionAction;
    }

    public void setModifyBodyRegionAction(Action modifyBodyRegionAction) {
        this.modifyBodyRegionAction = modifyBodyRegionAction;
    }

    public ObservableList<StudyWithBodyRegionPM> getStudyWithBodyRegionList() {
        return studyWithBodyRegionList;
    }

    public void setStudyWithBodyRegionList(ObservableList<StudyWithBodyRegionPM> studyWithBodyRegionList) {
        this.studyWithBodyRegionList = studyWithBodyRegionList;
    }

    public ObservableList<ScenarioInstanceDTO> getScenarioInstanceList() {
        return scenarioInstanceList;
    }

    public void setScenarioInstanceList(ObservableList<ScenarioInstanceDTO> scenarioInstanceList) {
        this.scenarioInstanceList = scenarioInstanceList;
    }

    public String getBodyRegions() {
        return bodyRegions;
    }

    public void setBodyRegions(String bodyRegions) {
        String oldValue = this.bodyRegions;
        this.bodyRegions = bodyRegions;
        propertyChangeSupport.firePropertyChange("bodyRegions", oldValue, this.bodyRegions);
    }

    public Action getOpenTroubleShootAction() {
        return openTroubleShootAction;
    }

    public void setOpenTroubleShootAction(Action openTroubleShootAction) {
        this.openTroubleShootAction = openTroubleShootAction;
    }

    public boolean isIsSaving() {
        return isSaving;
    }

    public void setIsSaving(boolean isSaving) {
        this.isSaving = isSaving;
    }

    public String getDataProcLogEntry() {
        return dataProcLogEntry;
    }

    public void setDataProcLogEntry(String dataProcLogEntry) {
        String oldValue = this.dataProcLogEntry;
        this.dataProcLogEntry = dataProcLogEntry;
        propertyChangeSupport.firePropertyChange("dataProcLogEntry", oldValue, this.dataProcLogEntry);
    }
}
