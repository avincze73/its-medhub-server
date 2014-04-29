/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.WayOfClosing;

/**
 *
 * @author root
 */
@Stateless
public class WayOfClosingRepository extends AbstractRepository<WayOfClosing> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WayOfClosingRepository() {
        super(WayOfClosing.class);
    }

    @Override
    public WayOfClosing findSingleByName(String name) {
        String strJQL = "select wc from WayOfClosing wc "
                + "where wc.name = :name";
        return em.createNamedQuery("WayOfClosing.findByName", WayOfClosing.class).setParameter("name", name).getSingleResult();
    }

}
