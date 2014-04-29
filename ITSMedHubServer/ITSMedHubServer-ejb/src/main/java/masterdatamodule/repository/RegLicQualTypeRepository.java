/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.RegLicQualType;

/**
 *
 * @author root
 */
@Stateless
public class RegLicQualTypeRepository extends AbstractRepository<RegLicQualType> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegLicQualTypeRepository() {
        super(RegLicQualType.class);
    }

}
