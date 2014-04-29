/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.medhub.itsapplet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class MacAddressApplet extends javax.swing.JApplet {

    private String macAddress;
    private String hostMacAddress;

    public static String getMacFromInterface(NetworkInterface ni) throws SocketException {
        byte mac[] = ni.getHardwareAddress();

        if (mac != null) {
            StringBuilder macAddress = new StringBuilder("");
            String sep = "";
            for (byte o : mac) {
                macAddress.append(sep).append(String.format("%02X", o));
                sep = ":";
            }
            return macAddress.toString();
        }

        return "";
    }

    public static String getInterfacesJSON() {
        try {
            String macs[] = getInterfaces();

            String sep = "";
            StringBuilder macArray = new StringBuilder("['");
            for (String mac : macs) {
                macArray.append(sep).append(mac);
                sep = "','";
            }
            macArray.append("']");

            return macArray.toString();
        } catch (Exception ex) {
            System.err.println("Exception:: " + ex.getMessage());
        }

        return "[]";
    }

    public static String[] getInterfaces() {
        try {
            Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

            ArrayList<String> result = new ArrayList<String>();
            while (nis.hasMoreElements()) {
                NetworkInterface ni = nis.nextElement();
                if (ni.isUp() && !ni.isLoopback() && !ni.isVirtual()) {
                    String mac = getMacFromInterface(ni);
                    //String str = ni.getDisplayName() + ";" + mac;
                    //result.add(str);
                    result.add(mac);
                    if (nis.hasMoreElements()) {
                        result.add(";");
                    }
                }
            }

            return result.toArray(new String[0]);
        } catch (SocketException ex) {
            System.err.println("SocketException:: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Exception:: " + ex.getMessage());
        }

        return new String[0];
    }

    /**
     * Initializes the applet MacAddressApplet
     */
    @Override
    public void init() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MacAddressApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MacAddressApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MacAddressApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MacAddressApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    String macs[] = getInterfaces();
                    String s = "";
                    for (String mac : macs) {
                        //System.err.println(" Interfaces = " + mac);
                        s += mac;
                    }
                    macAddress = s;
                    //jLabel1.setText(s);


                    //
                    String sMac = "";
                    try {
                        InetAddress address = InetAddress.getLocalHost();
                        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
                        byte[] mac = ni.getHardwareAddress();
                        
                        for (int i = 0; i < mac.length; i++) {
                            sMac = sMac + String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        }
                    } catch (SocketException ex) {
                        Logger.getLogger(MacAddressApplet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnknownHostException ex) {
                        Logger.getLogger(MacAddressApplet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    hostMacAddress = sMac;
                            
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getMac() {

        return macAddress;
    }

    public String getHostMacAddress() {
        return hostMacAddress;
    }

    public static void main(String... args) {
        String macs[] = getInterfaces();

        for (String mac : macs) {
            System.err.println(" Interfaces = " + mac);
        }

        System.err.println(" Interfaces JSON = " + getInterfacesJSON());
    }

    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(616, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
