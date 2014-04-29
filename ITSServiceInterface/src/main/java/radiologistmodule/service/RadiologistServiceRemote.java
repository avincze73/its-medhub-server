/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.service;

import base.IRemoteService;
import java.util.List;
import javax.ejb.Remote;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.SuperVisionDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.dto.WorkPlaceDTO;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RadiologistServiceRemote extends IRemoteService<TDSRadiologistDTO> {
    List<TDSRadiologistDTO> getDraftRadiogistList();
    long saveSuperVision(SuperVisionDTO dto);
    List<CompanyDTO> getCompanyList();
    long saveCompanyAssignment(CompanyAssignmentDTO dto);
    long saveCompany(CompanyDTO dto);
    long saveWorkPlace(WorkPlaceDTO dto);
}
