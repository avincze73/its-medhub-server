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
public class StartScenarioEvent extends EventObject {

    public StartScenarioEvent(Object source) {
        super(source);
    }
}
