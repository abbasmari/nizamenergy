package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import bal.ApplianceBAL;
import bal.CustomerBAL;
import bal.EligibilityBAL;
import bal.LoanPaymentBAL;
import bal.Payment_loanNewBAL;
import bal.StatusUpdateBAL;
import bal.telenor;
import bean.UserBean;

/**
 * Servlet implementation class Update_Installment
 */
@WebServlet("/Update_Installment")
public class Update_Installment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Update_Installment.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			proccess(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			proccess(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// response.sendRedirect("PaymentLoanServlet?appliace_key="+id);

	}

	protected void proccess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getRemoteAddr());

		String Amount = request.getParameter("Amount");
		String Reason = request.getParameter("Reason");
		String id = request.getParameter("id");
		String loan_id = request.getParameter("loan_id");

		Integer amount_int = Integer.parseInt(Amount);
		Integer loanid = Integer.parseInt(loan_id);
		Payment_loanNewBAL.updateLoan(loanid, amount_int);
		Payment_loanNewBAL.insert_other_loan(loanid, amount_int, Reason);
		System.out.println("Amount  " + Amount + " Amount Reason " + Reason + "ID=  " + id);
		response.sendRedirect("PaymentLoanServlet?appliace_key=" + id);
	}
}
