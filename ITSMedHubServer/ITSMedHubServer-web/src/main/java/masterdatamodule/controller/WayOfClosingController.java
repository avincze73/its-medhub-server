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
import masterdatamodule.entity.WayOfClosing;
import masterdatamodule.service.BodyRegionService;
import masterdatamodule.service.CaseStatusService;
import masterdatamodule.service.WayOfClosingService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class WayOfClosingController extends MasterDataControllerBase<WayOfClosing> {

    @EJB
    private WayOfClosingService service;

    public WayOfClosingController() {
        super(WayOfClosing.class);
    }

    @Override
    protected EntityService<WayOfClosing> getService() {
        return service;
    }
}
