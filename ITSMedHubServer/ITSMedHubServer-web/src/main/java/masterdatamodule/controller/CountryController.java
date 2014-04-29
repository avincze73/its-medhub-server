/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.DisplayMode;
import common.service.EntityService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Currency;
import masterdatamodule.service.CountryService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class CountryController extends MasterDataControllerBase<Country> {

    @EJB
    private CountryService service;

    public CountryController() {
        super(Country.class);
    }

    @Override
    protected EntityService<Country> getService() {
        return service;
    }

    @Override
    public void insert() {
        super.insert();
        getSelectedEntity().setCurrency(new Currency());
    }
    
    
}
