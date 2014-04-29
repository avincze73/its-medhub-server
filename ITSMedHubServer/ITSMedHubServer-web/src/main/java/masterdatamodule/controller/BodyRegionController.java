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
import masterdatamodule.service.BodyRegionService;
import masterdatamodule.service.CaseStatusService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class BodyRegionController extends MasterDataControllerBase<BodyRegion> {

    @EJB
    private BodyRegionService service;

    public BodyRegionController() {
        super(BodyRegion.class);
    }

    @Override
    protected EntityService<BodyRegion> getService() {
        return service;
    }
}
