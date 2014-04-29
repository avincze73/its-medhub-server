/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import base.ITSEntity;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONException;
import org.richfaces.json.JSONObject;

/**
 *
 * @author tds
 */
public abstract class MasterDataServletBase<T extends ITSEntity> extends HttpServlet {

    protected abstract MasterDataControllerBase<T> getController();

    protected abstract void putData(JSONArray rowData, T entity);

    protected void doSort(HttpServletRequest request, HttpServletResponse response) {
    }

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
        doSort(request, response);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            JSONObject gridData = new JSONObject();
            gridData.put("total", "1");
            gridData.put("page", "1");
            gridData.put("records", getController().getEntityList().size());
            JSONArray data = new JSONArray();
            gridData.put("rows", data);

            for (T entity : getController().getEntityList()) {
                JSONObject row = new JSONObject();
                JSONArray rowData = new JSONArray();
                rowData.put(entity.getId());
                putData(rowData, entity);
                row.put("id", entity.getId());
                row.put("cell", rowData);
                data.put(row);
            }
            JSONObject userData = new JSONObject();
            gridData.put("userdata", userData);
            userData.put("page", "1");
            userData.put("selId", getController().getSelectedId());
            out.println(gridData);
        } catch (JSONException ex) {
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
