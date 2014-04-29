/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.CaseFlag;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class CaseFlagFacade extends AbstractFacade<CaseFlag> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CaseFlagFacade() {
        super(CaseFlag.class);
    }

}
