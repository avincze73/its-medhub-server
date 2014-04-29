/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import masterdatamodule.entity.Currency;
import masterdatamodule.service.CurrencyService;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class TestController implements Serializable, Cloneable {

    @EJB
    private CurrencyService service;
    protected List<Currency> entityList;
    protected Currency selectedEntity;

    public void save()  {
        System.out.println("save");
    }
    
    
    @PostConstruct
    public void init() {
        if (entityList == null) {
            setEntityList(service.getList());
            if (!entityList.isEmpty()) {
                setSelectedEntity(entityList.get(7));
            }
        }
    }

    public List<Currency> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Currency> entityList) {
        this.entityList = entityList;
    }

    public Currency getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Currency selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
}
