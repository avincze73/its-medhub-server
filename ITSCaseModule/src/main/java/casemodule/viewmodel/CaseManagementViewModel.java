/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.ViewModelBase;
import casemodule.dto.CaseDTO;
import casemodule.iview.ICaseManagementView;
import casemodule.service.CaseServiceRemote;
import casemodule.view.SelectRadiologistView;
import commonmodule.view.TDSGlassPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import worker.SimpleTDSWorker;

/**
 *
 * @author vincze.attila
 */
public class CaseManagementViewModel extends ViewModelBase {

    private ICaseManagementView view;
    private ObservableList<CaseDTO> assignedCaseList;
    private ObservableList<CaseDTO> normalWaitingCaseList;
    private ObservableList<CaseDTO> urgentWaitingCaseList;
    private CaseDTO selectedAssignedCase;
    private CaseDTO selectedNormalWaitingCase;
    private CaseDTO selectedUrgentWaitingCase;
    private Integer selectedAssignedCaseIndex;
    private Integer selectedNormalWaitingCaseIndex;
    private Integer selectedUrgentWaitingCaseIndex;
    private Action takenAction;
    private Action takenAwayAction;
    private Action assignFromNormalWaitingAction;
    private Action assignFromUrgentWaitingAction;
    private Action assignAction;

    public CaseManagementViewModel(final ICaseManagementView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseManagementBundle"));
        this.view = view;
        assignedCaseList = ObservableCollections.observableList(new ArrayList<CaseDTO>());
        normalWaitingCaseList = ObservableCollections.observableList(new ArrayList<CaseDTO>());
        urgentWaitingCaseList = ObservableCollections.observableList(new ArrayList<CaseDTO>());
        loadCases();

        takenAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedAssignedCase == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, "Taken?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }
                new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        ((JInternalFrame) view).setGlassPane(new TDSGlassPane());
                        ((JInternalFrame) view).getGlassPane().setVisible(true);
                        CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                        service.takenCase(selectedAssignedCase.getId());
                        loadCases();
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
                            ((JInternalFrame) view).getGlassPane().setVisible(false);
                        }

                    }
                }.execute();


