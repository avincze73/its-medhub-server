/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package worker;

import commonmodule.view.ITSGlassPane;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;

/**
 *
 * @author vincze.attila
 */
public abstract class SimpleITSWorker extends SwingWorker {

    JInternalFrame internalFrame;
    JFrame frame;
    JDialog dialog;

    public SimpleITSWorker(JInternalFrame internalFrame) {
        this.internalFrame = internalFrame;
    }

    public SimpleITSWorker(JFrame frame) {
        this.frame = frame;
    }

    public SimpleITSWorker(JDialog dialog) {
        this.dialog = dialog;
    }

    protected abstract void doTask();

    @Override
    protected Object doInBackground() throws Exception {
        if (internalFrame != null) {
            internalFrame.setGlassPane(new ITSGlassPane());
            internalFrame.getGlassPane().setVisible(true);
        } else if (frame != null) {
            frame.setGlassPane(new ITSGlassPane());
            frame.getGlassPane().setVisible(true);
        } else if (dialog != null) {
            dialog.setGlassPane(new ITSGlassPane());
            dialog.getGlassPane().setVisible(true);
        }
        doTask();
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
            if (internalFrame != null) {
                internalFrame.getGlassPane().setVisible(false);
            } else if (frame != null) {
                frame.getGlassPane().setVisible(false);
            } else if (dialog != null) {
                dialog.getGlassPane().setVisible(false);
            }
        }
    }
}
