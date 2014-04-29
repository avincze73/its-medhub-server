/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pm;

import base.BaseDTO;
import common.dto.TDSServiceDTO;
import java.util.ArrayList;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class TDSServicePM extends BaseDTO {

    private TDSServiceDTO tdsService;
    private ObservableList<RolePM> rolePMList;

    public TDSServicePM(long id) {
        super(id);
        rolePMList = ObservableCollections.observableList(new ArrayList<RolePM>());
    }

    public TDSServicePM() {
        rolePMList = ObservableCollections.observableList(new ArrayList<RolePM>());
    }

    public TDSServicePM(TDSServiceDTO tdsService, ObservableList<RolePM> rolePMList) {
        this.tdsService = tdsService;
        this.rolePMList = rolePMList;
        //rolePMList = ObservableCollections.observableList(new ArrayList<RolePM>());
    }

    public ObservableList<RolePM> getRolePMList() {
        return rolePMList;
    }

    public void setRolePMList(ObservableList<RolePM> rolePMList) {
        ObservableList<RolePM> oldValue = this.rolePMList;
        this.rolePMList = rolePMList;
        propertyChangeSupport.firePropertyChange("rolePMList", oldValue, this.rolePMList);
    }

    public TDSServiceDTO getTdsService() {
        return tdsService;
    }

    public void setTdsService(TDSServiceDTO tdsService) {
        TDSServiceDTO oldValue = this.tdsService;
        this.tdsService = tdsService;
        propertyChangeSupport.firePropertyChange("tdsService", oldValue, this.tdsService);
    }
}
