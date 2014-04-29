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
public class IntegrationHandledStudyDTO extends BaseDTO {

    private String instanceUid;
    private Date studyDateTime;
    private boolean succeeded;
    private int dataSetCount;
    private CaseUploadLogDTO caseUploadLog;

    public IntegrationHandledStudyDTO(long id) {
        super(id);
    }

    public IntegrationHandledStudyDTO() {
        this(0);
    }

    @Override
    public IntegrationHandledStudyDTO clone() throws CloneNotSupportedException {
        IntegrationHandledStudyDTO result = (IntegrationHandledStudyDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntegrationHandledStudyDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getStudyDateTime() {
        return studyDateTime;
    }

    public void setStudyDateTime(Date studyDateTime) {
        this.studyDateTime = studyDateTime;
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public int getDataSetCount() {
        return dataSetCount;
    }

    public void setDataSetCount(int dataSetCount) {
        this.dataSetCount = dataSetCount;
    }

    public CaseUploadLogDTO getCaseUploadLog() {
        return caseUploadLog;
    }

    public void setCaseUploadLog(CaseUploadLogDTO caseUploadLog) {
        this.caseUploadLog = caseUploadLog;
    }
}
