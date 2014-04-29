/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.util.Collections;
import java.util.Comparator;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import masterdatamodule.entity.RegLicQual;
import masterdatamodule.entity.Region;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "RegLicQualListServlet", urlPatterns = {"/pages/RegLicQualListServlet"})
public class RegLicQualListServlet extends MasterDataServletBase<RegLicQual> {

    @Inject
    private RegLicQualController controller;
    
   
    @Override
    protected MasterDataControllerBase<RegLicQual> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, RegLicQual entity) {
        rowData.put(entity.getName());
        rowData.put(entity.getRegLicQualType().getName());
        rowData.put(entity.getRegion().getName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<RegLicQual>() {

                @Override
                public int compare(RegLicQual o1, RegLicQual o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
    
    
}