//                try {
//                    CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
//                    service.takenCase(selectedAssignedCase.getId());
//                    List<CaseDTO> res = service.getNormalWaitingCases();
//                    normalWaitingCaseList.clear();
//                    normalWaitingCaseList.addAll(res);
//                    assignedCaseList.remove(selectedAssignedCase);
//                    if (!assignedCaseList.isEmpty()) {
//                        setSelectedAssignedCaseIndex(0);
//                    }
//                    if (!normalWaitingCaseList.isEmpty()) {
//                        setSelectedNormalWaitingCaseIndex(0);
//                    }
//                    if (!urgentWaitingCaseList.isEmpty()) {
//                        setSelectedUrgentWaitingCaseIndex(0);
//                    }
//                } catch (NamingException ex) {
//                    Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        };

        takenAwayAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedAssignedCase == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, "Taken away?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }
                JFrame frame = new JFrame();
                frame.setSize(((JInternalFrame) view).getSize());
                frame.setLocation(((JInternalFrame) view).getLocation());
                final SelectRadiologistView selectRadiologistView = new SelectRadiologistView(frame, true);
                if (selectRadiologistView.getViewModel().getRadiologistId() != 0) {
                    new SimpleTDSWorker((JInternalFrame) view) {

                        @Override
                        protected void doTask() {
                            try {
                                CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                                service.takenAwayCase(selectedAssignedCase.getId(), selectRadiologistView.getViewModel().getRadiologistId());
                                loadCases();
                            } catch (NamingException ex) {
                                Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.execute();
                }
            }
        };

        assignFromNormalWaitingAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedNormalWaitingCase == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, "Assign from waiting - normal?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                JFrame frame = new JFrame();
                frame.setSize(((JInternalFrame) view).getSize());
                frame.setLocation(((JInternalFrame) view).getLocation());
                final SelectRadiologistView selectRadiologistView = new SelectRadiologistView(frame, true);
                if (selectRadiologistView.getViewModel().getRadiologistId() != 0) {
                     new SimpleTDSWorker((JInternalFrame) view) {

                        @Override
                        protected void doTask() {
                            try {
                                CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                                service.assignCase(selectedNormalWaitingCase.getId(), selectRadiologistView.getViewModel().getRadiologistId());
                                loadCases();
                            } catch (NamingException ex) {
                                Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.execute();
//                    CaseServiceRemote service;
//                    try {
//                        service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
//                        service.assignCase(selectedNormalWaitingCase.getId(),
//                                selectRadiologistView.getViewModel().getRadiologistId());
//                        loadCases();
//                    } catch (NamingException ex) {
//                        Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            }
        };

        assignAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedNormalWaitingCaseIndex == null || selectedNormalWaitingCaseIndex == -1) {
                    assignFromUrgentWaitingAction.actionPerformed(e);
                } else {
                    assignFromNormalWaitingAction.actionPerformed(e);
                }
            }
        };

        assignFromUrgentWaitingAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedUrgentWaitingCase == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, "Assign from urgent - normal?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                JFrame frame = new JFrame();
                frame.setSize(((JInternalFrame) view).getSize());
                frame.setLocation(((JInternalFrame) view).getLocation());
                final SelectRadiologistView selectRadiologistView = new SelectRadiologistView(frame, true);
                if (selectRadiologistView.getViewModel().getRadiologistId() != 0) {
                    new SimpleTDSWorker((JInternalFrame) view) {

                        @Override
                        protected void doTask() {
                            try {
                                CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
                                service.assignCase(selectedUrgentWaitingCase.getId(), selectRadiologistView.getViewModel().getRadiologistId());
                                loadCases();
                            } catch (NamingException ex) {
                                Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }.execute();
//                    CaseServiceRemote service;
//                    try {
//                        service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
//                        service.assignCase(selectedUrgentWaitingCase.getId(),
//                                selectRadiologistView.getViewModel().getRadiologistId());
//                        loadCases();
//
//                    } catch (NamingException ex) {
//                        Logger.getLogger(CaseManagementViewModel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            }
        };
    }

    private void loadCases() {
        try {
            setSelectedAssignedCaseIndex(null);
            setSelectedNormalWaitingCaseIndex(null);
            setSelectedUrgentWaitingCaseIndex(null);
            CaseServiceRemote service = (CaseServiceRemote) new InitialContext().lookup("casemodule.service.CaseServiceRemote");
            List<CaseDTO> res = service.getNotWaitingCases();
            assignedCaseList.clear();
            assignedCaseList.addAll(res);
            if (!assignedCaseList.isEmpty()) {
                setSelectedAssignedCaseIndex(0);
            }

            res = service.getNormalWaitingCases();
            normalWaitingCaseList.clear();
            normalWaitingCaseList.addAll(res);
            if (!normalWaitingCaseList.isEmpty()) {
                setSelectedNormalWaitingCaseIndex(0);
            }

            res = service.getUrgentWaitingCases();
            urgentWaitingCaseList.clear();
            urgentWaitingCaseList.addAll(res);
            if (!urgentWaitingCaseList.isEmpty()) {
                setSelectedUrgentWaitingCaseIndex(0);
            }
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<CaseDTO> getAssignedCaseList() {
        return assignedCaseList;
    }

    public void setAssignedCaseList(ObservableList<CaseDTO> assignedCaseList) {
        this.assignedCaseList = assignedCaseList;
    }

    public ObservableList<CaseDTO> getNormalWaitingCaseList() {
        return normalWaitingCaseList;
    }

    public void setNormalWaitingCaseList(ObservableList<CaseDTO> normalWaitingCaseList) {
        this.normalWaitingCaseList = normalWaitingCaseList;
    }

    public ObservableList<CaseDTO> getUrgentWaitingCaseList() {
        return urgentWaitingCaseList;
    }

    public void setUrgentWaitingCaseList(ObservableList<CaseDTO> urgentWaitingCaseList) {
        this.urgentWaitingCaseList = urgentWaitingCaseList;
    }

    public CaseDTO getSelectedAssignedCase() {
        return selectedAssignedCase;
    }

    public void setSelectedAssignedCase(CaseDTO selectedAssignedCase) {
        this.selectedAssignedCase = selectedAssignedCase;
    }

    public CaseDTO getSelectedNormalWaitingCase() {
        return selectedNormalWaitingCase;
    }

    public void setSelectedNormalWaitingCase(CaseDTO selectedNormalWaitingCase) {
        this.selectedNormalWaitingCase = selectedNormalWaitingCase;
    }

    public CaseDTO getSelectedUrgentWaitingCase() {
        return selectedUrgentWaitingCase;
    }

    public void setSelectedUrgentWaitingCase(CaseDTO selectedUrgentWaitingCase) {
        this.selectedUrgentWaitingCase = selectedUrgentWaitingCase;
    }

    public Action getTakenAction() {
        return takenAction;
    }

    public void setTakenAction(Action takenAction) {
        this.takenAction = takenAction;
    }

    public Action getTakenAwayAction() {
        return takenAwayAction;
    }

    public void setTakenAwayAction(Action takenAwayAction) {
        this.takenAwayAction = takenAwayAction;
    }

    public Action getAssignFromNormalWaitingAction() {
        return assignFromNormalWaitingAction;
    }

    public void setAssignFromNormalWaitingAction(Action assignFromNormalWaitingAction) {
        this.assignFromNormalWaitingAction = assignFromNormalWaitingAction;
    }

    public Action getAssignFromUrgentWaitingAction() {
        return assignFromUrgentWaitingAction;
    }

    public void setAssignFromUrgentWaitingAction(Action assignFromUrgentWaitingAction) {
        this.assignFromUrgentWaitingAction = assignFromUrgentWaitingAction;
    }

    public Integer getSelectedAssignedCaseIndex() {
        return selectedAssignedCaseIndex;
    }

    public void setSelectedAssignedCaseIndex(Integer selectedAssignedCaseIndex) {
        Integer oldValue = this.selectedAssignedCaseIndex;
        this.selectedAssignedCaseIndex = selectedAssignedCaseIndex;
        propertyChangeSupport.firePropertyChange("selectedAssignedCaseIndex", oldValue, this.selectedAssignedCaseIndex);
    }

    public Integer getSelectedNormalWaitingCaseIndex() {
        return selectedNormalWaitingCaseIndex;
    }

    public void setSelectedNormalWaitingCaseIndex(Integer selectedNormalWaitingCaseIndex) {
        Integer oldValue = this.selectedNormalWaitingCaseIndex;
        this.selectedNormalWaitingCaseIndex = selectedNormalWaitingCaseIndex;
        propertyChangeSupport.firePropertyChange("selectedNormalWaitingCaseIndex", oldValue, this.selectedNormalWaitingCaseIndex);
        setSelectedUrgentWaitingCaseIndex(-1);
    }

    public Integer getSelectedUrgentWaitingCaseIndex() {
        return selectedUrgentWaitingCaseIndex;
    }

    public void setSelectedUrgentWaitingCaseIndex(Integer selectedUrgentWaitingCaseIndex) {
        Integer oldValue = this.selectedUrgentWaitingCaseIndex;
        this.selectedUrgentWaitingCaseIndex = selectedUrgentWaitingCaseIndex;
        propertyChangeSupport.firePropertyChange("selectedUrgentWaitingCaseIndex", oldValue, this.selectedUrgentWaitingCaseIndex);
        if (selectedUrgentWaitingCaseIndex != null && selectedUrgentWaitingCaseIndex == -1) {
            return;
        }
        setSelectedNormalWaitingCaseIndex(-1);
    }

    public Action getAssignAction() {
        return assignAction;
    }

    public void setAssignAction(Action assignAction) {
        this.assignAction = assignAction;
    }
}
