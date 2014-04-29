/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import masterdatamodule.entity.Country;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class CountryService extends MasterDataServiceBase<Country> {

    public CountryService() {
        super(Country.class);
    }
}
