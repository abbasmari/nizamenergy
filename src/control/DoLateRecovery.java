package control;

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

@WebServlet(name = "DoLateRecovery", urlPatterns = { "/DoLateRecovery" })
public class DoLateRecovery extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoLateRecovery.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String id = request.getParameter("salesman_id");
			if (id != null) {
				int SalesmanId = Integer.parseInt(id);
				System.out.println("Salesman Id" + SalesmanId);
				request.setAttribute("salesman_id", SalesmanId);
				RequestDispatcher rd = request.getRequestDispatcher("DoLatePaymentRecord");
				rd.forward(request, response);
			}
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
