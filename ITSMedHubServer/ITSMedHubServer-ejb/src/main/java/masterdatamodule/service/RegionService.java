/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.Region;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class RegionService extends MasterDataServiceBase<Region> {

    public RegionService() {
        super(Region.class);
    }
}
