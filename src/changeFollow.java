

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;

/**
 * Servlet implementation class changeFollow
 */
@WebServlet("/changeFollow")
public class changeFollow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeFollow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----------In changeFollow Servlet-----------" );
		
		String currentUserEmail = request.getParameter("currentUserEmail");
		String otherEmail = request.getParameter("otherEmail");
		
		System.out.println("Current User Email: ----------" + currentUserEmail);
		//String followStatus = request.getParameter("followStatus");
		
		JDBC jdbc = new JDBC();
		
		jdbc.changeFollow(currentUserEmail, otherEmail);
		
		//RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/profileOtherUser.jsp");
		//dispatch.forward(request,response);
	}

}
