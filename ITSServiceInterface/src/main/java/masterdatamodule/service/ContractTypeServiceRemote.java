/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.service;

import base.IRemoteService;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;
import masterdatamodule.dto.ContractTypeDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ContractTypeServiceRemote extends IRemoteService<ContractTypeDTO> {
    List<ContractTypeDTO> findByHungarianNameAndEnglishName(String hungarianName, String englishName)
            throws TooManyResultsException, ZeroResultException;
}
