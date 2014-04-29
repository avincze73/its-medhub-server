/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.CaseDTO;
import casemodule.entity.TDSCase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class HcCaseService implements HcCaseServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public byte[] viewReport(long caseId) {
        TDSCase entity = em.find(TDSCase.class, caseId);
        if (entity.getReportFirstViewed() == null) {
            entity.setReportFirstViewed(new Date());
            em.merge(entity);
        }
        return entity.getFinalizedReport();
    }

    @Override
    public byte[] downloadReport(long caseId) {
        TDSCase entity = em.find(TDSCase.class, caseId);
        if (entity.getReportFirstDownloaded() == null) {
            entity.setReportFirstDownloaded(new Date());
            em.merge(entity);
        }
        return entity.getFinalizedReport();
    }

    @Override
    public List<CaseDTO> getCaseList() {
        List<CaseDTO> dtos = new ArrayList<CaseDTO>();
        List<TDSCase> items = em.createNamedQuery("TDSCase.findAll", TDSCase.class).getResultList();
        for (TDSCase item : items) {
            dtos.add(CaseAssembler.toHcDTO(item));
        }
        return dtos;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
