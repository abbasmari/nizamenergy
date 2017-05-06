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
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.MessageNotificationsBal;
import bal.UserBAL;
import bean.DistrictOfficerList;
import bean.ServiceOperationBean;
import bean.ShowMsgAdminBean;
import bean.SuperAdminListBean;
import bean.UserBean;

/**
 * Servlet implementation class DoAlerts
 */
@WebServlet("/DoAlerts")
public class DoAlerts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoAlerts.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoAlerts() {
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
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		if (ubean != null) {
			String action = request.getParameter("action");

			if (action != null && !action.isEmpty()) {
				PrintWriter out = response.getWriter();
				int userId = ubean.getUserId();
				// Count Unseen Status Messages From Appliance
				if (action.equalsIgnoreCase("countUnseenMessagesFromAppliance")) {
					JSONObject json = new JSONObject();
					try {
						json.put("countAlerts", CustomerBAL.countUnseenMessagesFromAppliance(userId));
					} catch (JSONException e) {
						logger.error("", e);
					}
					out.print(json);
				}
				// Get Unseen Status Messages From Appliance
				if (action.equalsIgnoreCase("getUnseenMessagesFromAppliance")) {
					JSONArray json = null;
					try {
						json = new JSONArray(CustomerBAL.getUnseenMessagesFromAppliance(userId));
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
					out.print(json);
				}

			}
		}
	}

}
