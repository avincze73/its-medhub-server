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
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.entity.Region;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "ModeOfAcquisitionListServlet", urlPatterns = {"/pages/ModeOfAcquisitionListServlet"})
public class ModeOfAcquisitionListServlet extends MasterDataServletBase<ModeOfAcquisition> {

    @Inject
    private ModeOfAcquisitionController controller;
    
   
    @Override
    protected MasterDataControllerBase<ModeOfAcquisition> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, ModeOfAcquisition entity) {
        rowData.put(entity.getName());
        rowData.put(entity.getCode());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<ModeOfAcquisition>() {

                @Override
                public int compare(ModeOfAcquisition o1, ModeOfAcquisition o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        } else if (request.getParameter("sidx").equals("code")) {
            Collections.sort(controller.getEntityList(), new Comparator<ModeOfAcquisition>() {

                @Override
                public int compare(ModeOfAcquisition o1, ModeOfAcquisition o2) {
                    return "asc".equals(order)
                            ? o1.getCode().compareToIgnoreCase(o2.getCode())
                            : o1.getCode().compareToIgnoreCase(o2.getCode()) * -1;
                }
            });
        }
    }
    
    
}
