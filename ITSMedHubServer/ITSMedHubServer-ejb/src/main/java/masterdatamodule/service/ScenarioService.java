/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.Scenario;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ScenarioService extends MasterDataServiceBase<Scenario> {

    public ScenarioService() {
        super(Scenario.class);
    }
}
