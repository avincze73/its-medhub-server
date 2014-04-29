/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.service;

import integrationmodule.dto.CaseUploadAttributeDTO;
import integrationmodule.dto.CaseUploadErrorLogDTO;
import integrationmodule.dto.CaseUploadLogDTO;
import integrationmodule.dto.IntegrationHandledStudyDTO;
import javax.ejb.Remote;

/**
 *
 * @author tdsadmin
 */
@Remote
public interface IntegrationServiceRemote {

    CaseUploadAttributeDTO getCaseUploadAttribute();

    void saveCaseUploadAttribute(CaseUploadAttributeDTO dto);

    CaseUploadErrorLogDTO getCaseUploadErrorLog();

    void saveCaseUploadErrorLog(CaseUploadErrorLogDTO dto);

    CaseUploadLogDTO getCaseUploadLog();

    void saveCaseUploadLog(CaseUploadLogDTO dto);

    IntegrationHandledStudyDTO getIntegrationHandledStudy();

    void saveIntegrationHandledStudy(IntegrationHandledStudyDTO dto);
}
