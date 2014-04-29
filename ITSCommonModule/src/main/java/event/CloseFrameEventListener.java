/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.EventListener;

/**
 *
 * @author vincze.attila
 */
public interface CloseFrameEventListener extends EventListener {

    void eventOccured(CloseFrameEvent event);
}
