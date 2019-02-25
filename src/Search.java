

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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		String currentUserEmail = request.getParameter("currentUserEmail");
		System.out.println("Passed in email: " + currentUserEmail);
		
		JDBC jdbc = new JDBC();
		ArrayList<String[]> searchResult = jdbc.getSearchResult(search);
		
		request.setAttribute("searchResult", searchResult);
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher("/searchResult.jsp");
		dispatch.forward(request,response);
		
	}

}
