/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.viewmodel;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.util.logging.Level;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

/**
 *
 * @author vincze.attila
 */
public class TDSEventQueue extends EventQueue {

    @Override
    protected void dispatchEvent(AWTEvent newEvent) {
        try {
            super.dispatchEvent(newEvent);
        } catch (Throwable t) {
            JXErrorPane.showDialog(null, new ErrorInfo("Hiba!",
                    t.getMessage(), null, "ConstraintError", t, Level.ALL, null));
        }
    }
}
