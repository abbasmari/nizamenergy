package control;

import bal.CallingXML;
import bal.SendmailBAL;
import bal.UserBAL;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "ForgotPassword", urlPatterns = { "/ForgotPassword" })
public class ForgotPassword extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ForgotPassword.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String option = request.getParameter("click");
			if (option.equalsIgnoreCase("forgot")) {
				String email = request.getParameter("email");
				int min = 1000;
				int max = 100000;
				int randomNum;
				System.out.println(UserBAL.checkUserEmail(email));
				if (UserBAL.checkUserEmail(email)) {
					Random rn = new Random();
					int n = max - min + 1;
					int i = rn.nextInt() % n;
					randomNum = min + i;
					System.out.println(randomNum);
					String password = "A" + randomNum;
					UserBAL.updateUserPassword(password, email);
					out.println("Password has been sent");
				}
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
