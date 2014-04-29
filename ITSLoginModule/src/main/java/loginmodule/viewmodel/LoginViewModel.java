/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmodule.viewmodel;

import base.ViewModelBase;
import com.sun.appserv.security.ProgrammaticLogin;
import common.dto.ClientIdentifierDTO;
import common.exception.UserAlreadyLoggedInException;
import event.SuccessfulLoginEvent;
import event.ITSEventManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import loginmodule.iview.ILoginView;
import usermodule.service.UserSessionServiceRemote;
import util.CryptographicUtil;

/**
 *
 * @author vincze.attila
 */
public class LoginViewModel extends ViewModelBase {
    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

    private class ButtonActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };

    private String userName;
    private String password;
    private ClientIdentifierDTO clientIdentifierDTO;
    private Action loginCommand;
    private ILoginView view;

    public LoginViewModel(final ILoginView view) {
        super(java.util.ResourceBundle.getBundle("loginmodule/bundle/LoginBundle"));
        this.view = view;

        loginCommand = new AbstractAction(bundle.getString("LoginView.btnLogin.text")) {

            public void actionPerformed(ActionEvent e) {
                
                SwingWorker doLogin = new SwingWorker() {

                    @Override
                    protected Object doInBackground() throws Exception {
                        view.ShowBusyCursor();
                        log.info("itt vagyok a loginmodule-ban");
                        if (userName == null || password == null) {
                            throw new Exception("java.rmi.AccessException");
                        }
                        ProgrammaticLogin pm = new ProgrammaticLogin();
                        pm.login(userName, password.toCharArray());
                        
                        UserSessionServiceRemote service =
                                (UserSessionServiceRemote)
                                new InitialContext().lookup("usermodule.service.UserSessionServiceRemote");
                        
                        service.login(CryptographicUtil.create().encrypt(clientIdentifierDTO));
                        service.login(clientIdentifierDTO);
                        ITSEventManager.fireSuccessfulLoginEvent(new SuccessfulLoginEvent(userName));
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            super.done();
                            get();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(LoginViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            if (ex.getCause() instanceof UserAlreadyLoggedInException) {
                                JOptionPane.showMessageDialog(
                                        (Component) view, ((UserAlreadyLoggedInException) ex.getCause())
                                        .getClientIdentifierDTO().getHostMacAddress());
                            } else if (ex.getMessage().contains("java.rmi.AccessException")) {
                                JOptionPane.showMessageDialog((Component) view, bundle.getString("msgAuthenticationFailed"));
                            } else if (ex.getMessage().contains("Invalid user of")) {
                                JOptionPane.showMessageDialog((Component) view, "Invalid user of " + clientIdentifierDTO.getItsApplication());
                            } else {
                               JOptionPane.showMessageDialog((Component) view, bundle.getString("msgUnknownError") ); 
                            }
                            Logger.getLogger(LoginViewModel.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            view.HideBusyCursor();
                        }
                    }
                };
                doLogin.execute();
            }
        };
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldValue = this.password;
        this.password = password;
        propertyChangeSupport.firePropertyChange("password", oldValue, this.password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        String oldValue = this.userName;
        this.userName = userName;
        propertyChangeSupport.firePropertyChange("userName", oldValue, this.userName);
    }

    public Action getLoginCommand() {
        return loginCommand;
    }

    public void setLoginCommand(Action loginCommand) {
        this.loginCommand = loginCommand;
    }

    public void setClientIdentifierDTO(ClientIdentifierDTO clientIdentifierDTO) {
        this.clientIdentifierDTO = clientIdentifierDTO;
    }
}
