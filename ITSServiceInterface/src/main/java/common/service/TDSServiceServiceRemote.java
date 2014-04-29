/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import common.dto.TDSServiceDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface TDSServiceServiceRemote {

    List<TDSServiceDTO> findAll();

    TDSServiceDTO find(long id);

    long save(TDSServiceDTO dto);

    void delete(long id);
}
