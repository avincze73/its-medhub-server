/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vincze.attila
 */
@Named
@SessionScoped
public class MasterDataController implements Serializable, Cloneable {

    protected String title1;
    protected String title2;

    public MasterDataController() {
        title1 = "MASTERDATA";
        title2 = "";
    }

    
    
    public String loadCurrency() {
        return "currency";
    }

    public String loadLanguage() {
        return "language";
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }
}
