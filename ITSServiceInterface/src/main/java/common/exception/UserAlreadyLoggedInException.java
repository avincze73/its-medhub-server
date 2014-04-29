/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.exception;

import common.dto.ClientIdentifierDTO;

/**
 *
 * @author vincze.attila
 */
public class UserAlreadyLoggedInException extends Exception {

    private ClientIdentifierDTO clientIdentifierDTO;
    private String loginName;

    public UserAlreadyLoggedInException(ClientIdentifierDTO clientIdentifierDTO, String loginName) {
        this.clientIdentifierDTO = clientIdentifierDTO;
        this.loginName = loginName;
    }

    public ClientIdentifierDTO getClientIdentifierDTO() {
        return clientIdentifierDTO;
    }

    public void setClientIdentifierDTO(ClientIdentifierDTO clientIdentifierDTO) {
        this.clientIdentifierDTO = clientIdentifierDTO;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
