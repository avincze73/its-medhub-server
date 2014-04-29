/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.EventObject;

/**
 *
 * @author vincze.attila
 */
public class UpdateHospitalInListEvent extends EventObject {

    public UpdateHospitalInListEvent(Object source) {
        super(source);
    }
}
