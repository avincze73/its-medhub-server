/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.controller;

import java.io.Serializable;
import java.util.Stack;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vincze.attila
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MenuController.class);
    private String navigationPath;
    private String title1;
    private String title2;
    private Stack<NavigationData> navigationStack;

    public MenuController() {
        log.info("navigation controller creation...");
        navigationStack = new Stack<NavigationData>();
        navigationStack.push(new NavigationData("", "/pages/main_1"));
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

    public String back() {
        if (navigationStack.size() != 1) {
            navigationStack.pop();
        }
        log.info(navigationStack.peek().getLastPage());
        setNavigationPath(getNavigationStack().peek().getNavigationPath());
        return getNavigationStack().peek().getLastPage() + ".jsf?faces-redirect=true";
    }

    public String managerList() {
        navigationStack.push(new NavigationData("ITS MANAGER LIST", "/pages/userList4_jq"));
        setNavigationPath(navigationStack.peek().getNavigationPath());
        return navigationStack.peek().getLastPage();
    }

    public String userList() {
        navigationStack.push(new NavigationData("USER LIST", "/pages/user/userList", "USER LIST", ""));
        setNavigationPath(navigationStack.peek().getNavigationPath());
        setTitle1(navigationStack.peek().getTitle1());
        setTitle2(navigationStack.peek().getTitle2());
        return navigationStack.peek().getLastPage() + "?faces-redirect=true";
    }

    public String masterData() {
//        FacesContext ctx = FacesContext.getCurrentInstance();
//
//        ExternalContext extContext = ctx.getExternalContext();
//        String url = extContext.encodeActionURL(ctx.getApplication().getViewHandler().getActionURL(ctx, "/pages/masterdata/masterdata"));
//        try {
//
//            extContext.redirect(url);
//        } catch (IOException ioe) {
//            throw new FacesException(ioe);
//
//        }
//        return null;




        log.info("masterdata");
        //navigationStack.push(new NavigationData("MASTERDATA", "/pages/masterdata/masterdata"));
        navigationStack.push(new NavigationData("MASTERDATA", "/pages/masterdata/masterdata", "MASTERDATA", ""));
        setNavigationPath(navigationStack.peek().getNavigationPath());
        setTitle1(navigationStack.peek().getTitle1());
        setTitle2(navigationStack.peek().getTitle2());
        return navigationStack.peek().getLastPage() + "?faces-redirect=true";
        //return navigationStack.peek().getLastPage();
    }

    public Stack<NavigationData> getNavigationStack() {
        return navigationStack;
    }

    public void setNavigationStack(Stack<NavigationData> navigationStack) {
        this.navigationStack = navigationStack;
    }
}
