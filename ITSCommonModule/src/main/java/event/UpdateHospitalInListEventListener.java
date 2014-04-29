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
public interface UpdateHospitalInListEventListener extends EventListener {

    void eventOccured(UpdateHospitalInListEvent event);
}
