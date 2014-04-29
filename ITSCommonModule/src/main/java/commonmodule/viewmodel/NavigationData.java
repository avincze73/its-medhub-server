/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.viewmodel;

import javax.swing.JInternalFrame;

/**
 *
 * @author vincze.attila
 */
public class NavigationData {

    private String navigationPath;
    private String mainTitle;
    private String detailTitle;
    private String progressText;
    private JInternalFrame openedFrame;

    public NavigationData(String navigationPath, String mainTitle, String detailTitle,
            String progressText, JInternalFrame openedFrame) {
        this.navigationPath = navigationPath;
        this.mainTitle = mainTitle;
        this.detailTitle = detailTitle;
        this.progressText = progressText;
        this.openedFrame = openedFrame;
    }


    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public JInternalFrame getOpenedFrame() {
        return openedFrame;
    }

    public void setOpenedFrame(JInternalFrame openedFrame) {
        this.openedFrame = openedFrame;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
    }

    public String getProgressText() {
        return progressText;
    }

    public void setProgressText(String progressText) {
        this.progressText = progressText;
    }

    
}
