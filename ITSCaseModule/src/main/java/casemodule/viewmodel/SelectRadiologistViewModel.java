/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.ViewModelBase;
import casemodule.iview.ISelectRadiologistView;
import commonmodule.view.TDSGlassPane;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.service.RadiologistServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class SelectRadiologistViewModel extends ViewModelBase {

    private ISelectRadiologistView view;
    private long radiologistId;
    private Action selectAction;
    private ObservableList<TDSRadiologistDTO> radiologistList;
    private TDSRadiologistDTO selectedRadiologist;
    private Integer selectedIndex;

    public SelectRadiologistViewModel(ISelectRadiologistView view) {
        super(null);
        this.view = view;
        radiologistList = ObservableCollections.observableList(new ArrayList<TDSRadiologistDTO>());

        selectAction = new SelectAction();
        loadRadiologists();
    }

    private void loadRadiologists() {
        new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                ((JDialog) view).setGlassPane(new TDSGlassPane());
                ((JDialog) view).getGlassPane().setVisible(true);
                RadiologistServiceRemote service = (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
                List<TDSRadiologistDTO> res = service.findAll();
                radiologistList.clear();
                radiologistList.addAll(res);
                if (!radiologistList.isEmpty()) {
                    setSelectedIndex(0);
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    super.done();
                    get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                } finally {
                    ((JDialog) view).getGlassPane().setVisible(false);
                }

            }
        }.execute();

//
//        try {
//            RadiologistServiceRemote service = (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
//            List<TDSRadiologistDTO> res = service.findAll();
//            radiologistList.clear();
//            radiologistList.addAll(res);
//            if (!radiologistList.isEmpty()) {
//                setSelectedIndex(0);
//            }
//
//        } catch (TooManyResultsException ex) {
//            Logger.getLogger(SelectRadiologistViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ZeroResultException ex) {
//            Logger.getLogger(SelectRadiologistViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public Action getSelectAction() {
        return selectAction;
    }

    public void setSelectAction(Action selectAction) {
        this.selectAction = selectAction;
    }

    private class SelectAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            if (selectedRadiologist != null) {
                radiologistId = selectedRadiologist.getId();
            }
            view.close();
        }
    }

    public ObservableList<TDSRadiologistDTO> getRadiologistList() {
        return radiologistList;
    }

    public void setRadiologistList(ObservableList<TDSRadiologistDTO> radiologistList) {
        this.radiologistList = radiologistList;
    }

    public TDSRadiologistDTO getSelectedRadiologist() {
        return selectedRadiologist;
    }

    public void setSelectedRadiologist(TDSRadiologistDTO selectedRadiologist) {
        this.selectedRadiologist = selectedRadiologist;
    }

    public long getRadiologistId() {
        return radiologistId;
    }

    public void setRadiologistId(long radiologistId) {
        this.radiologistId = radiologistId;
    }

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex) {
        Integer oldValue = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        propertyChangeSupport.firePropertyChange("selectedIndex", oldValue, this.selectedIndex);

    }
}
