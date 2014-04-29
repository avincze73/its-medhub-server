/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.Currency;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class CurrencyService extends MasterDataServiceBase<Currency> {

    public CurrencyService() {
        super(Currency.class);
    }
}