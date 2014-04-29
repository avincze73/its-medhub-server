/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.dto;

import base.BaseDTO;
import casemodule.dto.ScannedReferralImageDTO;
import casemodule.dto.ScannedReportImageDTO;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class HistoryStudyUploadPackageDTO extends BaseDTO {

    private String studyInstanceUid;
    private String referralText;
    private List<ScannedReferralImageDTO> referralImageList;
    private String reportText;
    private List<ScannedReportImageDTO> reportImageList;

    public HistoryStudyUploadPackageDTO(long id) {
        super(id);
    }

    public HistoryStudyUploadPackageDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HistoryStudyUploadPackageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<ScannedReferralImageDTO> getReferralImageList() {
        return referralImageList;
    }

    public void setReferralImageList(List<ScannedReferralImageDTO> referralImageList) {
        List<ScannedReferralImageDTO> oldValue = this.referralImageList;
        this.referralImageList = referralImageList;
        propertyChangeSupport.firePropertyChange("referralImageList", oldValue, this.referralImageList);
    }

    public String getReferralText() {
        return referralText;
    }

    public void setReferralText(String referralText) {
        String oldValue = this.referralText;
        this.referralText = referralText;
        propertyChangeSupport.firePropertyChange("referralText", oldValue, this.referralText);
    }

    public List<ScannedReportImageDTO> getReportImageList() {
        return reportImageList;
    }

    public void setReportImageList(List<ScannedReportImageDTO> reportImageList) {
        List<ScannedReportImageDTO> oldValue = this.reportImageList;
        this.reportImageList = reportImageList;
        propertyChangeSupport.firePropertyChange("reportImageList", oldValue, this.reportImageList);
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        String oldValue = this.reportText;
        this.reportText = reportText;
        propertyChangeSupport.firePropertyChange("reportText", oldValue, this.reportText);
    }

    public String getStudyInstanceUid() {
        return studyInstanceUid;
    }

    public void setStudyInstanceUid(String studyInstanceUid) {
        String oldValue = this.studyInstanceUid;
        this.studyInstanceUid = studyInstanceUid;
        propertyChangeSupport.firePropertyChange("studyInstanceUid", oldValue, this.studyInstanceUid);
    }
}
