/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogEntryDTO;
import casemodule.entity.DataProcLogEntry;
import casemodule.entity.TDSCase;
import casemodule.repository.TDSCaseRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import reportingmodule.service.ReportingService;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class McCaseService implements McCaseServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "caseService")
    private CaseService caseService;
    @EJB(name = "caseRepository")
    private TDSCaseRepository caseRepository;
    @EJB(name = "reportingService")
    private ReportingService reportingService;

    @Override
    public List<CaseDTO> getCaseList() {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
        List<TDSCase> items = em.createNamedQuery("TDSCase.findAll", TDSCase.class).getResultList();
        for (TDSCase item : items) {
            dtos.add(CaseAssembler.toMcDTO(item));
        }
        return dtos;
    }

    @Override
    public CaseDTO getCase(long id) {
        CaseDTO ret = null;
        TDSCase entity = em.createNamedQuery("TDSCase.findById", TDSCase.class).setParameter("id", id).getSingleResult();
        if ("ready".equals(entity.getCaseStatus().getEnglishName())) {
            caseService.setCaseStatus(id, "transmitted");
        }
        ret = CaseAssembler.toDTO(entity, true);
        return ret;
    }

    @Override
    public List<CaseDTO> getCaseListByCaseStatus(String englishName) {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
        List<TDSCase> items = new ArrayList<TDSCase>();
        if (englishName == null || "".equals(englishName)) {
            items = em.createNamedQuery("TDSCase.findAll", TDSCase.class).getResultList();
        } else {
            items = em.createQuery("SELECT t FROM TDSCase t WHERE t.caseStatus.englishName = :englishName", TDSCase.class).setParameter("englishName", englishName).getResultList();
        }
        for (TDSCase item : items) {
            dtos.add(CaseAssembler.toDTO(item, true));
        }
        return dtos;
    }

    @Override
    public List<CaseDTO> getWaitingCaseList() {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
        List<TDSCase> entities = caseRepository.getWaitingCaseList();
        for (TDSCase tDSCase : entities) {
            dtos.add(CaseAssembler.toDTO(tDSCase, true));
        }
        return dtos;
    }

    @Override
    public List<DataProcLogEntryDTO> getDataProcLogEntryList(long caseId) {
        List<DataProcLogEntryDTO> dtos = new ArrayList<DataProcLogEntryDTO>();
        List<DataProcLogEntry> entities =
                em.createQuery("SELECT d FROM DataProcLogEntry d WHERE d.dataProcLog.tDSCase.id = :id", DataProcLogEntry.class).setParameter("id", caseId).getResultList();
        for (DataProcLogEntry dataProcLogEntry : entities) {
            dtos.add(CaseAssembler.toDTO(dataProcLogEntry));
        }
        return dtos;
    }

    @Override
    public void rejectCase(long caseId) {
        reportingService.rejectedByTDS(caseId);
    }

    @Override
    public List<DataProcLogEntryDTO> getDataProcLogEntryList() {
        List<DataProcLogEntryDTO> dtos = new ArrayList<DataProcLogEntryDTO>();
        List<DataProcLogEntry> entities =
                em.createNamedQuery("DataProcLogEntry.findAll", DataProcLogEntry.class).getResultList();
        for (DataProcLogEntry dataProcLogEntry : entities) {
            dtos.add(CaseAssembler.toDTO(dataProcLogEntry));
        }
        return dtos;
    }
}
