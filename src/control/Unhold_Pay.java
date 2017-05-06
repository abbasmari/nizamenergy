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

import org.apache.log4j.Logger;
import org.json.JSONException;

import bal.LoanPaymentBAL;
import bean.UserBean;

/**
 * Servlet implementation class Unhold_Pay
 */
@WebServlet("/Unhold_Pay")
public class Unhold_Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Unhold_Pay.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException, SQLException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		response.setContentType("text/html;charset=UTF-8");

		String type = request.getParameter("type");
		String payer_id = request.getParameter("fo_id");
		System.out.println("Type  " + type + "   payer_id " + payer_id);
		int type_int = Integer.parseInt(type);
		int payer_id_int = Integer.parseInt(payer_id);
		LoanPaymentBAL.Pay_commission_onhold(type_int, payer_id_int);

		RequestDispatcher rd = request.getRequestDispatcher("commissionPayout.jsp");
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet");

		try {
			processRequest(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		System.out.println("DoPost");
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
