/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginFrame.java
 *
 * Created on 2010.08.02., 11:27:34
 */
package loginmodule.view;

import java.awt.Cursor;
import loginmodule.iview.ILoginView;
import loginmodule.viewmodel.LoginViewModel;

/**
 *
 * @author vincze.attila -Duser.language=en -Duser.country=US
 */
public class LoginView extends javax.swing.JInternalFrame implements ILoginView {

    private LoginViewModel viewModel;

    public LoginViewModel getViewModel() {
        return viewModel;
    }

    /**
     * Creates new form LoginFrame
     */
    public LoginView() {
        viewModel = new LoginViewModel(this);
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).setNorthPane(null);
        createBindings();
        getRootPane().setDefaultButton(btnLogin);
        //UIManager.put("Label.font", new Font("Tahoma",Font.PLAIN,11));

    }

    private void createBindings() {
        org.jdesktop.beansbinding.BindingGroup bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, 
                org.jdesktop.beansbinding.ELProperty.create("${viewModel.userName}"), 
                txtUserName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(
                org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, 
                org.jdesktop.beansbinding.ELProperty.create("${viewModel.password}"), 
                pwfPassword, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        bindingGroup.bind();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        lblCopyright = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblWelcomeMessage = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        pwfPassword = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(232, 238, 247));
        setBorder(null);
        setName("a"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(193, 215, 239));
        jPanel2.setPreferredSize(new java.awt.Dimension(812, 100));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lblCopyright.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("loginmodule/bundle/LoginBundle"); // NOI18N
        lblCopyright.setText(bundle.getString("LoginView.lblCopyright.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel2.add(lblCopyright, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBackground(new java.awt.Color(193, 215, 239));
        jPanel1.setPreferredSize(new java.awt.Dimension(812, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblWelcomeMessage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWelcomeMessage.setText(bundle.getString("LoginView.lblWelcomeMessage.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblWelcomeMessage, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(241, 245, 249));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 157));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        lblUserName.setText(bundle.getString("LoginView.lblUserName.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(lblUserName, gridBagConstraints);

        lblPassword.setText(bundle.getString("LoginView.pwfPassword.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel3.add(lblPassword, gridBagConstraints);

        txtUserName.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        txtUserName.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel3.add(txtUserName, gridBagConstraints);

        btnLogin.setAction(viewModel.getLoginCommand());
        btnLogin.setText(bundle.getString("LoginView.btnLogin.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel3.add(btnLogin, gridBagConstraints);

        pwfPassword.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.gray));
        pwfPassword.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel3.add(pwfPassword, gridBagConstraints);

        jPanel4.add(jPanel3, new java.awt.GridBagConstraints());

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLabel lblWelcomeMessage;
    private javax.swing.JPasswordField pwfPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables

    public void ShowBusyCursor() {
        getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
    }

    public void HideBusyCursor() {
        getContentPane().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    public void initFocus() {
        txtUserName.requestFocus();
    }
}