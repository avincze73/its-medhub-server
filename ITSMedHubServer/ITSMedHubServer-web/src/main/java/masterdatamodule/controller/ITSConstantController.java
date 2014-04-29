/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.ITSConstant;
import masterdatamodule.service.ITSConstantService;

/**
 *
 * @author tds
 */
@Named(value="itsConstantController")
@SessionScoped
public class ITSConstantController extends MasterDataControllerBase<ITSConstant> {

    @EJB
    private ITSConstantService service;

    public ITSConstantController() {
        super(ITSConstant.class);
    }

    @Override
    protected EntityService<ITSConstant> getService() {
        return service;
    }
}
