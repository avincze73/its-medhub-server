/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import common.RemoteMessage;
import common.dto.ClientIdentifierDTO;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.entity.CaseStatus;
import util.CryptographicUtil;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class CaseStatusService extends MasterDataServiceBase<CaseStatus> implements CaseStatusServiceRemote {

    public CaseStatusService() {
        super(CaseStatus.class, "englishName");
    }

    public CaseStatusDTO saveDTO(CaseStatusDTO dto) {
        CaseStatus item = MasterDataAssembler.toEntity(dto);
        CaseStatusDTO ret = MasterDataAssembler.toDTO(item);
        return ret;
    }

    public List<CaseStatusDTO> getListDTO() {
        List<CaseStatusDTO> dtos = new ArrayList<CaseStatusDTO>();
        List<CaseStatus> items = getList();

        for (CaseStatus item : items) {
            dtos.add(MasterDataAssembler.toDTO(item));
        }

        return dtos;
    }

    public CaseStatusDTO findDTO(Long id) {
        CaseStatus item = find(id);
        return MasterDataAssembler.toDTO(item);
    }

    public void delete(Long id) {
        CaseStatus item = find(id);
        remove(item);
    }

    @Override
    public List<CaseStatusDTO> findByHungarianNameAndEnglishName(String hungarianName, String englishName) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoteMessage getList(RemoteMessage input) {
        log.info("CaseStatusService.getList secret entering");
        byte[] ret = CryptographicUtil.create().encrypt(getListDTO());
        log.info("CaseStatusService.getList secret leaving");
        return new RemoteMessage(ret);
    }

    @Override
    public RemoteMessage save(RemoteMessage input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoteMessage find(RemoteMessage input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(RemoteMessage input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
