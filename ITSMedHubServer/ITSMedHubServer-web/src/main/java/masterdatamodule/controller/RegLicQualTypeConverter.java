/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.Region;

/**
 *
 * @author tds
 */
@FacesConverter(value = "regLicQualTypeConverter")
@RequestScoped
public class RegLicQualTypeConverter implements Converter, Serializable {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getCanonicalName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info(value);
        if (value == null) return null;
        RegLicQualType result = new RegLicQualType();
        List<RegLicQualType> regLicQualTypeList = (List<RegLicQualType>) component.getAttributes().get("regLicQualTypeList");
        for (RegLicQualType regLicQual : regLicQualTypeList) {
            if (value.equals(regLicQual.getName())){
                result = regLicQual;
                break;
            }
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((RegLicQualType) value).getName();
    }
}
