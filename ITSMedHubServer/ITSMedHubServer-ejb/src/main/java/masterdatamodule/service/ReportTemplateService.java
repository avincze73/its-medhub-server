/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import casemodule.entity.ReportTemplate;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.dto.ReportTemplateDTO;
import masterdatamodule.repository.ReportTemplateRepository;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class ReportTemplateService implements ReportTemplateServiceRemote {

    @EJB(name = "repository")
    private ReportTemplateRepository repository;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    @Override
    public List<ReportTemplateDTO> getList() {
        List<ReportTemplateDTO> dtos = new ArrayList<ReportTemplateDTO>();
        List<ReportTemplate> items = repository.getList();
        for (ReportTemplate item : items) {
            dtos.add(MasterDataAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public long save(ReportTemplateDTO dto) {
        ReportTemplate item = MasterDataAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            repository.create(item);
            res = item.getId();
        } else {
            repository.edit(item);
        }
        return res;
    }

    @Override
    public ReportTemplateDTO find(long id) {
        ReportTemplate item = repository.find(id);
        return MasterDataAssembler.toDTO(item);
    }
}
