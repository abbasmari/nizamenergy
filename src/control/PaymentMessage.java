package control;

import bal.CustomerLoanBAL;
import bal.StatusUpdateBAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PaymentMessage", urlPatterns = { "/PaymentMessage" })
public class PaymentMessage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PaymentMessage.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			String GSMno = request.getParameter("productId");
			double amount = Double.parseDouble(request.getParameter("amount"));
			String transectionNo = request.getParameter("transectionNo");
			int applianceId = StatusUpdateBAL.getApplianceIdByGsm(GSMno);
			int soldId = StatusUpdateBAL.getSoldId(applianceId);
			int loanId = StatusUpdateBAL.getLoanId(soldId);
			double payment = StatusUpdateBAL.getMonthlyInstallment(loanId);
			CustomerLoanBAL.InsertLoanPayment(loanId, amount, payment, transectionNo);
			System.err.println(payment);
			StatusUpdateBAL.insertMessageForHandover(StatusUpdateBAL.getCustomerIdFromApplianceId(applianceId),
					"on," + StatusUpdateBAL.getGsmNumber(applianceId), StatusUpdateBAL.getGsmNumber(applianceId), 1);
			StatusUpdateBAL.updateApplianceStatus(applianceId, 1);
			out.println("ok");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(PaymentMessage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException ex) {
			Logger.getLogger(PaymentMessage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}