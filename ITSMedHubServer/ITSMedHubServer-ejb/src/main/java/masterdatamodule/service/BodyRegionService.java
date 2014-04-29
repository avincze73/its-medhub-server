/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.BodyRegion;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class BodyRegionService extends MasterDataServiceBase<BodyRegion> {

    public BodyRegionService() {
        super(BodyRegion.class);
    }
}
