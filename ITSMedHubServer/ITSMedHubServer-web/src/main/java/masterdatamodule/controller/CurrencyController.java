/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.service.EntityService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.Currency;
import masterdatamodule.service.CurrencyService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class CurrencyController extends MasterDataControllerBase<Currency> {

    @EJB
    private CurrencyService service;

    public CurrencyController() {
        super(Currency.class);
    }

    @Override
    protected EntityService<Currency> getService() {
        return service;
    }
}
