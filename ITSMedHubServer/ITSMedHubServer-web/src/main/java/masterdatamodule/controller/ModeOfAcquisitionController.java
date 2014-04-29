/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.service.ModeOfAcquisitionService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class ModeOfAcquisitionController extends MasterDataControllerBase<ModeOfAcquisition> {

    @EJB
    private ModeOfAcquisitionService service;

    public ModeOfAcquisitionController() {
        super(ModeOfAcquisition.class);
    }

    @Override
    protected EntityService<ModeOfAcquisition> getService() {
        return service;
    }
}
