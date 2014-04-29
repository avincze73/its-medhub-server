/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.dto;

import base.BaseDTO;
import casemodule.dto.CaseDTO;
import java.util.Date;
import radiologistmodule.dto.TDSRadiologistDTO;

/**
 *
 * @author vincze.attila
 */
public class CommentDTO extends BaseDTO {

    private String title;
    private Date added;//
    private String finishedAndSignedText;
    private String type;
    private boolean opened;
    private CaseDTO caseItBelongsTo;
    private TDSRadiologistDTO tdsRadiologist;

    public CommentDTO(long id) {
        this(id, null, null);
    }

    public CommentDTO() {
        this(0);
    }

    public CommentDTO(long id, CaseDTO caseItBelongsTo, TDSRadiologistDTO tdsRadiologist) {
        super(id);
        this.caseItBelongsTo = caseItBelongsTo;
        this.tdsRadiologist = tdsRadiologist;
    }

    public CommentDTO(CaseDTO caseItBelongsTo) {
        this(0, caseItBelongsTo, null);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommentDTO)) {
            return false;
        }
        return super.equals(obj);
    }


    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public String getFinishedAndSignedText() {
        return finishedAndSignedText;
    }

    public void setFinishedAndSignedText(String finishedAndSignedText) {
        String oldValue = this.finishedAndSignedText;
        this.finishedAndSignedText = finishedAndSignedText;
        propertyChangeSupport.firePropertyChange("finishedAndSignedText", oldValue, this.finishedAndSignedText);
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        boolean oldValue = this.opened;
        this.opened = opened;
        propertyChangeSupport.firePropertyChange("opened", oldValue, this.opened);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldValue, this.title);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        String oldValue = this.type;
        this.type = type;
        propertyChangeSupport.firePropertyChange("type", oldValue, this.type);
    }


}
