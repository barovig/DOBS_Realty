/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.Filter;
import java.util.logging.LogRecord;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		ServletContext sc = filterConfig.getServletContext();
		String filterName = filterConfig.getFilterName();
		
		String username = httpReq.getParameter("j_username");

		String password = httpReq.getParameter("j_password");
		ArrayList<String> attributes = Collections.list(request.getAttributeNames());
		
		attributes.isEmpty();
		filterConfig.getServletContext()
				.log("Servlet path: "+httpReq.getServletPath());
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
	
	
}
