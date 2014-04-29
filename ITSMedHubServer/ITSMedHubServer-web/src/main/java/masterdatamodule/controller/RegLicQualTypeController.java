/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.service.RegLicQualTypeService;
import masterdatamodule.service.CaseStatusService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class RegLicQualTypeController extends MasterDataControllerBase<RegLicQualType> {

    @EJB
    private RegLicQualTypeService service;

    public RegLicQualTypeController() {
        super(RegLicQualType.class);
    }

    @Override
    protected EntityService<RegLicQualType> getService() {
        return service;
    }
}
