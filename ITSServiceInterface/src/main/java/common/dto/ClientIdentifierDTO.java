/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.dto;

import base.BaseDTO;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincze.attila
 */
public class ClientIdentifierDTO extends BaseDTO {

    private String hostName;
    private String hostIpAddress;
    private String hostMacAddress;
    private String itsApplication;
    private String loginName;
    private String sessionId;

    public ClientIdentifierDTO() {
        super();
    }

    public ClientIdentifierDTO(String hostName, String hostIpAddress, String hostMacAddress, String itsApplication) {
        super();
        this.hostName = hostName;
        this.hostIpAddress = hostIpAddress;
        this.hostMacAddress = hostMacAddress;
        this.itsApplication = itsApplication;
    }


    public ClientIdentifierDTO(String tdsApplication) {
        super();
        this.itsApplication = tdsApplication;
        this.hostMacAddress = "";
        try {
            this.hostName = java.net.InetAddress.getLocalHost().getHostName();
            this.hostIpAddress = java.net.InetAddress.getLocalHost().getHostAddress();
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            for (int i = 0; i < mac.length; i++) {
                this.hostMacAddress = this.hostMacAddress + String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
            }
        } catch (SocketException ex) {
            Logger.getLogger(ClientIdentifierDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientIdentifierDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHostIpAddress() {
        return hostIpAddress;
    }

    public void setHostIpAddress(String hostIpAddress) {
        this.hostIpAddress = hostIpAddress;
    }

    public String getHostMacAddress() {
        return hostMacAddress;
    }

    public void setHostMacAddress(String hostMacAddress) {
        this.hostMacAddress = hostMacAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getItsApplication() {
        return itsApplication;
    }

    public void setItsApplication(String tdsApplication) {
        this.itsApplication = tdsApplication;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    
    
    
}
