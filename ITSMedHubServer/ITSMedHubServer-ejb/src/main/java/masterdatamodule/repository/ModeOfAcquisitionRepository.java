/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.ModeOfAcquisition;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class ModeOfAcquisitionRepository extends AbstractRepository<ModeOfAcquisition> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModeOfAcquisitionRepository() {
        super(ModeOfAcquisition.class);
    }

//    @Override
//    public ModeOfAcquisition findByName(String name){
//        String strJQL = "select moa from ModeOfAcquisition moa "
//                + "where moa.name like :name";
//        return em.createQuery(strJQL, ModeOfAcquisition.class).setParameter("name", name).getSingleResult();
//    }

}
