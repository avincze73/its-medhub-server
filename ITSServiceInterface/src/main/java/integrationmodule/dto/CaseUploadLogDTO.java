/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class CaseUploadLogDTO extends BaseDTO {

    private Date uploadDate;
    private String uploadType;
    private boolean succeeded;
    private String patientId;
    private String patientName;
    private List<IntegrationHandledStudyDTO> studyList;

    public CaseUploadLogDTO(long id) {
        super(id);
        studyList = new ArrayList<IntegrationHandledStudyDTO>();
    }

    public CaseUploadLogDTO() {
        this(0);
    }

    @Override
    public CaseUploadLogDTO clone() throws CloneNotSupportedException {
        CaseUploadLogDTO result = (CaseUploadLogDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseUploadLogDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public List<IntegrationHandledStudyDTO> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<IntegrationHandledStudyDTO> studyList) {
        this.studyList = studyList;
    }
}
