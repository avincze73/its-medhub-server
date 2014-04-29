/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.ITSLanguage;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.WayOfClosing;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class RegLicQualTypeService extends MasterDataServiceBase<RegLicQualType> {

    public RegLicQualTypeService() {
        super(RegLicQualType.class);
    }
}
