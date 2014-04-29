/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.ITSLanguage;
import masterdatamodule.entity.WayOfClosing;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class WayOfClosingService extends MasterDataServiceBase<WayOfClosing> {

    public WayOfClosingService() {
        super(WayOfClosing.class);
    }
}
