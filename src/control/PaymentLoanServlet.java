package control;

import bal.Payment_loanNewBAL;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.EncryptDecrypt;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "PaymentLoanServlet", urlPatterns = { "/PaymentLoanServlet" })
public class PaymentLoanServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(PaymentLoanServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("appliace_key");

			if (id != null) {
				int applianceId = Integer.parseInt((id));
				CustomerLoanBean bean = Payment_loanNewBAL.getApplianceDetailsForLoanAccount(applianceId);
				List<MonthlyWisePayment> monthlyPayments = Payment_loanNewBAL.loanBookByApplianceId(applianceId,
						bean.getApplianceName());
//				String arr[] = Payment_loanNewBAL.getLoanOutStandingAndEndDateByApplianceId(applianceId);
				request.setAttribute("appliance", bean);
				request.setAttribute("loanBook", monthlyPayments);
//				if (arr[0].equalsIgnoreCase("0")) {
//					request.setAttribute("terminatAt", "Paid Off");
//				} else {
//					request.setAttribute("terminatAt", arr[1]);
//				}

				RequestDispatcher rd = request.getRequestDispatcher("LoanView");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			logger.error("", e);
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
