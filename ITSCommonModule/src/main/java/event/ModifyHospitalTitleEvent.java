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
public class ModifyHospitalTitleEvent extends EventObject {

    public ModifyHospitalTitleEvent(Object source) {
        super(source);
    }
}
