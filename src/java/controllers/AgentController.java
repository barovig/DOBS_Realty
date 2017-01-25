/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.entities.Agent;
import database.entities.Garage;
import database.entities.Property;
import database.entities.PropertyType;
import database.entities.Style;
import database.models.AgentModel;
import database.models.PropertyModel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import org.jcp.xml.dsig.internal.dom.Utils;

/**
 *
 * @author K00191419
 */
@WebServlet(name = "AgentController", urlPatterns = {"/AgentController"})
public class AgentController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        String address = "agent/agent.jsp";

		try{
			// get session and j_username
			HttpSession sess = request.getSession();
			String agentName = request.getUserPrincipal().getName();
			
			// get Agent and put into session
			Agent agent = AgentModel.getAgentByName(agentName);
			sess.setAttribute("user", agent);
			
            // get action parameter for callback
            String action = request.getParameter("action");
            
            // if it is null - homepage requested
            if(action == null)
                action = "agent_home";
            
            // perform action (which will set relevant paremeters) and return
            // the address
            switch(action){
                case "details":
                    address = DoDrilldown(request);
                    break;
                case "agent_home":
                    address = DoDisplayAgentHome(request, agent);
                    break;
                case "delete":
                    address = DoDelete(request, agent);
                    break;
                case "manage":
                    address = DoManage(request, agent);
                    break;
				case "edit":
					address = DoEdit(request, agent);
					break;
				case "logout":
					sess.invalidate();
					address= "homepage.jsp";
					break;
                default:
                    address = DoDisplayAgentHome(request, agent);
                    break;
			}
        }// end try
        catch (Exception ex) {
            address = "/error.jsp";
        }//end catch
		
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
        
    }

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


	private ArrayList<Property> GetAgentProperties(HttpServletRequest request) {
		return null;
	}

	private String DoDisplayAgentHome(HttpServletRequest request, Agent agent) {

		request.setAttribute("list", agent.getPropertyCollection());
		return "agent/agent.jsp";

	}

    private String DoDrilldown(HttpServletRequest request) {
        
        String id = request.getParameter("id");
        Property property = PropertyModel.getPropertyById(id);
        
        // Process images:
        // get folder name:
        String photo = property.getPhoto();
        String imgFolder = photo.substring(0, photo.lastIndexOf("."));
        // get real path for root context
        String rPath = request.getServletContext().getRealPath("/");
        File folder = new File(rPath+"assets/img/properties/large/"+imgFolder+"/");
        // get filenames
        File[] imgFiles = folder.listFiles();
                
        // set attributes
        request.setAttribute("img_folder", imgFolder);
        request.setAttribute("img_files", imgFiles);
        request.setAttribute("agent", property.getAgentId());
        request.setAttribute("prop", property);
        
        return "/drilldown.jsp";
    }

	private String DoDelete(HttpServletRequest request, Agent agent) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private String DoManage(HttpServletRequest request, Agent agent) {	
		String id = request.getParameter("id");
		Property prop = null;
		boolean found = false;
		for(Property p : agent.getPropertyCollection()){
			if(p.getId().toString().equals(id)){
				prop = p;
				found = true; break;
			}
		}
		// get comboboxes data
		List<Style> styles = PropertyModel.getStyles();
		List<PropertyType> pTypes = PropertyModel.getPTypes();
		List<Garage> garages = PropertyModel.getGarages();
		List<String> bers = PropertyModel.getBERs();
		
		if(!found){
			request.setAttribute("msg", "You cannot manage this property");
			return "error.jsp";
		}
		else{
			request.setAttribute("styles", styles);
			request.setAttribute("pTypes", pTypes);
			request.setAttribute("garages", garages);
			request.setAttribute("bers", bers);
			request.setAttribute("prop", prop);
			return "agent/edit.jsp";
		}
	}

	private String DoEdit(HttpServletRequest request, Agent agent) {
		
		// NOTE: Data validated by filter.
		// find property that is being edited
		int listingNum = Integer.parseInt(request.getParameter("listingNum"));
		Property property = null;
		for(Property p : agent.getPropertyCollection()){
			if(p.getListingNum() == listingNum){
				property = p; break;
			}
		}
		
		if(property == null)
			return "error.jsp";
		
		// Parse data.
		Enumeration<String> params = request.getParameterNames();
		// get references for Property member objects
		PropertyType pType = PropertyModel.getPropertyTypeById(request.getParameter("typeId"));
		Style style = PropertyModel.getStyleById(request.getParameter("styleId"));
		Garage garage = PropertyModel.getGarageById(request.getParameter("garageId"));
		
		// update property
		property.setStreet(request.getParameter("street"));
		property.setCity(request.getParameter("city"));
		property.setPrice(Double.parseDouble(request.getParameter("price")));
		property.setBedrooms(Integer.parseInt(request.getParameter("bedrooms")));
		property.setBathrooms(Float.parseFloat(request.getParameter("bathrooms")));
		property.setGaragesize(Short.parseShort(request.getParameter("garageSize")));
		property.setLotsize(request.getParameter("lotSize"));
		property.setBerRating(request.getParameter("berRating"));
		
		property.setStyleId(style);
		property.setTypeId(pType);
		property.setGarageId(garage);
		
		PropertyModel.updateProperty(property);
		
		return "AgentController?action=manage&id="+property.getId();
	}
}