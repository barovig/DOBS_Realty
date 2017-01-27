/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.servlet.Filter;
import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author semargl
 */
public class SecurityFilter implements Filter{

    private FilterConfig filterConfig = null;
	
	private static enum Types{
		INTEGER, DOUBLE, STRING, DATE, SHORT, FLOAT
	}
	private String msg;
	
	private static final HashMap<String, Types> attribs;
	static{
		attribs = new HashMap<>();
		attribs.put("id", Types.INTEGER);
		attribs.put("action", Types.STRING);
		attribs.put("listingNum", Types.INTEGER);
		attribs.put("dateAdded", Types.DATE);
		attribs.put("street", Types.STRING);
		attribs.put("city", Types.STRING);
		attribs.put("price", Types.DOUBLE);
		attribs.put("typeId", Types.INTEGER);
		attribs.put("styleId", Types.INTEGER);
		attribs.put("bedrooms", Types.INTEGER);
		attribs.put("bathrooms", Types.FLOAT);
		attribs.put("garageId", Types.INTEGER);
		attribs.put("garageSize", Types.SHORT);
		attribs.put("lotSize", Types.STRING);
		attribs.put("berRating", Types.STRING);
		attribs.put("phone", Types.STRING);
		attribs.put("fax", Types.STRING);		
		attribs.put("email", Types.STRING);		
		attribs.put("name", Types.STRING);		


	}
	
	private static final HashMap<String, String> rgxMap;
	static{
		// regex matching invalid characters for parameters
		rgxMap = new HashMap<>();
		rgxMap.put("action", "[^a-z_]");
		rgxMap.put("street", "[^-a-zA-Z0-9()., ]");
		rgxMap.put("city", "[^-a-zA-Z0-9()., ]");
		rgxMap.put("lotSize", "[^0-9x]");
		rgxMap.put("berRating", "[^0-9A-G]");
		rgxMap.put("phone", "[^-0-9()+]");
		rgxMap.put("fax", "[^-0-9]()+");
		rgxMap.put("email", "[^@a-zA-Z0-9._]");
		rgxMap.put("name", "[^-a-zA-Z' ]");
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		ServletContext sc = filterConfig.getServletContext();
						
		// validate possible parameters
		boolean validated = ValidateParameters(request);

		if(!validated){
			request.setAttribute("error", "Validation error");
		}
//		filterConfig.getServletContext()
//				.log("Servlet path: "+httpReq.getServletPath());
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {
		filterConfig= null;
	}
	
	private boolean ValidateParameters(ServletRequest request) {
		// get parameter names
		List<String> names = Collections.list(request.getParameterNames());
		// if param should be sanitised, process it using one of validators
		boolean valid = true;
		for(String p : names){
			if(attribs.containsKey(p)){
				switch(attribs.get(p)){
					case INTEGER:
						valid = validateInt(request.getParameter(p));
						break;
					case DOUBLE:
						valid = validateDouble(request.getParameter(p));
						break;
					case FLOAT:
						valid = validateFloat(request.getParameter(p));
						break;
					case SHORT:
						valid = validateShort(request.getParameter(p));
						break;						
					case STRING:
						valid = validateString(request.getParameter(p), rgxMap.get(p));
						break;
					case DATE:
						valid = validateDate(request.getParameter(p));
						break;
					default:
				}
			}
			if(!valid){
				filterConfig.getServletContext()
					.log("Error validating parameter ("+p+")");
				break;
			}
		}
		return valid;
	}
		
	private boolean validateInt(String value){
		try{
			Integer.parseInt(value);
		}
		catch(Exception e){
			filterConfig.getServletContext()
				.log("Error validating Int parameter ("+value+")");
			return false;
		}
		return true;
	}
	
	private boolean validateDouble(String value){
		try{
			Double.parseDouble(value);
		}
		catch(Exception e){
			filterConfig.getServletContext()
				.log("Error validating Double parameter ("+value+")");
			return false;
		}
		return true;
	}
	
	private boolean validateShort(String value){
		try{
			Short.parseShort(value);
		}
		catch(Exception e){
			filterConfig.getServletContext()
				.log("Error validating Double parameter ("+value+")");			
			return false;
		}
		return true;
	}
	
	private boolean validateFloat(String value){
		try{
			Float.parseFloat(value);
		}
		catch(Exception e){
			filterConfig.getServletContext()
				.log("Error validating Double parameter ("+value+")");			
			return false;
		}
		return true;
	}
	private boolean validateString(String value, String regex){
		// regex should match for disallowed characters
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(value);
		// return inverse of matching 
		// (e.g. regex for special chars->matched->return false
		if(m.find()){
			filterConfig.getServletContext()
				.log("Error validating String parameter ("+value+") with regex ("+regex+")");
			return false;
		}
		return true;
	}
	
	private boolean validateDate(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d = format.parse(date);
		}
		catch(Exception e){
			filterConfig.getServletContext()
				.log("Error validating Date parameter ("+date+")");			
			return false;
		}
		return true;
	}
}
