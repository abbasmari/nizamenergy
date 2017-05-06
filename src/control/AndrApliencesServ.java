package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import bal.ApplianceBAL;
import bean.CustomerAnd;
import bean.UserBean;

@WebServlet("/AndrApliencesServ")
public class AndrApliencesServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrApliencesServ.class);

	public AndrApliencesServ() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try {
			response.setContentType("application/json;charset=UTF-8");
			String salemanId = request.getParameter("salemanId");
			PrintWriter out = response.getWriter();
			if (salemanId != null) {
				JSONObject obj = new JSONObject();
				JSONArray arr = null;
				List<CustomerAnd> customers = null;
				try {
					customers = ApplianceBAL.getAcceptedAppliencesBySalemanId(Integer.parseInt(salemanId));
					arr = new JSONArray();
					for (CustomerAnd customer : customers) {

						JSONObject custObj = new JSONObject();
						custObj.put("cust_name", customer.getName());
						custObj.put("apl_name", customer.getAplName());
						arr.put(custObj);

					}
					obj.put("accepted_applience", arr);
					arr = new JSONArray();
					customers = ApplianceBAL.getActiveAppliencesBySalemanId(Integer.parseInt(salemanId));
					for (CustomerAnd customer : customers) {
						JSONObject custObj = new JSONObject();
						custObj.put("cust_name", customer.getName());
						custObj.put("apl_name", customer.getAplName());
						arr.put(custObj);

					}
					obj.put("active_applience", arr);

				} catch (JSONException ex) {
					ex.printStackTrace();
				}
				out.print(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
