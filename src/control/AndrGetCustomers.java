package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
import bal.FieldOfficerBAL;
import bean.UserBean;

@WebServlet("/AndrGetCustomers")
public class AndrGetCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrGetCustomers.class);

	public AndrGetCustomers() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("salesmanId") != null) {
				try {
					int salemanId = Integer.parseInt(request.getParameter("salesmanId"));
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("Customers", CustomerBAL.getCustomersDetailBySalesmanId(salemanId));
					jsonObj.put("VLECounters", FieldOfficerBAL.getVLECounters(salemanId));
					out.print(jsonObj);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
