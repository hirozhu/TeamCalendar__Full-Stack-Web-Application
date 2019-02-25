

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;

/**
 * Servlet implementation class LinkToOthersProfile
 */
@WebServlet("/LinkToOthersProfile")
public class LinkToOthersProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LinkToOthersProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String currentUserEmail = request.getParameter("currentUserEmail");
		
		System.out.println("----------------In LinkToOthersProfile ----------------");
		System.out.println("Here: email: " + email);
		System.out.println("Here: current User email: " + currentUserEmail);
		JDBC jdbc = new JDBC();
		ArrayList<String[]> eventOfThisUser = jdbc.getEvent(email);
		ArrayList<String[]> profileOfThisUser = jdbc.getProfile(email);
		
		boolean followStatus = jdbc.checkIfFollow(currentUserEmail, email);
		request.setAttribute("followStatus", followStatus);
		
		request.setAttribute("eventOfThisUser", eventOfThisUser);
		request.setAttribute("profileOfThisUser", profileOfThisUser);
		
		
		request.setAttribute("currentUserEmail", currentUserEmail);
		
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/profileOtherUser.jsp");
		dispatch.forward(request,response);
	}

}
