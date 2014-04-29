/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.entity.RegLicQual;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.Region;
import masterdatamodule.service.BodyRegionService;
import masterdatamodule.service.CaseStatusService;
import masterdatamodule.service.RegLicQualService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class RegLicQualController extends MasterDataControllerBase<RegLicQual> {

    @EJB
    private RegLicQualService service;

    public RegLicQualController() {
        super(RegLicQual.class);
    }

    @Override
    protected EntityService<RegLicQual> getService() {
        return service;
    }

    @Override
    public void insert() {
        super.insert();
        getSelectedEntity().setRegion(new Region());
        getSelectedEntity().setRegLicQualType(new RegLicQualType());
    }
    
    
}
