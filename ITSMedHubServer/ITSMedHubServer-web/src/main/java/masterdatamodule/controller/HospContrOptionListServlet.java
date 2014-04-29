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
import masterdatamodule.entity.HospContrOption;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "HospContrOptionListServlet", urlPatterns = {"/pages/HospContrOptionListServlet"})
public class HospContrOptionListServlet extends MasterDataServletBase<HospContrOption> {

    @Inject
    private HospContrOptionController controller;
    
   
    @Override
    protected MasterDataControllerBase<HospContrOption> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, HospContrOption entity) {
        rowData.put(entity.getName());
        rowData.put(entity.getTypes());
        rowData.put(entity.getExplanation());
        rowData.put(entity.getStringParameter());
        rowData.put(entity.getLongParameter());
        rowData.put(entity.getDoubleParameter());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<HospContrOption>() {

                @Override
                public int compare(HospContrOption o1, HospContrOption o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
    
    
}
