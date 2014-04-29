/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.HospContrOption;
import masterdatamodule.service.HospContrOptionService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class HospContrOptionController extends MasterDataControllerBase<HospContrOption> {

    @EJB
    private HospContrOptionService service;

    public HospContrOptionController() {
        super(HospContrOption.class);
    }

    @Override
    protected EntityService<HospContrOption> getService() {
        return service;
    }
}
