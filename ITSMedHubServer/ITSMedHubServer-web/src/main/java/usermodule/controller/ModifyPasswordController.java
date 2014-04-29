/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vincze.attila
 */
@Named
@SessionScoped
public class ModifyPasswordController implements Cloneable, Serializable{

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ModifyPasswordController.class);
    
    private String actualPassword;
    private String newPassword;
    private String newPasswordAgain;

    public void save() {
        log.info("saving");
        log.info(actualPassword);
        log.info(newPassword);
        log.info(newPasswordAgain);
    }
    
    public void clear(){
        setActualPassword("");
        setNewPassword("");
        setNewPasswordAgain("");
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    
}
