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
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Currency;
import org.richfaces.json.JSONArray;
import usermodule.entity.ITSManager;

/**
 *
 * @author tds
 */
@WebServlet(name = "CountryListServlet", urlPatterns = {"/pages/CountryListServlet"})
public class CountryListServlet extends MasterDataServletBase<Country> {

    @Inject
    private CountryController controller;

    @Override
    protected MasterDataControllerBase<Country> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, Country entity) {
        rowData.put(entity.getEnglishName());
        rowData.put(entity.getHungarianName());
        rowData.put(entity.getCurrency().getName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("englishName")) {
            Collections.sort(controller.getEntityList(), new Comparator<Country>() {

                @Override
                public int compare(Country o1, Country o2) {
                    return "asc".equals(order)
                            ? o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName())
                            : o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName()) * -1;
                }
            });
        } else if (request.getParameter("sidx").equals("hungarianName")) {
            Collections.sort(controller.getEntityList(), new Comparator<Country>() {

                @Override
                public int compare(Country o1, Country o2) {
                    return "asc".equals(order)
                            ? o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName())
                            : o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName()) * -1;
                }
            });
        }
    }
}
