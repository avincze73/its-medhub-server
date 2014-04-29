/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.EventObject;

/**
 *
 * @author vincze.attila
 */
public class UpdateDetailTitleEvent extends EventObject {

    private String detailTitle;
    private String navitagationPath;

    public UpdateDetailTitleEvent(Object source) {
        super(source);
    }

    public UpdateDetailTitleEvent(Object source, String detailTitle, String navitagationPath) {
        super(source);
        this.detailTitle = detailTitle;
        this.navitagationPath = navitagationPath;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getNavitagationPath() {
        return navitagationPath;
    }

    public void setNavitagationPath(String navitagationPath) {
        this.navitagationPath = navitagationPath;
    }
}
