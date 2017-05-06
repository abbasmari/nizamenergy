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
import org.json.JSONObject;

import bal.CallingXML;
import bal.SalesmanLogin;
import bal.telenor;
import bean.SalesmanLoginInfo;
import bean.UserBean;

@WebServlet("/AndrSalesmanLogin")
public class AndrSalesmanLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrSalesmanLogin.class);

	public AndrSalesmanLogin() {
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
			PrintWriter out = response.getWriter();
			String option = request.getParameter("option");
			String number = request.getParameter("number");
			JSONObject jsonObj = new JSONObject();

			if (option.equals("phone")) {
				String password = SalesmanLogin.checkNumber(number);

				if (password.equals("")) {
					jsonObj.put("status", 0);
				} else {
					jsonObj.put("status", 1);
					// CallingXML.SendMessage(number, password);
					telenor.SendSms(number, password);
				}
			} else if (option.equals("password")) {
				String pass = request.getParameter("pass");
				SalesmanLoginInfo custInfo = null; // SalesmanLogin.checkPasswrd(number,
													// pass);

				if (custInfo == null) {
					jsonObj.put("status", 0);
				} else {
					jsonObj.put("status", 1);
					jsonObj.put(SalesmanLoginInfo.SALESMAN_ID, custInfo.getSalesmanId());
					jsonObj.put(SalesmanLoginInfo.SALESMAN_PHONE, custInfo.getSalesmanPhone());
					jsonObj.put(SalesmanLoginInfo.SALESMAN_NAME, custInfo.getSalesmanName());
					jsonObj.put(SalesmanLoginInfo.SALESMAN_CNIC, custInfo.getSalesmanCnic());

					jsonObj.put(SalesmanLoginInfo.SALESMAN_TARGET, custInfo.getSalesmanTarget());
					jsonObj.put(SalesmanLoginInfo.TARGET_START_ON, custInfo.getTargetStartOn());
					jsonObj.put(SalesmanLoginInfo.TARGET_END_ON, custInfo.getTargetEndOn());

					jsonObj.put(SalesmanLoginInfo.BEFORE_TIME_PERC, custInfo.getBeforeTimePerc());
					jsonObj.put(SalesmanLoginInfo.ON_TIME_PERC, custInfo.getOnTimePerc());
					jsonObj.put(SalesmanLoginInfo.AFTER_TIME_PERC, custInfo.getAfterTimePerc());
					jsonObj.put(SalesmanLoginInfo.SALEMAN_JOIN_DATE, custInfo.getJoinDate());
					jsonObj.put(SalesmanLoginInfo.SALEMAN_IMG, custInfo.getSalemanImg());
				}

			} else if (option.equals("shareId")) {
				String app_reg_id = request.getParameter("app_reg_id");
				String salesmanId = request.getParameter("salesmanId");
				System.err.println(
						"option=> " + option + "   app_reg_id => " + app_reg_id + "    salesman_id=> " + salesmanId);
				int row = SalesmanLogin.addRegId(app_reg_id, salesmanId);
				if (row > 0) {
					jsonObj.put("status", 2);
				} else {
					jsonObj.put("status", 4);
				}
			}

			out.println(jsonObj);
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