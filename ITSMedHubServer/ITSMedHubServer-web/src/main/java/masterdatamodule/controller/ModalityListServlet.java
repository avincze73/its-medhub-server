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
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.Region;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "ModalityListServlet", urlPatterns = {"/pages/ModalityListServlet"})
public class ModalityListServlet extends MasterDataServletBase<Modality> {

    @Inject
    private ModalityController controller;
    
   
    @Override
    protected MasterDataControllerBase<Modality> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, Modality entity) {
        rowData.put(entity.getName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<Modality>() {

                @Override
                public int compare(Modality o1, Modality o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
    
    
}
