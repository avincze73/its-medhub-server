/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import base.ViewModelBase;
import iview.IModifyBodyRegionView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.service.BodyRegionServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import casemodule.helper.StudyWithBodyRegionPM;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import worker.SimpleITSWorker;

/**
 *
 * @author vincze.attila
 */
public class ModifyBodyRegionViewModel extends ViewModelBase {

    private final IModifyBodyRegionView view;
    protected List<BodyRegionDTO> allBodyRegions;
    private ObservableList<StudyWithBodyRegionPM> bodyRegionOfStudyList;
    private Action saveAction;
    private Action cancelAction;
    private boolean saving;

    public ModifyBodyRegionViewModel(final IModifyBodyRegionView view) {
        super(null);
        this.view = view;
        allBodyRegions = new ArrayList<BodyRegionDTO>();
        bodyRegionOfStudyList = ObservableCollections.observableList(new ArrayList<StudyWithBodyRegionPM>());
        loadBodyRegions();

        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                saving = true;
                view.close();
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                saving = false;
                view.close();
            }
        };

    }

    private void loadBodyRegions() {
        new SimpleITSWorker((JFrame) view) {

            @Override
            protected void doTask() {
                try {
                    BodyRegionServiceRemote service = (BodyRegionServiceRemote) new InitialContext().lookup("masterdatamodule.service.BodyRegionServiceRemote");
                    List<BodyRegionDTO> res = service.getList();
                    allBodyRegions.addAll(res);
                } catch (NamingException ex) {
                    Logger.getLogger(ModifyBodyRegionViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
//        try {
//            BodyRegionServiceRemote service = (BodyRegionServiceRemote) new InitialContext().lookup("masterdatamodule.service.BodyRegionServiceRemote");
//            List<BodyRegionDTO> res = service.getList();
//            allBodyRegions.addAll(res);
//        } catch (NamingException ex) {
//            Logger.getLogger(ModifyBodyRegionViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void loadBodyRegionOfStudies(List<StudyWithBodyRegionPM> list) {
        for (StudyWithBodyRegionPM bodyRegionOfStudyPM : list) {
            bodyRegionOfStudyPM.setRadiologistBodyRegionId(bodyRegionOfStudyPM.getHospitalBodyRegionId());
            bodyRegionOfStudyPM.setRadiologistBodyRegionName(bodyRegionOfStudyPM.getHospitalBodyRegionName());
        }
        bodyRegionOfStudyList.clear();
        bodyRegionOfStudyList.addAll(list);
    }

    public List<BodyRegionDTO> getAllBodyRegions() {
        return allBodyRegions;
    }

    public void setAllBodyRegions(List<BodyRegionDTO> allBodyRegions) {
        this.allBodyRegions = allBodyRegions;
    }

    public ObservableList<StudyWithBodyRegionPM> getBodyRegionOfStudyList() {
        return bodyRegionOfStudyList;
    }

    public void setBodyRegionOfStudyList(ObservableList<StudyWithBodyRegionPM> bodyRegionOfStudyList) {
        this.bodyRegionOfStudyList = bodyRegionOfStudyList;
    }

    public Action getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(Action cancelAction) {
        this.cancelAction = cancelAction;
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }
}
