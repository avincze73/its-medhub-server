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
public class OpenCaseEvent extends EventObject {

    public static enum type {

        all, caseData
    };
    private String openType;

    public OpenCaseEvent(Object source) {
        super(source);
    }

    public OpenCaseEvent(Object source, String openType) {
        super(source);
        this.openType = openType;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }
}
