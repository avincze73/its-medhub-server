/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common.service;

import common.assembler.CommonAssembler;
import common.dto.TDSServiceDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import systemmodule.entity.TDSService;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class TDSServiceService implements TDSServiceServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;


    @Override
    public List<TDSServiceDTO> findAll() {
        List<TDSServiceDTO> dtos = new ArrayList<TDSServiceDTO>();
        List<TDSService> items = em.createNamedQuery("TDSService.findAll", TDSService.class).getResultList();
        for (TDSService item : items) {
            dtos.add(CommonAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public TDSServiceDTO find(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long save(TDSServiceDTO dto) {
        TDSService item = CommonAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
