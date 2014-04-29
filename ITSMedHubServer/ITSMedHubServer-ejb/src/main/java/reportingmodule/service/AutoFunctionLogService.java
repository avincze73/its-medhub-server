/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.service;

import casemodule.entity.AutoFunctionLogEntry;
import casemodule.entity.AutoFunctionType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import reportingmodule.assembler.ReportingAssembler;
import reportingmodule.dto.AutoFunctionLogEntryDTO;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class AutoFunctionLogService implements AutoFunctionLogServiceRemote{

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    public void save(String autoFunctionTypeName) {
        AutoFunctionLogEntry log = new AutoFunctionLogEntry();
        AutoFunctionType functionType = em.createNamedQuery("AutoFunctionType.findByName", AutoFunctionType.class).setParameter("name", autoFunctionTypeName).getSingleResult();
        log.setAdded(new Date());
        log.setAutoFunctionType(functionType);
        em.persist(log);
    }

    @Override
    public List<AutoFunctionLogEntryDTO> getList() {
        List<AutoFunctionLogEntryDTO> dtos = new ArrayList<AutoFunctionLogEntryDTO>();
        List<AutoFunctionLogEntry> entities = em.createNamedQuery("AutoFunctionLogEntry.findAll", AutoFunctionLogEntry.class).getResultList();
        for (AutoFunctionLogEntry autoFunctionLogEntry : entities) {
            dtos.add(ReportingAssembler.toDTO(autoFunctionLogEntry));
        }
        return dtos;
    }
}
