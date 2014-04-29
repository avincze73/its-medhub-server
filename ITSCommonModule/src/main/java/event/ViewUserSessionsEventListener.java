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
public interface ViewUserSessionsEventListener extends EventListener {

    void eventOccured(ViewUserSessionsEvent event);
}
