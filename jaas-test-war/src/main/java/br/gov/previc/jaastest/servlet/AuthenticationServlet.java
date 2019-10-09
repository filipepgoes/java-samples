package br.gov.previc.jaastest.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AuthenticationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<html><head><title>Jaas Test</title></head><body>");
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		if ((user!=null)&&(password!=null)) {
			JaasTestCallbackHandler ch = new JaasTestCallbackHandler(user,password);
			boolean authenticatedFlag=false;
			try {
				LoginContext lc = new LoginContext("JaasTest",ch);
				lc.login();
			} catch (LoginException e) {
				authenticatedFlag = false;
			}
			if (authenticatedFlag) {
				pw.println("<p>Authentication success...</p>");
			}
			else {
				pw.println("<p>Authentication failure...</p>");
			}
		}
		else {
			pw.println("<p>Invalid authentication...</p>");
		}
		pw.println("</body></html>");
	}

}
