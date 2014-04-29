/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class ReferralInfoDTO extends BaseDTO {

    private HistoryCaseDTO historyCaseItBelongsTo;
    private CaseDTO caseItBelongsTo;
    private HISReferralDTO hisReferral;
    private RISReferralDTO risReferral;
    private ElectronicReferralDTO electronicReferral;
    private ImageReferralDTO imageReferral;
    private List<StudyDTO> studyList;

    public ReferralInfoDTO(long id) {
        super(id);
        studyList = new ArrayList<StudyDTO>();
    }

    public ReferralInfoDTO() {
        this(0);
    }

    public ElectronicReferralDTO getElectronicReferral() {
        return electronicReferral;
    }

    public void setElectronicReferral(ElectronicReferralDTO electronicReferral) {
        ElectronicReferralDTO oldValue = this.electronicReferral;
        this.electronicReferral = electronicReferral;
        propertyChangeSupport.firePropertyChange("electronicReferral", oldValue, this.electronicReferral);
    }

    public HISReferralDTO getHisReferral() {
        return hisReferral;
    }

    public void setHisReferral(HISReferralDTO hisReferral) {
        HISReferralDTO oldValue = this.hisReferral;
        this.hisReferral = hisReferral;
        propertyChangeSupport.firePropertyChange("hisReferral", oldValue, this.hisReferral);
    }

    public ImageReferralDTO getImageReferral() {
        return imageReferral;
    }

    public void setImageReferral(ImageReferralDTO imageReferral) {
        ImageReferralDTO oldValue = this.imageReferral;
        this.imageReferral = imageReferral;
        propertyChangeSupport.firePropertyChange("imageReferral", oldValue, this.imageReferral);
    }

    public RISReferralDTO getRisReferral() {
        return risReferral;
    }

    public void setRisReferral(RISReferralDTO risReferral) {
        RISReferralDTO oldValue = this.risReferral;
        this.risReferral = risReferral;
        propertyChangeSupport.firePropertyChange("risReferral", oldValue, this.risReferral);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public List<StudyDTO> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<StudyDTO> studyList) {
        List<StudyDTO> oldValue = this.studyList;
        this.studyList = studyList;
        propertyChangeSupport.firePropertyChange("studyList", oldValue, this.studyList);
    }

    public HistoryCaseDTO getHistoryCaseItBelongsTo() {
        return historyCaseItBelongsTo;
    }

    public void setHistoryCaseItBelongsTo(HistoryCaseDTO historyCaseItBelongsTo) {
        HistoryCaseDTO oldValue = this.historyCaseItBelongsTo;
        this.historyCaseItBelongsTo = historyCaseItBelongsTo;
        propertyChangeSupport.firePropertyChange("historyCaseItBelongsTo", oldValue, this.historyCaseItBelongsTo);
    }
}
