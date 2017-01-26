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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import util.ImageUtil;
/**
 *
 * @author K00191419
 */
@WebServlet(name = "AgentController", urlPatterns = {"/AgentController"})
@MultipartConfig
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
				case "insert":
					address = DoInsert(request, agent);
					break;
				case "add":
					address = DoAdd(request, agent);
					break;
				case "agent_details":
					address = DoAgentDetails(request, agent);
					break;
				case "edit_agent":
					address = DoEditAgent(request, agent);
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

	private void processUpload(HttpServletRequest request, String listingNum) throws ServletException, IOException {
		// get description param (multipart form)
		String description = request.getParameter("description");
		if(description != null){
			// get file Part list from getParts() collection
			Collection<Part> partCollection = request.getParts();
			List<Part> parts = new ArrayList<>();
			for(Part p : partCollection){
				if(p.getName().equals("file")){
					parts.add(p);
				}
			}
			
			for (Part filePart : parts) {
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				InputStream fileContent = filePart.getInputStream();
				ProcessFileUpload(fileContent, 
						request.getServletContext().getRealPath("/"),
						listingNum);
			}		
		}
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

	private String DoDelete(HttpServletRequest request, Agent agent) throws IOException {
		String id = request.getParameter("id");
		Property prop = null;
		boolean found = false;
		
		for(Property p : agent.getPropertyCollection()){
			if(p.getId().toString().equals(id)){
				prop = p;
				found = true; break;
			}
		}
		
		if(!found){
			request.setAttribute("msg", "You cannot delete this property");
			return "error.jsp";			
		}
		// archive images
		handleImageArchiving(prop.getListingNum().toString(), 
				request.getServletContext().getRealPath("/"));
		PropertyModel.archiveProperty(prop);
		PropertyModel.deleteProperty(prop);
		return "AgentController?action=agent_home";
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
		
		// get image data
        String rPath = request.getServletContext().getRealPath("/");
        File folder = new File(rPath+"assets/img/properties/large/"+prop.getListingNum()+"/");
        // get filenames
        File[] imgFiles = folder.listFiles();
		
		if(!found){
			request.setAttribute("msg", "You cannot manage this property");
			return "error.jsp";
		}
		else{
			request.setAttribute("img_folder", prop.getListingNum());
			request.setAttribute("img_files", imgFiles);
			request.setAttribute("styles", styles);
			request.setAttribute("pTypes", pTypes);
			request.setAttribute("garages", garages);
			request.setAttribute("bers", bers);
			request.setAttribute("prop", prop);
			return "agent/edit.jsp";
		}
	}

	private String DoEdit(HttpServletRequest request, Agent agent)
			throws IOException, ServletException{
		
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
		// handle image deletion
		String[] delImgs = request.getParameterValues("delImgs");
		if(delImgs != null){
			handleImageArchiving(delImgs, 
					property.getListingNum().toString(),
					request.getServletContext().getRealPath("/"));
		}
		// handle image upload
		processUpload(request, property.getListingNum().toString());
		
		// Parse data.
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
		property.setDescription(request.getParameter("description"));
		property.setSquarefeet(Integer.parseInt(request.getParameter("squarefeet")));
		
		property.setStyleId(style);
		property.setTypeId(pType);
		property.setGarageId(garage);

		PropertyModel.updateProperty(property);
		
		return "AgentController?action=manage&id="+property.getId();
	}

	private void handleImageArchiving(String[] delImgs, String imgDir, String rPath)
			throws IOException{
		// get image data
        File folder = new File(rPath+"assets/img/properties/large/"+imgDir+"/");
		Path archive = Paths.get(rPath+"archive/"+imgDir);
		
		// check and create directories if they don't exist
		if(!Files.exists(archive))
			Files.createDirectories(archive);
		
		// move files to archive
		for(String file : delImgs){
			Path src = Paths.get(rPath+"assets/img/properties/large/"+imgDir+"/"+file);
			Path dest = Paths.get(archive+"/"+file);
//			File img = new File(rPath+"assets/img/properties/large/"+imgDir+"/"+file);
			Files.move(src, dest, REPLACE_EXISTING);
		}
		
	}
	private void handleImageArchiving(String imgDir, String rPath)
			throws IOException{
		// get image data
        File folder = new File(rPath+"assets/img/properties/large/"+imgDir+"/");
		Path archive = Paths.get(rPath+"archive/"+imgDir);
		
		// check and create directories if they don't exist
		if(!Files.exists(archive))
			Files.createDirectories(archive);
		
		// move files to archive

		Path src = Paths.get(rPath+"assets/img/properties/large/"+imgDir+"/");
		Path dest = Paths.get(archive+"/");
//			File img = new File(rPath+"assets/img/properties/large/"+imgDir+"/"+file);
		Files.move(src, dest, REPLACE_EXISTING);

		
	}
	private void ProcessFileUpload(InputStream fileContent, String realPath, String lsNum) 
			throws IOException {
		System.out.println("Uploaded file.");
		// img configuration params
		int largeWidth = 480;
		int largeHeight = 320;
		
		BufferedImage rawImg = ImageIO.read(fileContent);
		BufferedImage large = ImageUtil.ResizeJpeg(largeWidth, largeHeight, rawImg);
		
		String newFileDir = realPath+"assets/img/properties/large/"+lsNum+"/";
		String newFile = GetNewFileName(newFileDir, lsNum);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(large, "jpg", baos);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());

		File upload = new File(newFile);
		Files.copy(is, upload.toPath());	
		
	}

	private String GetNewFileName(String newFileDir, String lsNum) {
		boolean ex  = Files.exists(Paths.get(newFileDir));
		if(!ex){
			return newFileDir + lsNum+".jpg";
		}
		
		String name = newFileDir+lsNum;
		File[ ] existing = new File(newFileDir).listFiles();
		int suffix = 1;
		for(File f : existing){
			String fn = f.getName();
			if(fn.contains("-")){
				int sfx = Integer.parseInt(fn.substring(
						fn.indexOf("-")+1, 
						fn.indexOf(".")));
				if(sfx > suffix)
					suffix = sfx+1;
			}
		}
		newFileDir += lsNum+"-"+suffix+".jpg";
		
		return newFileDir;
	}

	private String DoInsert(HttpServletRequest request, Agent agent) {

			// get comboboxes data
			List<Style> styles = PropertyModel.getStyles();
			List<PropertyType> pTypes = PropertyModel.getPTypes();
			List<Garage> garages = PropertyModel.getGarages();
			List<String> bers = PropertyModel.getBERs();
			Date date = new Date();
			
			request.setAttribute("styles", styles);
			request.setAttribute("pTypes", pTypes);
			request.setAttribute("garages", garages);
			request.setAttribute("bers", bers);
			request.setAttribute("date", date);
			return "agent/insert.jsp";

	}

	private String DoAdd(HttpServletRequest request, Agent agent) throws IOException, ServletException {
		Property property = new Property();
		
		property.setAgentId(agent);
		property.setDateAdded(new Date());
		
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
		property.setDescription(request.getParameter("description"));
		property.setSquarefeet(Integer.parseInt(request.getParameter("squarefeet")));
		property.setListingNum(Integer.parseInt(request.getParameter("listingNum")));
		property.setPhoto(request.getParameter("listingNum")+".jpg");
		PropertyModel.insertProperty(property);
		
		
		HandleInsertFileUpload(request);
		
		return "/AgentController?action=agent_home";
	}

	private void HandleInsertFileUpload(HttpServletRequest request) throws IOException, ServletException {

			// get file Part list from getParts() collection
			Collection<Part> partCollection = request.getParts();
			List<Part> parts = new ArrayList<>();
			for(Part p : partCollection){
				if(p.getName().equals("file")){
					parts.add(p);
				}
			}
			
			// img configuration params
			int largeWidth = 480;
			int largeHeight = 320;
			int thumbWidth = 75;
			int thumbHeight = 50;
			
			String lsNum = request.getParameter("listingNum");
			String thumbDir = request.getServletContext().getRealPath("/")+"assets/img/properties/thumbs/";
			int sfx = 0;
			for (Part filePart : parts) {
				
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				InputStream fileContent = filePart.getInputStream();
				BufferedImage rawImg = ImageIO.read(fileContent);
				BufferedImage large = ImageUtil.ResizeJpeg(largeWidth, largeHeight, rawImg);
				
				if(sfx == 0){
					BufferedImage thumb = ImageUtil.ResizeJpeg(thumbWidth, thumbHeight, rawImg);
					String newFileDir = request.getServletContext().getRealPath("/")+"assets/img/properties/large/"+lsNum+"/";
					String newFile = newFileDir+lsNum+".jpg";
					String thumbFile = thumbDir+lsNum+".jpg";
					ImageUtil.SaveBufferedImage(large, newFile);
					ImageUtil.SaveBufferedImage(thumb, thumbFile);
				}
				else{
					String newFileDir = request.getServletContext().getRealPath("/")+"assets/img/properties/large/"+lsNum+"/";
					String newFile = newFileDir+lsNum+"-"+sfx+".jpg";
					ImageUtil.SaveBufferedImage(large, newFile);
				}
				sfx++;
			}		
		
	}

	private String DoAgentDetails(HttpServletRequest request, Agent agent) {
		request.setAttribute("agent", agent);
		return "agent/agent_details.jsp";
		
	}

	private String DoEditAgent(HttpServletRequest request, Agent agent) {
		// params validated by filter
		agent.setName(request.getParameter("name"));
		agent.setEmail(request.getParameter("email"));
		agent.setPhone(request.getParameter("phone"));
		agent.setFax(request.getParameter("fax"));
		
		AgentModel.updateAgent(agent);
		return "AgentController?action=agent_home";
		
	}
}