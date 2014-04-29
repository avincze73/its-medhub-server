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
import masterdatamodule.dto.CaseFlagDTO;

/**
 *
 * @author root
 */
@Remote
public interface CaseFlagServiceRemote extends IRemoteService<CaseFlagDTO>{
        List<CaseFlagDTO> findByHungarianNameAndEnglishName(String hungarianName, String englishName)
            throws TooManyResultsException, ZeroResultException;
}
