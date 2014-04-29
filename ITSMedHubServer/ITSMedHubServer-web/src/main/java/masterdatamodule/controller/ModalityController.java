/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.service.ModalityService;
import masterdatamodule.service.CaseStatusService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class ModalityController extends MasterDataControllerBase<Modality> {

    @EJB
    private ModalityService service;

    public ModalityController() {
        super(Modality.class);
    }

    @Override
    protected EntityService<Modality> getService() {
        return service;
    }
}
