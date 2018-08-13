package servlets.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITestConnectionController {
	public void testConnection(HttpServletRequest request, HttpServletResponse response);
}
