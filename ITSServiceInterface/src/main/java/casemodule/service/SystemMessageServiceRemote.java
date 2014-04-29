/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.service;

import casemodule.dto.SystemMessageDTO;
import casemodule.dto.SystemMessageTypeDTO;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface SystemMessageServiceRemote {

    List<SystemMessageDTO> getSystemMessageList(long userId, long systemMessageTypeId)
            throws TooManyResultsException, ZeroResultException;

    List<SystemMessageTypeDTO> getSystemMessageTypeList()
            throws TooManyResultsException, ZeroResultException;

    void save(long userId, long caseId, long radiologistId, String messageText);
    
}
