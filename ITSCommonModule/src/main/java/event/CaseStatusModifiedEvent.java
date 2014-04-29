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
public class CaseStatusModifiedEvent extends EventObject {

    private long caseId;
    private String caseStatus;

    public CaseStatusModifiedEvent(Object source) {
        super(source);

    }

    public CaseStatusModifiedEvent(Object source, long caseId, String caseStatus) {
        super(source);
        this.caseId = caseId;
        this.caseStatus = caseStatus;
    }

    public long getCaseId() {
        return caseId;
    }

    public void setCaseId(long caseId) {
        this.caseId = caseId;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }
}
