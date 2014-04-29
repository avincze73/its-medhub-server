/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.ITSConstant;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ITSConstantService extends MasterDataServiceBase<ITSConstant> {

    public ITSConstantService() {
        super(ITSConstant.class);
    }
}
