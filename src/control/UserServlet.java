package control;

import bean.UserBean;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "UserServlet", urlPatterns = { "/UserServlet" })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UserServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		UserBean bean = null;
		String option = request.getParameter("register");

		if (option.equals("signup")) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String gender = request.getParameter("gender");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (firstName != null && lastName != null && gender != null && dateOfBirth != null && email != null
					&& password != null) {
				bean = new UserBean();
				System.out.println(bean);

				request.setAttribute("msg", "Data Inserted");
				RequestDispatcher rd = request.getRequestDispatcher("Index");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Data Not Inserted");
				RequestDispatcher rd = request.getRequestDispatcher("Index");
				rd.forward(request, response);
			}
		}
	}
}