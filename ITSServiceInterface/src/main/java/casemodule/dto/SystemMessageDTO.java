/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;
import radiologistmodule.dto.TDSRadiologistDTO;
import usermodule.dto.UserDTO;

/**
 *
 * @author vincze.attila
 */
public class SystemMessageDTO extends BaseDTO {

    private SystemMessageTypeDTO messageType;
    private UserDTO recipient;
    private boolean emailWasSent;
    private String emailAddress;
    private String messageText;
    private Date sent;
    private CaseDTO relatedCase;
    private TDSRadiologistDTO relatedRadiologist;

    public SystemMessageDTO(long id) {
        super(id);
    }

    public SystemMessageDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SystemMessageDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        String oldValue = this.emailAddress;
        this.emailAddress = emailAddress;
        propertyChangeSupport.firePropertyChange("emailAddress", oldValue, this.emailAddress);
    }

    public boolean isEmailWasSent() {
        return emailWasSent;
    }

    public void setEmailWasSent(boolean emailWasSent) {
        boolean oldValue = this.emailWasSent;
        this.emailWasSent = emailWasSent;
        propertyChangeSupport.firePropertyChange("emailWasSent", oldValue, this.emailWasSent);
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        String oldValue = this.messageText;
        this.messageText = messageText;
        propertyChangeSupport.firePropertyChange("messageText", oldValue, this.messageText);
    }

    public SystemMessageTypeDTO getMessageType() {
        return messageType;
    }

    public void setMessageType(SystemMessageTypeDTO messageType) {
        SystemMessageTypeDTO oldValue = this.messageType;
        this.messageType = messageType;
        propertyChangeSupport.firePropertyChange("messageType", oldValue, this.messageType);
    }

    public UserDTO getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDTO recipient) {
        UserDTO oldValue = this.recipient;
        this.recipient = recipient;
        propertyChangeSupport.firePropertyChange("recipient", oldValue, this.recipient);
    }

    public CaseDTO getRelatedCase() {
        return relatedCase;
    }

    public void setRelatedCase(CaseDTO relatedCase) {
        CaseDTO oldValue = this.relatedCase;
        this.relatedCase = relatedCase;
        propertyChangeSupport.firePropertyChange("relatedCase", oldValue, this.relatedCase);
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        Date oldValue = this.sent;
        this.sent = sent;
        propertyChangeSupport.firePropertyChange("sent", oldValue, this.sent);
    }

    public TDSRadiologistDTO getRelatedRadiologist() {
        return relatedRadiologist;
    }

    public void setRelatedRadiologist(TDSRadiologistDTO relatedRadiologist) {
        TDSRadiologistDTO oldValue = this.relatedRadiologist;
        this.relatedRadiologist = relatedRadiologist;
        propertyChangeSupport.firePropertyChange("relatedRadiologist", oldValue, this.relatedRadiologist);
    }
}
