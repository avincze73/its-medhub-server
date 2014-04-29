/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import base.ViewModelBase;
import casemodule.downloading.CaseDownloader;
import event.CaseStatusModifiedEvent;
import event.ITSEventManager;
import iview.IReportEditorView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import masterdatamodule.dto.ReportTemplateDTO;
import masterdatamodule.service.ReportTemplateServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import reportingmodule.dto.ReportingDTO;
import view.ConfirmReportView;
import worker.SimpleITSWorker;

/**
 *
 * @author vincze.attila
 */
public final class ReportEditorViewModel extends ViewModelBase {

    //private String reportText;
    private ReportingDTO reporting;
    private Action saveAction;
    private Action doneAction;
    private Action confirmAction;
    private Action loadDescriptionAction;
    private IReportEditorView view;
    private boolean confirmButtonEnabled;
    private ObservableList<ReportTemplateDTO> reportTemplateList;
    private ReportTemplateDTO selectedReportTemplate;
    private Integer selectedReportTemplateIndex;

    public ReportEditorViewModel(final IReportEditorView view) {
        super(null);
        this.view = view;
        confirmButtonEnabled = true;
        reportTemplateList = ObservableCollections.observableList(new ArrayList<ReportTemplateDTO>());
        //loadReportTemplates();
        setReporting(CaseDownloader.getInstance().getReporting());
        initActions();
    }

    private void initActions() {
        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
//                if (reportText == null || "".equals(reportText)) {
//                    return;
//                }
                if (JOptionPane.showConfirmDialog((Component) view, "Save?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
//                CaseDownloader.getInstance().saveReport();
//                TDSEventManager.fireEvent(
//                        new CaseStatusModifiedEvent(
//                        "",
//                        CaseDownloader.getInstance().getTdsMainCase().getId(),
//                        "in progress"));
                new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        view.showWaitingPane();
                        CaseDownloader.getInstance().saveReport();
                        ITSEventManager.fireEvent(
                                new CaseStatusModifiedEvent(
                                "",
                                CaseDownloader.getInstance().getTdsMainCase().getId(),
                                "in progress"));
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            super.done();
                            get();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ReportEditorViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(ReportEditorViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            view.hideWaitingPane();
                        }
                    }
                }.execute();
            }
        };

        doneAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
//                if (reportText == null || "".equals(reportText)) {
//                    return;
//                }
                if (JOptionPane.showConfirmDialog((Component) view, "Done?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
//                CaseDownloader.getInstance().doneReport();
//                TDSEventManager.fireEvent(
//                        new CaseStatusModifiedEvent(
//                        "",
//                        CaseDownloader.getInstance().getTdsMainCase().getId(),
//                        "done"));

                new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        view.showWaitingPane();
                        CaseDownloader.getInstance().doneReport();
                        ITSEventManager.fireEvent(
                                new CaseStatusModifiedEvent(
                                "",
                                CaseDownloader.getInstance().getTdsMainCase().getId(),
                                "done"));
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            super.done();
                            get();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ReportEditorViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(ReportEditorViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            view.hideWaitingPane();
                        }

                    }
                }.execute();


            }
        };

        confirmAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
//                if (reportText == null || "".equals(reportText)) {
//                    return;
//                }
                ConfirmReportView confirmReportView = new ConfirmReportView((JFrame) view);
                setConfirmButtonEnabled(false);
                confirmReportView.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        setConfirmButtonEnabled(true);

                    }
                });


            }
        };

        loadDescriptionAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Load report template?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                if (reporting.getDescription() != null) {
                    sb.append(reporting.getDescription());
                }
                sb.append(selectedReportTemplate.getDescription());
                reporting.setDescription(sb.toString());
            }
        };
    }

    private void loadReportTemplates() {
        new SimpleITSWorker((JFrame) view) {

            @Override
            protected void doTask() {
                try {
                    ReportTemplateServiceRemote service = (ReportTemplateServiceRemote) new InitialContext().lookup("masterdatamodule.service.ReportTemplateServiceRemote");
                    List<ReportTemplateDTO> res = service.getList();
                    reportTemplateList.addAll(res);
                    if (!reportTemplateList.isEmpty()) {
                        setSelectedReportTemplateIndex(0);
                    }
                } catch (NamingException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public Action getDoneAction() {
        return doneAction;
    }

    public void setDoneAction(Action doneAction) {
        this.doneAction = doneAction;
    }

    public Action getConfirmAction() {
        return confirmAction;
    }

    public void setConfirmAction(Action confirmAction) {
        this.confirmAction = confirmAction;
    }

    public boolean isConfirmButtonEnabled() {
        return confirmButtonEnabled;
    }

    public void setConfirmButtonEnabled(boolean confirmButtonEnabled) {
        boolean oldValue = this.confirmButtonEnabled;
        this.confirmButtonEnabled = confirmButtonEnabled;
        propertyChangeSupport.firePropertyChange("confirmButtonEnabled", oldValue, this.confirmButtonEnabled);
    }

    public ReportingDTO getReporting() {
        return reporting;
    }

    public void setReporting(ReportingDTO reporting) {
        ReportingDTO oldValue = this.reporting;
        this.reporting = reporting;
        propertyChangeSupport.firePropertyChange("reporting", oldValue, this.reporting);
    }

    public ObservableList<ReportTemplateDTO> getReportTemplateList() {
        return reportTemplateList;
    }

    public void setReportTemplateList(ObservableList<ReportTemplateDTO> reportTemplateList) {
        this.reportTemplateList = reportTemplateList;
    }

    public ReportTemplateDTO getSelectedReportTemplate() {
        return selectedReportTemplate;
    }

    public void setSelectedReportTemplate(ReportTemplateDTO selectedReportTemplate) {
        ReportTemplateDTO oldValue = this.selectedReportTemplate;
        this.selectedReportTemplate = selectedReportTemplate;
        propertyChangeSupport.firePropertyChange("selectedReportTemplate", oldValue, this.selectedReportTemplate);
    }

    public Integer getSelectedReportTemplateIndex() {
        return selectedReportTemplateIndex;
    }

    public void setSelectedReportTemplateIndex(Integer selectedReportTemplateIndex) {
        Integer oldValue = this.selectedReportTemplateIndex;
        this.selectedReportTemplateIndex = selectedReportTemplateIndex;
        propertyChangeSupport.firePropertyChange("selectedReportTemplateIndex", oldValue, this.selectedReportTemplateIndex);
    }

    public Action getLoadDescriptionAction() {
        return loadDescriptionAction;
    }

    public void setLoadDescriptionAction(Action loadDescriptionAction) {
        this.loadDescriptionAction = loadDescriptionAction;
    }
}
