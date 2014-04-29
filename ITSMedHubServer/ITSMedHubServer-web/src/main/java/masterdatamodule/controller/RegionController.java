/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Currency;
import masterdatamodule.entity.Region;
import masterdatamodule.service.RegionService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class RegionController extends MasterDataControllerBase<Region> {

    @EJB
    private RegionService service;

    public RegionController() {
        super(Region.class);
    }

    @Override
    protected EntityService<Region> getService() {
        return service;
    }

    @Override
    public void insert() {
        super.insert();
        getSelectedEntity().setCountry(new Country());
        getSelectedEntity().getCountry().setCurrency(new Currency());
        
    }
    
    
}
