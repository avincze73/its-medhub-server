/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.RegLicQual;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RegLicQualRepository extends AbstractRepository<RegLicQual> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegLicQualRepository() {
        super(RegLicQual.class);
    }

}
