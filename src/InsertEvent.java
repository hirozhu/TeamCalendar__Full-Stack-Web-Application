

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JDBC;

/**
 * Servlet implementation class InsertEvent
 */
@WebServlet("/InsertEvent")
public class InsertEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getContentType());
		Map<String, String[]> map = request.getParameterMap();
		for(Map.Entry<String, String[]> kv : map.entrySet()) {
			System.out.println(kv.getKey() + ": " + kv.getValue());
		}
		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");
		String email = request.getParameter("email");
		String id = request.getParameter("eventID");

		JDBC jdbc = new JDBC();
		jdbc.saveEvent(title,startDate,startTime,endDate,endTime,email,id);
	}

}
