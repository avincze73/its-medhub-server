/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itssecurity;

import com.sun.enterprise.security.auth.realm.*;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincze.attila
 */
public class ITSRealm extends IASRealm {

    @Override
    public String getAuthType() {
        return "itsRealm";
    }

    @Override
    public synchronized String getJAASContext() {
        return "itsRealm";
    }

    public boolean loginUser(String userName, String password) {

//        boolean loginSuccessful = false;
//        if ("admin".equals(userName) && "titkos".equals(password)) {
//            loginSuccessful = true;
//        }
//        return loginSuccessful;


        boolean loginSuccessful = false;
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itsmedhub", "root", "titkos");
            PreparedStatement ps = con.prepareStatement("select count(*) from ITSUser where loginName = ? and password = ?");
            ps.setString(1, userName);
            ps.setString(2, SecurityUtil.create().getPasswordHash(password));
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getInt(1) == 1) {
                loginSuccessful = true;
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ITSRealm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ITSRealm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ITSRealm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return loginSuccessful;
    }

    @Override
    protected void init(Properties props) throws BadRealmException, NoSuchRealmException {
        super.init(props);
    }

//    @Override
//    public User getUser(String name) throws NoSuchUserException, BadRealmException {
//        return super.getUser(name);
//    }

    @Override
    public Enumeration getGroupNames(String string) throws InvalidOperationException, NoSuchUserException {

        Vector vector = new Vector();
        //vector.add("Users");
        vector.add("its");
        return vector.elements();

//        Connection con = null;
//        String strSQL = null;
//        Vector<String> roles = new Vector<String>();
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://192.168.10.100:3306/tds", "root", "tds1");
//            strSQL = "select r.name from TDSRole r, RoleAssignment rs, TDSUser u "
//                    + "where u.id = rs.userId and rs.roleId = r.id and u.loginName like ?";
//            PreparedStatement ps = con.prepareStatement(strSQL);
//            //System.out.println(string);
//            ps.setString(1, string);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                roles.add(rs.getString(1));
//            }
//            rs.close();
//            ps.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(TDSRealm.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(TDSRealm.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(TDSRealm.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return roles.elements();
    }
}
