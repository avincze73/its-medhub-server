/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogEntryType;
import casemodule.dto.SeriesDTO;
import casemodule.entity.ScenarioInstance;
import casemodule.entity.Series;
import casemodule.entity.TDSCase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.Scenario;
import reportingmodule.entity.Reporting;
import reportingmodule.service.ReportingService;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RcCaseService implements RcCaseServiceRemote {

    @Resource
    private SessionContext ctx;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "dataProcLogService")
    private DataProcLogService dataProcLogService;
    @EJB(name = "reportingService")
    private ReportingService reportingService;
    @EJB(name = "systemMessageService")
    private SystemMessageService systemMessageService;

    @Override
    public List<CaseDTO> getCaseList() {
        return getCaseList(false);
    }

    private List<CaseDTO> getCaseList(boolean isClosed) {
        System.out.println("GETCASELIST");
        int suggestedOrder = 0;
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
//        String strJQL = "select r.tDSCase from Reporting r "
//                + "where r.tDSRadiologist.tDSUser.loginName like :loginName "
//                + "and r.active = true and r.tDSCase.caseStatus.englishName " + (isClosed ? "" : "not") + " like \'ready\' "
//                + "order by r.tDSCase.deadLine";
//        //List<TDSCase> items = em.createQuery(strJQL, TDSCase.class).setParameter("loginName", ctx.getCallerPrincipal().getName()).getResultList();
        String strJQL = "select c from TDSCase c ";
        
        List<TDSCase> items = em.createQuery(strJQL, TDSCase.class).getResultList();

        for (TDSCase item : items) {
            CaseDTO dto = null;
            if (!"assigned".equals(item.getCaseStatus().getEnglishName())) {
                dto = CaseAssembler.toDTO(item, true);
                dto.setSuggestedOrder(++suggestedOrder);
                dtos.add(dto);
                dataProcLogService.saveEntry(
                        item.getDataProcLog(),
                        DataProcLogEntryType.FullDisplayInWorkList,
                        "TDSCase", null, item.getTdsCaseUniqueId(), null, null);
            } else {
                dto = CaseAssembler.toAnonymDTO(item);
                dto.setSuggestedOrder(++suggestedOrder);
                dtos.add(dto);
                dataProcLogService.saveEntry(
                        item.getDataProcLog(),
                        DataProcLogEntryType.AnonimizedDisplayInWorkList,
                        "TDSCase", null, item.getTdsCaseUniqueId(), null, null);
            }

        }
        return dtos;
    }

    private boolean isAssigned(TDSCase entity) {
        boolean result = false;
        for (Reporting reporting : entity.getReportingCollection()) {
            if (reporting.getActive() && reporting.getTDSRadiologist().getItsUser().getLoginName().equals(ctx.getCallerPrincipal().getName())) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void closeCase(long caseId, String note) {
        TDSCase entity = em.createNamedQuery("TDSCase.findById", TDSCase.class).setParameter("id", caseId).getSingleResult();
        dataProcLogService.saveEntry(entity.getDataProcLog(), note, "closing case");
    }

    @Override
    public void addDataProcLog(List<Long> caseIds, String note) {
        for (Long id : caseIds) {
            TDSCase entity = em.find(TDSCase.class, id);
            dataProcLogService.saveEntry(entity.getDataProcLog(), note, "");
        }
    }

    @Override
    public byte[] viewReport(long caseId) {
        TDSCase entity = em.find(TDSCase.class, caseId);
        byte[] ret = null;
        if (entity.getFinalizedReport() != null) {
            ret = entity.getFinalizedReport();
        } else {
            for (Reporting reporting : entity.getReportingCollection()) {
                if (reporting.getActive()) {
                    ret = reporting.getUnfinishedText().getBytes();
                }
            }
        }
        return ret;
    }

    @Override
    public void rejectCase(long caseId) {
        ScenarioInstance scenarioInstance = new ScenarioInstance();
        scenarioInstance.setAdded(new Date());
        scenarioInstance.setTDSCase(new TDSCase(caseId));
        Scenario scenario = em.createQuery("select s from Scenario s where s.englishName like :englishName", Scenario.class).setParameter("englishName", "Reject case - not in my knowledge").getSingleResult();
        scenarioInstance.setScenario(scenario);
        em.persist(scenarioInstance);
        systemMessageService.save(4, caseId, 0, scenario.getEnglishName());
        reportingService.reject(caseId);

    }

    @Override
    public void updateBodyRegions(List<SeriesDTO> seriesList) {
        for (SeriesDTO seriesDTO : seriesList) {
            Series entity = em.find(Series.class, seriesDTO.getId());
            BodyRegion bodyRegion = em.createNamedQuery("BodyRegion.findByEnglishName", BodyRegion.class).setParameter("englishName", seriesDTO.getRadiologistBodyRegion().getEnglishName()).getSingleResult();
            entity.setBodyRegion(bodyRegion);
            em.merge(entity);
        }
    }

    @Override
    public List<CaseDTO> getClosedCaseList() {
        return getCaseList(true);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
