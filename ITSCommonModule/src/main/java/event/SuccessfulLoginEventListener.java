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
public interface SuccessfulLoginEventListener extends EventListener {
    void successfulLoginEventOccured(SuccessfulLoginEvent evt);
}
