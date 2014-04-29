/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class SessionDTO extends BaseDTO {

    private Date started;
    private Date ended;
    private String endingType;
    private UserDTO tdsUser;
    private String clientHostName;
    private String clientHostIpAddress;
    private String clientHostMacAddress;
    private String tdsApplication;
    private List<SessionLogEntryDTO> entryList;
    transient private ObservableList<SessionLogEntryDTO> obsEntryList;

    public SessionDTO(long id) {
        super(id);
        entryList = new ArrayList<SessionLogEntryDTO>();
        obsEntryList = ObservableCollections.observableList(entryList);
        tdsUser = new UserDTO();
    }

    public SessionDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SessionDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        Date oldValue = this.ended;
        this.ended = ended;
        propertyChangeSupport.firePropertyChange("ended", oldValue, this.ended);
    }

    public String getEndingType() {
        return endingType;
    }

    public void setEndingType(String endingType) {
        String oldValue = this.endingType;
        this.endingType = endingType;
        propertyChangeSupport.firePropertyChange("endingType", oldValue, this.endingType);
    }

    public List<SessionLogEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<SessionLogEntryDTO> entryList) {
        List<SessionLogEntryDTO> oldValue = this.entryList;
        this.entryList = entryList;
        propertyChangeSupport.firePropertyChange("entryList", oldValue, this.entryList);
        this.obsEntryList.clear();
        this.obsEntryList.addAll(entryList);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }

    public UserDTO getTdsUser() {
        return tdsUser;
    }

    public void setTdsUser(UserDTO tdsUser) {
        UserDTO oldValue = this.tdsUser;
        this.tdsUser = tdsUser;
        propertyChangeSupport.firePropertyChange("tdsUser", oldValue, this.tdsUser);
    }

    public String getClientHostIpAddress() {
        return clientHostIpAddress;
    }

    public void setClientHostIpAddress(String clientHostIpAddress) {
        String oldValue = this.clientHostIpAddress;
        this.clientHostIpAddress = clientHostIpAddress;
        propertyChangeSupport.firePropertyChange("clientHostIpAddress", oldValue, this.clientHostIpAddress);
    }

    public String getClientHostMacAddress() {
        return clientHostMacAddress;
    }

    public void setClientHostMacAddress(String clientHostMacAddress) {
        String oldValue = this.clientHostMacAddress;
        this.clientHostMacAddress = clientHostMacAddress;
        propertyChangeSupport.firePropertyChange("clientHostMacAddress", oldValue, this.clientHostMacAddress);
    }

    public String getClientHostName() {
        return clientHostName;
    }

    public void setClientHostName(String clientHostName) {
        String oldValue = this.clientHostName;
        this.clientHostName = clientHostName;
        propertyChangeSupport.firePropertyChange("clientHostName", oldValue, this.clientHostName);
    }

    public String getTdsApplication() {
        return tdsApplication;
    }

    public void setTdsApplication(String tdsApplication) {
        String oldValue = this.tdsApplication;
        this.tdsApplication = tdsApplication;
        propertyChangeSupport.firePropertyChange("tdsApplication", oldValue, this.tdsApplication);
    }

    public ObservableList<SessionLogEntryDTO> getObsEntryList() {
        return obsEntryList;
    }

    public void setObsEntryList(ObservableList<SessionLogEntryDTO> obsEntryList) {
        ObservableList<SessionLogEntryDTO> oldValue = this.obsEntryList;
        this.obsEntryList = obsEntryList;
        propertyChangeSupport.firePropertyChange("obsEntryList", oldValue, this.obsEntryList);
    }
}
