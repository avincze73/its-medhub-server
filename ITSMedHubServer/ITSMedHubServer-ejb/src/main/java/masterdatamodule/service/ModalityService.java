/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.Modality;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ModalityService extends MasterDataServiceBase<Modality> {

    public ModalityService() {
        super(Modality.class);
    }
}
