/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itsdatabasecreation;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vincze.attila
 */
public class DataUtil {

    public static EntityManagerFactory factory;
    public static EntityManagerFactory factoryGWS;

    static {
        factory = Persistence.createEntityManagerFactory("ITSDatabaseCreationPU");
        factoryGWS = Persistence.createEntityManagerFactory("ITSDatabaseCreationGWSPU");
    }
}
