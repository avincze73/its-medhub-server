/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.ModeOfAcquisition;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ModeOfAcquisitionService extends MasterDataServiceBase<ModeOfAcquisition> {

    public ModeOfAcquisitionService() {
        super(ModeOfAcquisition.class);
    }
}
