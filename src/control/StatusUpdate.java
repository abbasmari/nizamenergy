package control;

import bal.CallingXML;
import bal.StatusUpdateBAL;
import bal.telenor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "StatusUpdate", urlPatterns = { "/StatusUpdate" })
public class StatusUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(StatusUpdate.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getRemoteAddr());
		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("verify")) {
				int applianceId = Integer.parseInt(request.getParameter("applianceId"));
				int customerId = Integer.parseInt(request.getParameter("customerId"));
				String applianceGsm = request.getParameter("applianceGsm");
				String applianceIMInumber = request.getParameter("applianceIMInumber");
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			} else if (option.equals("accept")) {
				int eligibilityId = Integer.parseInt(request.getParameter("eligibilityId"));
				StatusUpdateBAL.acceptLoanRequestBySuperadmin(eligibilityId);
				// String str = StatusUpdateBAL.getDatails(eligibilityId);
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			} else if (option.equalsIgnoreCase("acceptRejectAddOn")) {
				int addOn_id = Integer.parseInt(request.getParameter("addOn_id"));
				int trackingFlag = Integer.parseInt(request.getParameter("trackingFlag"));
				int returnFlag = Integer.parseInt(request.getParameter("returnFlag"));
				System.err.println("addOn_id "+addOn_id+"====  trackingFlag"+trackingFlag+"======= returnFlag"+returnFlag);
				StatusUpdateBAL.acceptLoanRequestBySuperadminAddOn(addOn_id, trackingFlag, returnFlag);
				// String str = StatusUpdateBAL.getDatails(eligibilityId);
				RequestDispatcher rd = request.getRequestDispatcher("/Request");
				rd.forward(request, response);
			}
			
			

		} catch (Exception e) {
			logger.error(e);
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
