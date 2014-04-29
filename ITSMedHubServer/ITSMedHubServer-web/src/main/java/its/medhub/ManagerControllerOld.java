/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package its.medhub;

import common.DisplayMode;
import common.ModeHandler;
import its.gws.service.UserServiceProxyRemote;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import usermodule.entity.ITSManager;

/**
 *
 * @author vincze.attila
 */
@Named
@SessionScoped
public class ManagerControllerOld implements Serializable{

    private static Logger log = Logger.getLogger(ManagerControllerOld.class);
    @EJB
    private UserServiceProxyRemote userServiceProxy;
    @Inject
    private ModeHandler modeHandler;
    
    private ITSManager selectedEntity;
    private List<ITSManager> entityList;
    private String title1;
    private String title2;
    private boolean saving;

    public ManagerControllerOld() {
        log.info("hello");
        title1 = "ITS manager";
        title2 = "Dr. x.y";
    }

    public String selectEntity() {
        setSelectedEntity(userServiceProxy.getManager(1));
        return null;
    }

    public void edit() {
        log.info("edit");
        modeHandler.setMode(DisplayMode.Edit);
    }

    public void cancel() {
        log.info(saving);
        modeHandler.setMode(DisplayMode.View);
    }

    public void save() {
        log.info(saving);
        modeHandler.setMode(DisplayMode.View);
    }

    public ModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(ModeHandler modeHandler) {
        this.modeHandler = modeHandler;
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

    public List<ITSManager> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<ITSManager> entityList) {
        this.entityList = entityList;
    }

    public ITSManager getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(ITSManager selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }
    
    
}
