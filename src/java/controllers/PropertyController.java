/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.entities.Property;
import database.models.PropertyModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author K00191419
 */
@WebServlet(name = "PropertyController", urlPatterns = {"/PropertyController"})
public class PropertyController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // set default to error - in case it will not be set.
        String address = "/error.jsp";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try{
            // get action parameter for callback
            String action = request.getParameter("action");
            
            // if it is null - homepage requested
            if(action == null)
                action = "home";
            
            // perform action (which will set relevant paremeters) and return
            // the address
            switch(action){
                case "details":
                    address = DoDrilldown(request);
                    break;
                case "home":
                    address = DoDisplayHome(request);
                    break;
                case "search":
                    address = DoSearch(request);
                    break;
                default:
                    address = DoDisplayHome(request);
                    break;
            }
        }// end try
        catch (Exception ex) {
            address = "/error.jsp";
        }//end catch

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Callback methods for actions on Properties">
    private String DoDrilldown(HttpServletRequest request) {
        
        String id = request.getParameter("id");
        Property property = PropertyModel.getPropertyById(id);
        
        request.setAttribute("prop", property);
        
        return "/drilldown.jsp";
    }

    private String DoDisplayHome(HttpServletRequest request) {
        
        List<Property> propList = PropertyModel.getAllProperties();

        // sort by date
        Collections.sort(propList, new Comparator<Property>(){
           @Override
           public int compare(Property p1, Property p2){
               // swap arguments to reverse order (instead of calling reverse)
             return p2.getDateAdded().compareTo(p1.getDateAdded());
           } 
        });
        // get first 7 items
        List<Property> lastAdditions = propList.subList(0, 7);
        
        // get cities dynamically
        List<String> citiesList = PropertyModel.getAllCities();
        
        if (propList.isEmpty() || citiesList.isEmpty()) {
            return "/error.jsp";
        } else {
            Collections.sort(citiesList);
            request.setAttribute("list", propList);
            request.setAttribute("lastAdditions", lastAdditions);
            request.setAttribute("cities", citiesList);
            return "/homepage.jsp";
        }    
    }
    private String DoSearch(HttpServletRequest request) {
        
        // get and validate criteria
        String city = request.getParameter("city");
        city = ("any".equals(city)) ? "%" : city;        // replace 'any' with wildcard
        String min_p = request.getParameter("min_price");
        String max_p = request.getParameter("max_price");
        Double min = (min_p == null || min_p.isEmpty()) 
                ? 0d : Double.parseDouble(min_p);
        Double max = (max_p == null || max_p.isEmpty())
                ? Double.MAX_VALUE : Double.parseDouble(max_p);

        // get filtered properties
        List<Property> propList = PropertyModel.getSearchProperties(city, min, max);
        // don't forget list of cities
        List<String> citiesList = PropertyModel.getAllCities();
        
        if( citiesList.isEmpty()){
            return "/error.jsp";
        }
        else if(propList.isEmpty()){
            request.setAttribute("msg", "No items found...");
            request.setAttribute("cities", citiesList);
            request.setAttribute("selectCity", city);
            return "/homepage.jsp";            

        }
        else{
            request.setAttribute("list", propList);
            request.setAttribute("cities", citiesList);
            request.setAttribute("selectCity", city);
            return "/homepage.jsp";            
        }

    }
//</editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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