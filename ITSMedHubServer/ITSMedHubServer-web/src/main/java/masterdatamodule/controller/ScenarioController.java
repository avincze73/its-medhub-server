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
import masterdatamodule.entity.Scenario;
import masterdatamodule.service.BodyRegionService;
import masterdatamodule.service.CaseStatusService;
import masterdatamodule.service.ScenarioService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class ScenarioController extends MasterDataControllerBase<Scenario> {

    @EJB
    private ScenarioService service;

    public ScenarioController() {
        super(Scenario.class);
    }

    @Override
    protected EntityService<Scenario> getService() {
        return service;
    }
}
