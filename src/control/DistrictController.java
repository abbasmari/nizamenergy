package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import bal.DistrictBAL;
import bean.UserBean;

/**
 * Servlet implementation class DistrictController
 */
@WebServlet("/DistrictController")
public class DistrictController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DistrictController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DistrictController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String click = request.getParameter("click");
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		if (click != null && !click.isEmpty()) {
			PrintWriter out = response.getWriter();
			if (click.equals("getAllDistrictBySoldTo")) {
				DistrictBAL districtBAL = new DistrictBAL();
				ArrayList<HashMap<String, String>> allDistrictBySoldTo = districtBAL.getAllDistrictBySoldTo();
				JSONArray jsonArray = new JSONArray(allDistrictBySoldTo);
				out.print(jsonArray);
			}
		}
	}

}
