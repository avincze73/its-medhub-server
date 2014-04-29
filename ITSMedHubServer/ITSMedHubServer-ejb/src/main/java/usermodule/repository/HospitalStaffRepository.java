/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.repository;

import common.AbstractRepository;
import usermodule.entity.HospitalStaff;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class HospitalStaffRepository extends AbstractRepository<HospitalStaff> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalStaffRepository() {
        super(HospitalStaff.class);
    }

    public HospitalStaff findByLoginName(String loginName) {
        String strJQL = "select hs from HospitalStaff hs "
                + "where hs.tDSUser.loginName like :loginName";
        return em.createQuery(strJQL, HospitalStaff.class).setParameter("loginName", loginName).getSingleResult();
    }
}
