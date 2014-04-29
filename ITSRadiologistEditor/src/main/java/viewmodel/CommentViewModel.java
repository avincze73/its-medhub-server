/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodel;

import base.ViewModelBase;
import casemodule.downloading.CaseDownloader;
import casemodule.dto.CaseDTO;
import iview.ICommentView;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;
import worker.SimpleITSWorker;

/**
 *
 * @author vincze.attila
 */
public class CommentViewModel extends ViewModelBase {

    private ICommentView view;
    private CommentDTO comment;
    private ReportingDTO reporting;
    private Action saveAction;
    private Action cancelAction;

    public CommentViewModel(final ICommentView view) {
        super(null);
        this.view = view;
        setComment(new CommentDTO(
                new CaseDTO(CaseDownloader.getInstance().getTdsMainCase().getId())));
        setReporting(CaseDownloader.getInstance().getReporting());
        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Save?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }

                new SimpleITSWorker((JFrame) view) {

                    @Override
                    protected void doTask() {
                        CaseDownloader.getInstance().saveComment(comment);
                        view.close();
                    }
                }.execute();

            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Cancel?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
                view.close();
            }
        };
    }

    public CommentDTO getComment() {
        return comment;
    }

    public final void setComment(CommentDTO comment) {
        CommentDTO oldValue = this.comment;
        this.comment = comment;
        propertyChangeSupport.firePropertyChange("comment", oldValue, this.comment);
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public Action getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(Action cancelAction) {
        this.cancelAction = cancelAction;
    }

    public ReportingDTO getReporting() {
        return reporting;
    }

    public final void setReporting(ReportingDTO reporting) {
        ReportingDTO oldValue = this.reporting;
        this.reporting = reporting;
        propertyChangeSupport.firePropertyChange("reporting", oldValue, this.reporting);
    }
}
