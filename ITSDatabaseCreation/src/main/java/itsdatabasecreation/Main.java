/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package itsdatabasecreation;

/**
 *
 * @author vincze.attila
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //SessionFactory factory = ITSHibernateUtil.getSessionFactory();
        //MasterDataMigration.Create().Load();
        //MasterDataMigration.Create().serialize();
        //TDSHospitalHibernateUtil.getSessionFactory();
        ITSDatabaseConnection.create().dropTables();
        DataLoader.create().load();
    }

}
