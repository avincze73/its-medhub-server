/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.service;

import hospitalmodule.dto.ContactPersonDTO;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ContactPersonServiceRemote {

    long save(ContactPersonDTO item);

    void delete(long id);
    
}
