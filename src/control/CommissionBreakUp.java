package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.CommissionBreakUpBAL;
import bean.CommissionBreakUpBean;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/CommissionBreakUp")
public class CommissionBreakUp extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CommissionBreakUp.class);

	public CommissionBreakUp() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));
			if (request.getParameter("salesmanId") != null) {
				int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
				String applianceName = request.getParameter("applianceName");
				String targetEndDate = request.getParameter("targetEndDate");
				String timeLimit = request.getParameter("paidTime");
				System.out.println(targetEndDate);
				List<CommissionBreakUpBean> customerList = CommissionBreakUpBAL.getCustomerList(salesmanId,
						applianceName, targetEndDate, timeLimit);
				request.setAttribute("customerList", customerList);
				RequestDispatcher rs = request.getRequestDispatcher("CommissionBreakup");
				rs.forward(request, response);
			}

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
