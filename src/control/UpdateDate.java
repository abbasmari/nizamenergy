package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.LoanAndCustomerIdBAL;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/UpdateDate")
public class UpdateDate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateDate.class);

	public UpdateDate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String Id = request.getParameter("Appid");
			System.out.println("ye hy id" + Id);
			if (!Id.equals("")) {
				int appId = Integer.parseInt(Id);
				int loan_id = LoanAndCustomerIdBAL.getLoanid(appId);
				LoanAndCustomerIdBAL.updateDate(loan_id);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
}