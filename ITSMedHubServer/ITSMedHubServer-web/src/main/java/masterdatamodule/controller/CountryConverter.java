/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Region;

/**
 *
 * @author tds
 */
@FacesConverter(value = "countryConverter")
@RequestScoped
public class CountryConverter implements Converter, Serializable{

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getCanonicalName());

    @Inject
    private CountryController controller;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        Country result = new Country();
        List<Country> list = (List<Country>) component.getAttributes().get("countryList");
        for (Country entity : list) {
            if (value.equals(entity.getEnglishName())){
                result = entity;
                break;
            }
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Country) value).getEnglishName();
    }
    
}
