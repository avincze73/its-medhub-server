/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.CaseDTO;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.ScenarioInstanceDTO;
import common.exception.AuthorizationFailedException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface CaseServiceRemote {

    DicomImageDTO getDicomDataSet(long id);

    DicomImageDTO getDicomDataSetIcon(long id);

    List<CaseDTO> getCaseListByRadiologist(long radiologistId);

    List<CaseDTO> getCaseList() throws TooManyResultsException, ZeroResultException, AuthorizationFailedException;

    CaseDTO getCase(long caseId);

    void viewCase(long caseId);

    public abstract void saveReporting(long reportingId, String unfinishedTest);

    void doneReporting(long reportingId);

    long saveScenarioInstance(ScenarioInstanceDTO dto);

    List<CaseDTO> getWaitingCaseList();

    void setCaseStatus(long caseId, String caseStatus);

    List<CaseDTO>  getAssignedCases();

    List<CaseDTO> getNormalWaitingCases();

    List<CaseDTO>  getUrgentWaitingCases();

    List<CaseDTO>  getNotWaitingCases();

    void takenCase(long caseId);

    void takenAwayCase(long caseId, long radiologistId);

    void assignCase(long caseId, long radiologistId);

    String getReport(long caseId);


}