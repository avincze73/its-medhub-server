/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.service.CaseStatusService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class CaseStatusController extends MasterDataControllerBase<CaseStatus> {

    @EJB
    private CaseStatusService service;

    public CaseStatusController() {
        super(CaseStatus.class);
    }

    @Override
    protected EntityService<CaseStatus> getService() {
        return service;
    }
}
