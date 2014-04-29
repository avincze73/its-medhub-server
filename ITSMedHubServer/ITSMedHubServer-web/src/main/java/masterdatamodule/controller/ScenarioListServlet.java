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
import masterdatamodule.entity.Scenario;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "ScenarioListServlet", urlPatterns = {"/pages/ScenarioListServlet"})
public class ScenarioListServlet extends MasterDataServletBase<Scenario> {

    @Inject
    private ScenarioController controller;
    
   
    @Override
    protected MasterDataControllerBase<Scenario> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, Scenario entity) {
        rowData.put(entity.getEnglishName());
        rowData.put(entity.getHungarianName());
        rowData.put(entity.getCategory());
        rowData.put(entity.getRadiologistStarts());
        rowData.put(entity.getBehaviour());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("englishName")) {
            Collections.sort(controller.getEntityList(), new Comparator<Scenario>() {

                @Override
                public int compare(Scenario o1, Scenario o2) {
                    return "asc".equals(order)
                            ? o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName())
                            : o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName()) * -1;
                }
            });
        } else if (request.getParameter("sidx").equals("hungarianName")) {
            Collections.sort(controller.getEntityList(), new Comparator<Scenario>() {

                @Override
                public int compare(Scenario o1, Scenario o2) {
                    return "asc".equals(order)
                            ? o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName())
                            : o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName()) * -1;
                }
            });
        }
    }
    
    
}
