/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import base.ViewModelBase;
import casemodule.downloading.CaseDownloader;
import casemodule.dto.ScenarioInstanceDTO;
import event.DeactivateScenarioEvent;
import event.DeactivateScenarioEventListener;
import event.ITSEventManager;
import event.StartScenarioEvent;
import event.StartScenarioEventListener;
import iview.ITroubleshootView;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import masterdatamodule.dto.ScenarioDTO;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import view.DataProcLogDialog;
import worker.SimpleITSWorker;

/**
 *
 * @author vincze.attila
 */
public class TroubleshootViewModel extends ViewModelBase implements StartScenarioEventListener,
        DeactivateScenarioEventListener {

    private ObservableList<ScenarioDTO> scenarioList;
    private ObservableList<ScenarioInstanceDTO> scenarioInstanceList;
    private ITroubleshootView view;

    public TroubleshootViewModel(ITroubleshootView view) {
        super(null);
        this.view = view;
        scenarioList = ObservableCollections.observableList(new ArrayList<ScenarioDTO>());
        scenarioInstanceList = ObservableCollections.observableList(new ArrayList<ScenarioInstanceDTO>());
        load();

        ITSEventManager.getEventListenerList().add(StartScenarioEventListener.class, this);
        ITSEventManager.getEventListenerList().add(DeactivateScenarioEventListener.class, this);
    }

    private void load() {
        new SimpleITSWorker((JDialog) view) {

            @Override
            protected void doTask() {
                scenarioInstanceList.addAll(CaseDownloader.getInstance().getTdsMainCase().getScenarioInstanceList());
                scenarioList.addAll(CaseDownloader.getInstance().getScenarioList());
            }
        }.execute();
    }

    public ObservableList<ScenarioDTO> getScenarioList() {
        return scenarioList;
    }

    public void setScenarioList(ObservableList<ScenarioDTO> scenarioList) {
        this.scenarioList = scenarioList;
    }

    public ObservableList<ScenarioInstanceDTO> getScenarioInstanceList() {
        return scenarioInstanceList;
    }

    public void setScenarioInstanceList(ObservableList<ScenarioInstanceDTO> scenarioInstanceList) {
        this.scenarioInstanceList = scenarioInstanceList;
    }

    public void eventOccured(StartScenarioEvent evt) {
        String note = "";
        DataProcLogDialog dialog = new DataProcLogDialog(new javax.swing.JFrame(), true, "Comment to scenario start");
        if (dialog.getReturnStatus() == DataProcLogDialog.RET_OK) {
            note = dialog.getNote();
        }

        System.out.println(evt.getSource().toString());
        String[] array = evt.getSource().toString().split(";");

        ScenarioDTO scenarioDTO = scenarioList.get(scenarioList.indexOf(new ScenarioDTO(Integer.parseInt(array[0]))));
        scenarioDTO.setStarted(true);

        ScenarioInstanceDTO scenarioInstanceDTO = new ScenarioInstanceDTO();
        scenarioInstanceDTO.setScenario(scenarioDTO);
        scenarioInstanceDTO.setAdded(new Date());
        System.out.println(scenarioInstanceDTO.getAdded());
        if (!"".equals(note)) {
            scenarioInstanceDTO.setNote(note);
        }
        System.out.println(scenarioInstanceDTO.getNote());
        scenarioInstanceList.add(scenarioInstanceDTO);

        CaseDownloader.getInstance().startScenario(scenarioInstanceDTO);

    }

    public void eventOccured(DeactivateScenarioEvent event) {
        String note = "";
        DataProcLogDialog dialog = new DataProcLogDialog(new javax.swing.JFrame(), true, "Megjegyzés a scenario deaktiválásához");
        if (dialog.getReturnStatus() == DataProcLogDialog.RET_OK) {
            note = dialog.getNote();
        }

        System.out.println(event.getSource().toString());
        String[] array = event.getSource().toString().split(";");

        ScenarioDTO scenarioDTO = scenarioList.get(scenarioList.indexOf(new ScenarioDTO(Integer.parseInt(array[0]))));
        scenarioDTO.setStarted(false);

        ScenarioInstanceDTO dto = null;

        for (ScenarioInstanceDTO scenarioInstanceDTO : scenarioInstanceList) {
            if (scenarioInstanceDTO.getScenario().getId() == scenarioDTO.getId() && scenarioInstanceDTO.getDeactivated() == null) {
                scenarioInstanceDTO.setDeactivated(new Date());
                if (!"".equals(note)) {
                    scenarioInstanceDTO.setNote(scenarioInstanceDTO.getNote() + " -- " + note);
                }
                dto = scenarioInstanceDTO;
            }
        }

        CaseDownloader.getInstance().deactivateScenario(dto);
    }
}
