/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.service;

import java.util.List;
import javax.ejb.Remote;
import reportingmodule.dto.AutoFunctionLogEntryDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface AutoFunctionLogServiceRemote {

    List<AutoFunctionLogEntryDTO> getList();
}
