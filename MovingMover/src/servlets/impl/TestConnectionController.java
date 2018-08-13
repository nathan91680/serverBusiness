package servlets.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import servlets.abstr.CustomHttpServlet;
import servlets.api.ITestConnectionController;


@RestController
public class TestConnectionController extends CustomHttpServlet implements ITestConnectionController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@RequestMapping(value = "/test/connection/application", method = RequestMethod.GET)
	@ResponseBody
	public void testConnection(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.getServletContext().getRequestDispatcher( "/WEB-INF/connectiontestin.jsp" ).forward( request, response );
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
