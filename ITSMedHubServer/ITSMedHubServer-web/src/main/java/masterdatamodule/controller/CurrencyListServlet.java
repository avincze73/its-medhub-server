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
import masterdatamodule.entity.Currency;
import org.richfaces.json.JSONArray;
import usermodule.entity.ITSManager;

/**
 *
 * @author tds
 */
@WebServlet(name = "CurrencyListServlet", urlPatterns = {"/pages/CurrencyListServlet"})
public class CurrencyListServlet extends MasterDataServletBase<Currency> {

    @Inject
    private CurrencyController controller;

    @Override
    protected MasterDataControllerBase<Currency> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, Currency entity) {
        rowData.put(entity.getName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<Currency>() {

                @Override
                public int compare(Currency o1, Currency o2) {
                    return "asc".equals(order)
                            ? o1.getName().compareToIgnoreCase(o2.getName())
                            : o1.getName().compareToIgnoreCase(o2.getName()) * -1;
                }
            });
        }
    }
}
