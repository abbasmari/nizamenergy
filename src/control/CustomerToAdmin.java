package control;

import bal.CustomerBAL;
import bal.StatusUpdateBAL;
import bean.CustomerMessageBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import bean.UserBean;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CustomerToAdmin", urlPatterns = { "/CustomerToAdmin" })
public class CustomerToAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Payout.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String number = request.getParameter("number");
			String message = request.getParameter("message");
			int id = StatusUpdateBAL.getCustomerPhoneById(number);
			CustomerMessageBean messageBean = null;
			messageBean = new CustomerMessageBean();
			messageBean.setCustomerId(id);
			messageBean.setGsmNumber(number);
			messageBean.setMessage(message);
			if (message != null && number != null && id != 0) {
				CustomerBAL.insertMessageIntoCustomer(messageBean);
			}

		} catch (SQLException ex) {
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
