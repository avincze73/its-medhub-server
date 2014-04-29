/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.ViewModelBase;
import radiologistmodule.iview.IRadiologistView;

/**
 *
 * @author vincze.attila
 */
public class RadiologistViewModel extends ViewModelBase {

    private final IRadiologistView view;
    private String title;

    public RadiologistViewModel(IRadiologistView view) {
        super(java.util.ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistBundle"));
        this.view = view;
        //TDSEventManager.getEventListenerList().add(ModifyHospitalTitleEventListener.class, this);
        //title = bundle.getString("hospital");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldValue, this.title);
    }
}
