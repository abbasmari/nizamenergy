package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.FieldOfficerBAL;
import bean.UserBean;

/**
 * Servlet implementation class FoRemovingController
 */
@WebServlet("/FoRemovingController")
public class FoRemovingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FoRemovingController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoRemovingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("click");
		
		if (option.equals("remove")) {
			int foId = Integer.parseInt(request.getParameter("foId"));
			System.out.println(foId);
			JSONObject json = null;
			try {
				HashMap<String, String> map = FieldOfficerBAL.checkFoHaveNetwork(foId);
				json = new JSONObject(map);
			} catch (Exception e) {
				logger.error(e);
			}
			out.println(json);
		} else if (option.equals("removeNd")) {
			int foId = Integer.parseInt(request.getParameter("ndId"));
			System.out.println(foId);
			JSONObject json = null;
			try {
				HashMap<String, String> map = FieldOfficerBAL.checkSalesmanHaveNetwork(foId);
				json = new JSONObject(map);
			} catch (Exception e) {
				logger.error(e);
			}
			out.println(json);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String option = request.getParameter("click");
		if (option.equals("ok")) {
			int foId = Integer.parseInt(request.getParameter("foId"));
			try {
				String str = FieldOfficerBAL.updateFo(foId);
			} catch (Exception e) {
				logger.error(e);
			}
			response.sendRedirect("superAdminFO.jsp");
		} else if (option.equals("removNd")) {
			int ndId = Integer.parseInt(request.getParameter("ndId"));
			try {
				FieldOfficerBAL.updateNizamDost(ndId);
			} catch (Exception e) {
				logger.error(e);
			}
			response.sendRedirect("Salesmen");
		}
	}

}
