/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class SystemMessageTypeDTO extends BaseDTO {

    private String messageType;
    private String messageClass;
    private int priority;

    public SystemMessageTypeDTO(long id) {
        super(id);
    }

    public SystemMessageTypeDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SystemMessageTypeDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(String messageClass) {
        String oldValue = this.messageClass;
        this.messageClass = messageClass;
        propertyChangeSupport.firePropertyChange("messageClass", oldValue, this.messageClass);
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        String oldValue = this.messageType;
        this.messageType = messageType;
        propertyChangeSupport.firePropertyChange("messageType", oldValue, this.messageType);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        int oldValue = this.priority;
        this.priority = priority;
        propertyChangeSupport.firePropertyChange("priority", oldValue, this.priority);
    }
}
