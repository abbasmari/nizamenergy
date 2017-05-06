package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import bal.ApplianceBAL;
import bal.CustomerBAL;
import bal.Payment_loanNewBAL;
import bean.ApplianceBean;
import bean.CustomerInfoBean;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "customerService", urlPatterns = { "/customerService" })
public class customerService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(customerService.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));


			String gsmno = request.getParameter("gsm");

			System.out.println(gsmno);
			CustomerInfoBean bean = CustomerBAL.getCustomerLoanDetails(gsmno);
			ApplianceBean bean1 = ApplianceBAL.getApplianceInfo(bean.getApplianceId());
			CustomerLoanBean customer12 = CustomerBAL.getCustomer_details(bean.getCustomerId(), bean.getApplianceId());
			ArrayList<MonthlyWisePayment> loans1 = Payment_loanNewBAL.payment_loan_history(bean.getSoldId());

			System.out.println("THIS IS CUSTOMER NAME" + customer12.getCustomerName());
			JSONArray json = new JSONArray();
			try {

				json.put(bean1);
				json.put(customer12);

				json.put(loans1);
			} catch (Exception e) {
			}
			response.getWriter().print(json);
			System.out.println(json);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
