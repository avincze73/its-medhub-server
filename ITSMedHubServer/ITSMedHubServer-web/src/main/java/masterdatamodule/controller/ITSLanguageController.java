/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.ITSLanguage;
import masterdatamodule.service.ITSLanguageService;

/**
 *
 * @author tds
 */
@Named(value="itsLanguageController")
@SessionScoped
public class ITSLanguageController extends MasterDataControllerBase<ITSLanguage> {

    @EJB
    private ITSLanguageService service;

    public ITSLanguageController() {
        super(ITSLanguage.class);
    }
    
    @Override
    protected EntityService<ITSLanguage> getService() {
        return service;
    }
    
    
}
