/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.CaseDTO;
import casemodule.dto.SeriesDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface RcCaseServiceRemote {

    List<CaseDTO> getCaseList();
    List<CaseDTO> getClosedCaseList();
    void closeCase(long caseId, String note);
    void addDataProcLog(List<Long> caseIds, String note);
    byte[] viewReport(long caseId);
    void rejectCase(long caseId);
    void updateBodyRegions(List<SeriesDTO> seriesList);
}
