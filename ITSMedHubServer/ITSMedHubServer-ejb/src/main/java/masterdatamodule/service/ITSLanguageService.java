/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.ITSLanguage;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ITSLanguageService extends MasterDataServiceBase<ITSLanguage> {

    public ITSLanguageService() {
        super(ITSLanguage.class);
    }
}
