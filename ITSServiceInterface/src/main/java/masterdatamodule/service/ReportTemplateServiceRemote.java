/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;
import javax.ejb.Remote;
import masterdatamodule.dto.ReportTemplateDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface ReportTemplateServiceRemote extends MasterDataService<ReportTemplateDTO> {

    List<ReportTemplateDTO> getList();

    long save(ReportTemplateDTO dto);
}
