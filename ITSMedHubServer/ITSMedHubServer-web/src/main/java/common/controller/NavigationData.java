/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import java.io.Serializable;

/**
 *
 * @author vincze.attila
 */
public class NavigationData implements Serializable {

    private String navigationPath;
    private String lastPage;
    private String title1;
    private String title2;

    public NavigationData(String navigationPath, String lastPage) {
        this.navigationPath = navigationPath;
        this.lastPage = lastPage;
    }

    public NavigationData(String navigationPath, String lastPage, String title1, String title2) {
        this.navigationPath = navigationPath;
        this.lastPage = lastPage;
        this.title1 = title1;
        this.title2 = title2;
    }

    public NavigationData() {
        this(null, null);
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public String getNavigationPath() {
        return navigationPath;
    }

    public void setNavigationPath(String navigationPath) {
        this.navigationPath = navigationPath;
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
