/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;
import masterdatamodule.dto.CaseStatusDTO;

/**
 *
 * @author root
 */
@Remote
//public interface CaseStatusServiceRemote extends IRemoteService<CaseStatusDTO>,  MasterDataService<CaseStatusDTO> {
public interface CaseStatusServiceRemote extends IMasterDataServiceRemoteBase {
    
    List<CaseStatusDTO> findByHungarianNameAndEnglishName(String hungarianName, String englishName)
            throws TooManyResultsException, ZeroResultException;
}
