/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class CaseUploadErrorLogDTO extends BaseDTO {

    private Date added;
    private String exceptionName;
    private String exceptionMessage;
    private String stacktrace;
    private CaseUploadLogDTO caseUploadLog;

    public CaseUploadErrorLogDTO(long id) {
        super(id);
    }

    public CaseUploadErrorLogDTO() {
        this(0);
    }



    @Override
    public CaseUploadErrorLogDTO clone() throws CloneNotSupportedException {
        CaseUploadErrorLogDTO result = (CaseUploadErrorLogDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseUploadErrorLogDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public CaseUploadLogDTO getCaseUploadLog() {
        return caseUploadLog;
    }

    public void setCaseUploadLog(CaseUploadLogDTO caseUploadLog) {
        this.caseUploadLog = caseUploadLog;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }


}
