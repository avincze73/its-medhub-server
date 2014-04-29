/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;
import usermodule.entity.ITSManager;

/**
 *
 * @author itsadmin
 */
public class ManagerListServlet extends HttpServlet {

    @Inject
    private ManagerController controller;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html");
        final String order = request.getParameter("sord");
        if (request.getParameter("sidx").equals("name")) {
            Collections.sort(controller.getEntityList(), new Comparator<ITSManager>() {

                @Override
                public int compare(ITSManager o1, ITSManager o2) {
                    return "asc".equals(order) ?
                            o1.getItsUser().getName().compareToIgnoreCase(o2.getItsUser().getName()) :
                            o1.getItsUser().getName().compareToIgnoreCase(o2.getItsUser().getName()) * -1;
                }
            });
        } else if (request.getParameter("sidx").equals("loginName")) {
            Collections.sort(controller.getEntityList(), new Comparator<ITSManager>() {

                @Override
                public int compare(ITSManager o1, ITSManager o2) {
                    return "asc".equals(order) ? 
                            o1.getItsUser().getLoginName().compareToIgnoreCase(o2.getItsUser().getLoginName()) :
                            o1.getItsUser().getLoginName().compareToIgnoreCase(o2.getItsUser().getLoginName()) * -1;
                }
            });
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            JSONObject jqGridData = new JSONObject();
            try {
                jqGridData.put("total", "1");
                jqGridData.put("page", "1");
                jqGridData.put("records", controller.getEntityList().size());
            } catch (JSONException ex) {
                Logger.getLogger(ManagerListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                System.out.println("aa " + controller.getSearchName());
                System.out.println("sidx: " + request.getParameter("sidx"));
                //String name = controller.getEntityList().get(0).getItsUser().getName();
                JSONArray data = new JSONArray();
                jqGridData.put("rows", data);
                System.out.println(controller.getEntityList().size());

                for (ITSManager iTSManager : controller.getEntityList()) {
                    JSONObject row1 = new JSONObject();
                    JSONArray row1data = new JSONArray();
                    row1data.put(iTSManager.getId());
                    row1data.put(iTSManager.getItsUser().getName());
                    row1data.put(iTSManager.getItsUser().getLoginName());
                    row1data.put(iTSManager.getItsUser().getAddingDateTime());
                    row1data.put(iTSManager.getItsUser().getLoginName());
                    row1.put("id", iTSManager.getId());
                    row1.put("cell", row1data);
                    data.put(row1);
                }
                JSONObject userData = new JSONObject();
                jqGridData.put("userdata", userData);
                userData.put("page", "1");
                userData.put("selId", (!controller.getEntityList().isEmpty() ? controller.getEntityList().get(0).getId() : null));
            } catch (JSONException ex) {
                Logger.getLogger(ManagerListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(jqGridData);
        } finally {
            out.close();
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
