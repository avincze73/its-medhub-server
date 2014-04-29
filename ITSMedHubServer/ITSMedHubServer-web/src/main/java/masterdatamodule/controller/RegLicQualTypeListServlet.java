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
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.Region;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "RegLicQualTypeListServlet", urlPatterns = {"/pages/RegLicQualTypeListServlet"})
public class RegLicQualTypeListServlet extends MasterDataServletBase<RegLicQualType> {

    @Inject
    private RegLicQualTypeController controller;
    
   
    @Override
    protected MasterDataControllerBase<RegLicQualType> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, RegLicQualType entity) {
        rowData.put(entity.getName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<RegLicQualType>() {

                @Override
                public int compare(RegLicQualType o1, RegLicQualType o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
    
    
}
