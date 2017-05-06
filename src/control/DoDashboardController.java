package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.ApplianceBAL;
import bean.UserBean;

/**
 * Servlet implementation class DoDashboardController
 */
@WebServlet("/DoDashboardController")
public class DoDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoDashboardController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoDashboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		int districtId = ubean.getUser_district();

		String action = request.getParameter("action");
		try (PrintWriter out = response.getWriter()) {
			if (action.equalsIgnoreCase("getDoDonut")) {
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("appliancePercent", ApplianceBAL.getDoDonutChart(districtId));
					out.print(jobject);
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		}

	}

}
