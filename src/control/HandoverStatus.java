package control;

import bal.CallingXML;
import bal.StatusUpdateBAL;
import bal.telenor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.json.JSONException;

@WebServlet(name = "HandoverStatus", urlPatterns = { "/HandoverStatus" })
public class HandoverStatus extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HandoverStatus.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException, SQLException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			int customerid = Integer.parseInt(request.getParameter("customerId"));
			int applianceId = Integer.parseInt(request.getParameter("applianceId"));
			String arr[] = StatusUpdateBAL.updateDownPayment(customerid, applianceId);
			String message = "Dear Customer, Your downpayment has been recieved and after few hours you can get your device from DO Office";
			try {
				telenor.SendSms(arr[1], message);
			} catch (ServiceException e) {
				logger.error(e);
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("LoanRequest");
			rd.forward(request, response);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}