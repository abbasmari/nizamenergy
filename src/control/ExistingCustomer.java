package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bal.AddCustomerBAL;

import bean.UserBean;
import org.apache.log4j.Logger;

@WebServlet("/ExistingCustomer")
public class ExistingCustomer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ExistingCustomer.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("click");

		if (option.equalsIgnoreCase("execustomer")) {

			int exe_customerid = Integer.parseInt(request.getParameter("exe_customerid"));
			int exe_salesmanid = Integer.parseInt(request.getParameter("exe_salesmanid"));
			// for Customer

			int exescheme = Integer.parseInt(request.getParameter("exescheme"));
			double exedownPayment = (double) (Integer.parseInt(request.getParameter("exedownPayment")));
			double exeinstallment = (double) (Integer.parseInt(request.getParameter("exeinstallment")));
			String exappname = request.getParameter("exappname");
			String exe_gsm = request.getParameter("exe_gsm");

			double exeprice = 0;
			if (exappname.equals("50 W")) {
				exeprice = 32000;
			} else if (exappname.equals("80 W")) {
				exeprice = 46000;
			} else if (exappname.equals("100 W")) {
				exeprice = 60000;
			}

			int exe_applianceId = 0;
			try {
				exe_applianceId = AddCustomerBAL.SaveAppliance2(exe_gsm, exappname, exeprice, 0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			try {
				AddCustomerBAL.saveEligibilty2(exe_customerid, exe_applianceId, exe_salesmanid, exedownPayment,
						exescheme, exeinstallment);
				RequestDispatcher rd = request.getRequestDispatcher("CustomerForm");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
