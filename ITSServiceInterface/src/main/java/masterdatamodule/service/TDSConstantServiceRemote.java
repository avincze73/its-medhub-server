/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import masterdatamodule.dto.TDSConstantDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface TDSConstantServiceRemote extends MasterDataService<TDSConstantDTO> {

    List<TDSConstantDTO> getAll();

    void save(List<TDSConstantDTO> dtos);
}
