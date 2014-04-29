/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseUploadAttributeDTO extends BaseDTO {

    private String ipAddress;
    private int port;
    private String aeTitle;
    private String pacsServerIpAddress;
    private int pacsServerPort;
    private String pacsServerAETitle;
    private long associationMessageResponseTimeout;		// millisecodns
    private long serviceMessageResponseTimeout;		// millisecodns
    private long pdataTimeout;		// millisecodns
    private int autoCaseUpdateType;
    private int runPeriod;
    private int minOldStudy;
    private int fixRunHour;
    private int fixRunMinute;

    public CaseUploadAttributeDTO() {
        this(0);
    }

    public CaseUploadAttributeDTO(long id) {
        super(id);
    }

    @Override
    public CaseUploadAttributeDTO clone() throws CloneNotSupportedException {
        CaseUploadAttributeDTO result = (CaseUploadAttributeDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseUploadAttributeDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getAeTitle() {
        return aeTitle;
    }

    public void setAeTitle(String aeTitle) {
        this.aeTitle = aeTitle;
    }

    public long getAssociationMessageResponseTimeout() {
        return associationMessageResponseTimeout;
    }

    public void setAssociationMessageResponseTimeout(long associationMessageResponseTimeout) {
        this.associationMessageResponseTimeout = associationMessageResponseTimeout;
    }

    public int getAutoCaseUpdateType() {
        return autoCaseUpdateType;
    }

    public void setAutoCaseUpdateType(int autoCaseUpdateType) {
        this.autoCaseUpdateType = autoCaseUpdateType;
    }

    public int getFixRunHour() {
        return fixRunHour;
    }

    public void setFixRunHour(int fixRunHour) {
        this.fixRunHour = fixRunHour;
    }

    public int getFixRunMinute() {
        return fixRunMinute;
    }

    public void setFixRunMinute(int fixRunMinute) {
        this.fixRunMinute = fixRunMinute;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getMinOldStudy() {
        return minOldStudy;
    }

    public void setMinOldStudy(int minOldStudy) {
        this.minOldStudy = minOldStudy;
    }

    public String getPacsServerAETitle() {
        return pacsServerAETitle;
    }

    public void setPacsServerAETitle(String pacsServerAETitle) {
        this.pacsServerAETitle = pacsServerAETitle;
    }

    public String getPacsServerIpAddress() {
        return pacsServerIpAddress;
    }

    public void setPacsServerIpAddress(String pacsServerIpAddress) {
        this.pacsServerIpAddress = pacsServerIpAddress;
    }

    public int getPacsServerPort() {
        return pacsServerPort;
    }

    public void setPacsServerPort(int pacsServerPort) {
        this.pacsServerPort = pacsServerPort;
    }

    public long getPdataTimeout() {
        return pdataTimeout;
    }

    public void setPdataTimeout(long pdataTimeout) {
        this.pdataTimeout = pdataTimeout;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRunPeriod() {
        return runPeriod;
    }

    public void setRunPeriod(int runPeriod) {
        this.runPeriod = runPeriod;
    }

    public long getServiceMessageResponseTimeout() {
        return serviceMessageResponseTimeout;
    }

    public void setServiceMessageResponseTimeout(long serviceMessageResponseTimeout) {
        this.serviceMessageResponseTimeout = serviceMessageResponseTimeout;
    }

    
}
