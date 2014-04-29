/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itsdatabasecreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincze.attila
 */
public class ITSDatabaseConnection {

    public static ITSDatabaseConnection create() {
        return new ITSDatabaseConnection();
    }

    public void dropTables() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://109.74.51.253:3306", "root", "titkos");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "titkos");
            Statement ps = con.createStatement();
//            ps.executeUpdate("drop table ITSManagerRoleAssignment,"
//                    + "ITSRadiologistRoleAssignment, HospitalStaffRoleAssignment, ITSRole"
//                    + ""  );
            ps.executeUpdate("drop database itsmedhub");
            ps.executeUpdate("create database itsmedhub");
            ps.executeUpdate("drop database itsgws");
            ps.executeUpdate("create database itsgws");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ITSDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ITSDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ITSDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
