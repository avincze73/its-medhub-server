/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import masterdatamodule.entity.ITSConstant;
import org.richfaces.json.JSONArray;

/**
 *
 * @author tds
 */
@WebServlet(name = "ITSConstantListServlet", urlPatterns = {"/pages/ITSConstantListServlet"})
public class ITSConstantListServlet extends MasterDataServletBase<ITSConstant> {

    @Inject
    private ITSConstantController controller;
    
   
    @Override
    protected MasterDataControllerBase<ITSConstant> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, ITSConstant entity) {
        rowData.put(entity.getName());
        rowData.put(entity.getConstantValue());
        rowData.put(entity.getDescription());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<ITSConstant>() {

                @Override
                public int compare(ITSConstant o1, ITSConstant o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
    
    
}
