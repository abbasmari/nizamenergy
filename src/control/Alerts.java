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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.MessageNotificationsBal;
import bal.UserBAL;
import bean.DistrictOfficerList;
import bean.ServiceOperationBean;
import bean.ShowMsgAdminBean;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(asyncSupported = true, urlPatterns = { "/Alerts" })
public class Alerts extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Alerts.class);

	public Alerts() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");

		if (action != null && !action.isEmpty()) {
			PrintWriter out = response.getWriter();
			// Count Unseen Status Messages From Appliance
			if (action.equalsIgnoreCase("countUnseenSAAlarms")) {
				JSONObject json = new JSONObject();
				try {
					json.put("countAlerts", CustomerBAL.countUnseenMessagesFromAppliance());
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			// Get Unseen Status Messages From Appliance
			if (action.equalsIgnoreCase("getUnseenSAAlarms")) {
				JSONArray json = null;
				try {
					json = new JSONArray(CustomerBAL.getUnseenMessagesFromAppliance());
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
		}
	}

}
