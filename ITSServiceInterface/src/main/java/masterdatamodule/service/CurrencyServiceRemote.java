/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.service;

import javax.ejb.Remote;
import masterdatamodule.dto.CurrencyDTO;

/**
 *
 * @author root
 */
@Remote
public interface CurrencyServiceRemote extends MasterDataService<CurrencyDTO> {
   
}
