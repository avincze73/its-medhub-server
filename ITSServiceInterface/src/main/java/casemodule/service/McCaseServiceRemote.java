/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogEntryDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface McCaseServiceRemote {

    public List<CaseDTO> getCaseList();

    CaseDTO getCase(long id);

    public List<CaseDTO> getCaseListByCaseStatus(String englishName);

    List<CaseDTO> getWaitingCaseList();

    List<DataProcLogEntryDTO> getDataProcLogEntryList(long caseId);

    void rejectCase(long caseId);

    List<DataProcLogEntryDTO> getDataProcLogEntryList();
}
