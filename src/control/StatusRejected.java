package control;

import bal.CustomerBAL;
import bal.StatusUpdateBAL;
import bean.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import javax.servlet.http.HttpSession;

@WebServlet(name = "StatusRejected", urlPatterns = { "/StatusRejected" })
public class StatusRejected extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(StatusRejected.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("ok")) {
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int applianceId = Integer.parseInt(request.getParameter("applianceId"));
				String text = request.getParameter("text");
				CustomerBAL.updateEligibility(customerId, applianceId, text);
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			}
			
			if (option.equals("rejectAddOn")) {
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				int applianceId = Integer.parseInt(request.getParameter("applianceId"));
				String text = request.getParameter("text");
//				CustomerBAL.updateEligibility(customerId, applianceId, text);
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			}
			
			
		} catch (Exception ex) {
			logger.error(ex);
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
