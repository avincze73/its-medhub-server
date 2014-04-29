/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.viewmodel;

import base.ViewModelBase;
import casemodule.iview.ICaseView;
import java.util.ResourceBundle;

/**
 *
 * @author vincze.attila
 */
public class CaseViewModel extends ViewModelBase {

    private ICaseView view;
    private String title;
    public CaseViewModel(ICaseView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"));
        this.view = view;
        setTitle("Esetek");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldValue, this.title);
    }

    public ICaseView getView() {
        return view;
    }

    public void setView(ICaseView view) {
        this.view = view;
    }




}
