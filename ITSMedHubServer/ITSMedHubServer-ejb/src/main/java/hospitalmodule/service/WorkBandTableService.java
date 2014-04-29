/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.dto.BandInfoDTO;
import hospitalmodule.dto.BodyRegionWithBandDTO;
import hospitalmodule.dto.WorkBandTableDTO;
import hospitalmodule.entity.HospitalContract;
import hospitalmodule.repository.WorkBandTableRepository;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.Modality;
import radiologistmodule.entity.WorkBandTable;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class WorkBandTableService implements WorkBandTableServiceRemote {

    @EJB(name = "repository")
    private WorkBandTableRepository repository;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public long save(WorkBandTableDTO item) {
        WorkBandTable workBandTable = HospitalAssembler.toEntity(item);
        if (workBandTable.getId() == 0) {
            repository.create(workBandTable);
        } else {
            repository.edit(workBandTable);
        }
        return workBandTable.getId();
    }

    @Override
    public void delete(long id) {
        WorkBandTable workBandTable = repository.find(id);
        repository.remove(workBandTable);
    }

    @Override
    public void save(long hospitalContractid, List<WorkBandTableDTO> dtos) {
        for (WorkBandTableDTO workBandTableDTO : dtos) {
            if (workBandTableDTO.getId() == 0) {
                WorkBandTable wbt = new WorkBandTable();
                wbt.setHospitalContract(new HospitalContract(workBandTableDTO.getHospitalContract().getId()));
                wbt.setModality(new Modality(workBandTableDTO.getModality().getId()));
                em.persist(wbt);
                em.flush();
                workBandTableDTO.setId(wbt.getId());
                for (BandInfoDTO bandInfoDTO : workBandTableDTO.getBandInfoList()) {
                    bandInfoDTO.setWorkBandTable(new WorkBandTableDTO(wbt.getId()));
                }
                for (BodyRegionWithBandDTO bodyRegionWithBandDTO : workBandTableDTO.getBodyRegionWithBandList()) {
                    bodyRegionWithBandDTO.setWorkBandTable(new WorkBandTableDTO(wbt.getId()));
                }
            }

            WorkBandTable workBandTable = em.find(WorkBandTable.class, workBandTableDTO.getId());
            WorkBandTable workBandTableNew = HospitalAssembler.updateEntity(workBandTableDTO, workBandTable);
            em.merge(workBandTableNew);
        }
        //return getByHospitalContract(hospitalContractid);
    }

    @Override
    public List<WorkBandTableDTO> getByHospitalContract(long hospitalContractid) {
        List<WorkBandTableDTO> retDTO = new ArrayList<WorkBandTableDTO>();
        List<WorkBandTable> ret =
                em.createQuery("SELECT w FROM WorkBandTable w WHERE w.hospitalContract.id = :id", WorkBandTable.class)
                .setParameter("id", hospitalContractid).getResultList();
        for (WorkBandTable workBandTable : ret) {
            retDTO.add(HospitalAssembler.toDTO(workBandTable));
        }
        return retDTO;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
