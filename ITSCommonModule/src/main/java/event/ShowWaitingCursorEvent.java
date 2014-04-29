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
public class ShowWaitingCursorEvent extends EventObject {

    private String message;

    public ShowWaitingCursorEvent(Object source) {
        super(source);
    }

    public ShowWaitingCursorEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
