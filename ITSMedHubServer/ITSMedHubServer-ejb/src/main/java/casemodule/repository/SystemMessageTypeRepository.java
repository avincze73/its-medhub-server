/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import systemmodule.entity.SystemMessageType;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class SystemMessageTypeRepository extends AbstractRepository<SystemMessageType> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemMessageTypeRepository() {
        super(SystemMessageType.class);
    }
}
