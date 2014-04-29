/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.downloading;

import casemodule.dto.CaseDTO;
import casemodule.dto.ReferralInfoDTO;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import java.util.List;
import masterdatamodule.dto.ScenarioDTO;
import pm.CaseFlagPM;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ReportingDTO;

/**
 *
 * @author vincze.attila
 */
public abstract class CaseDownloader {

    protected CaseDTO tdsMainCase;
    protected CaseDTO tdsCaseReference1;
    protected CaseDTO tdsCaseReference2;
    protected CaseDTO tdsCaseNext;

    protected CaseDownloader() {
    }

    public static synchronized CaseDownloader getInstance() {
        //return RemoteCaseDownloader.getInstance();
        return LocalCaseDownloader.getInstance();
    }

    public abstract CaseDTO loadCase(String caseId, boolean dataOnly);

    public abstract void loadCase();

    public abstract void startScenario(ScenarioInstanceDTO dto);

    public abstract void deactivateScenario(ScenarioInstanceDTO dto);

    public abstract List<ScenarioDTO> getScenarioList();

    public abstract void saveReport();

    public abstract void doneReport();

    public abstract void confirmReport(List<SeriesDTO> seriesList, String note);

    public abstract String getReportText();

    public abstract List<CaseFlagPM> getCaseFlagPMList();

    public abstract void closeCase(String note);

    public abstract void saveComment(CommentDTO dto);

    public ReportingDTO getReporting() {
        return tdsMainCase.getReportingList().get(0);
    }

    public CaseDTO getTdsCaseNext() {
        return tdsCaseNext;
    }

    public CaseDTO getTdsCaseReference1() {
        return tdsCaseReference1;
    }

    public CaseDTO getTdsCaseReference2() {
        return tdsCaseReference2;
    }

    public CaseDTO getTdsMainCase() {
        return tdsMainCase;
    }

    public SeriesDTO getSeriesDTO(String seriesInstanceUid){
        SeriesDTO ret = null;
        for (StudyDTO studyDTO : tdsMainCase.getReferralInfoList().get(0).getStudyList()) {
            for (SeriesDTO seriesDTO : studyDTO.getSeriesList()) {
                if (seriesInstanceUid.equals(seriesDTO.getInstanceUid())){
                    return seriesDTO;
                }
            }
        }
        return ret;
    }
}
