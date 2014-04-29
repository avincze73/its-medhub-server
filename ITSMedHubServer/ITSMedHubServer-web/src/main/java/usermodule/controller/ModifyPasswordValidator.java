/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import util.CryptographicUtil;

/**
 *
 * @author vincze.attila
 */
@FacesValidator("modifyPasswordValidator")
public class ModifyPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        System.out.println(uic.getAttributes().get("actualPassword"));
        System.out.println(CryptographicUtil.create().getPasswordHash(o.toString()));
        if (!uic.getAttributes().get("actualPassword").equals( CryptographicUtil.create().getPasswordHash(o.toString()) ) ) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid", "invalid"));
        }
    }
}
