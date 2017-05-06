package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.simple.JSONObject;

import bal.ApplianceBAL;
import bal.CustomerBAL;
import bal.Payment_loanNewBAL;
import bean.CustomerInfoBean;
import bean.UserBean;

/**
 * Servlet implementation class GetCustomer
 */
@WebServlet("/GetCustomer")
public class GetCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(GetCustomer.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
	
		String option = request.getParameter("click");
		String id = request.getParameter("id");
		if (option.equals("getExpansion")) {
			JSONArray json = new JSONArray();
			try {
				json.put(CustomerBAL.getCustomersAppliances(Integer.parseInt(id)));
			} catch (Exception e) {
				logger.error(e);
			}

			response.getWriter().print(json);
		} else if (option.equals("getRejectedExpansion")) {
			JSONArray json = new JSONArray();
			try {
				json.put(CustomerBAL.getRejectedAppliances(Integer.parseInt(id)));
			} catch (Exception e) {
				logger.error(e);
			}

			response.getWriter().print(json);
		}

		else if (option.equals("getCashCustomer")) {
			JSONArray json = new JSONArray();
			try {
				json.put(CustomerBAL.getCashCustomer(Integer.parseInt(id)));
			} catch (Exception e) {
				logger.error(e);
			}

			response.getWriter().print(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
