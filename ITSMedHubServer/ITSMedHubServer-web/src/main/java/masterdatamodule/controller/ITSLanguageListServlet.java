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
import masterdatamodule.entity.ITSLanguage;
import org.richfaces.json.JSONArray;
import usermodule.entity.ITSManager;

/**
 *
 * @author tds
 */
@WebServlet(name = "ITSLanguageListServlet", urlPatterns = {"/pages/ITSLanguageListServlet"})
public class ITSLanguageListServlet extends MasterDataServletBase<ITSLanguage> {

    @Inject
    private ITSLanguageController controller;

    @Override
    protected MasterDataControllerBase<ITSLanguage> getController() {
        return controller;
    }

    @Override
    protected void putData(JSONArray rowData, ITSLanguage entity) {
        rowData.put(entity.getEnglishName());
        rowData.put(entity.getHungarianName());
    }

    @Override
    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
        super.doSort(request, response);
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("englishName")) {
            Collections.sort(controller.getEntityList(), new Comparator<ITSLanguage>() {

                @Override
                public int compare(ITSLanguage o1, ITSLanguage o2) {
                    return "asc".equals(order)
                            ? o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName())
                            : o1.getEnglishName().compareToIgnoreCase(o2.getEnglishName()) * -1;
                }
            });
        } else if (request.getParameter("sidx").equals("hungarianName")) {
            Collections.sort(controller.getEntityList(), new Comparator<ITSLanguage>() {

                @Override
                public int compare(ITSLanguage o1, ITSLanguage o2) {
                    return "asc".equals(order)
                            ? o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName())
                            : o1.getHungarianName().compareToIgnoreCase(o2.getHungarianName()) * -1;
                }
            });
        }
    }
}
