package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.UserBAL;
import bean.SearchBean;
import bean.UserBean;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/Search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Search.class);

	public Search() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("click");
		System.out.println("Click = " + option);
		if (option != null) {
			if (option.equals("searchapp")) {
				// out.print("<script>alert('doPost called')</script>");
				String applianceval = request.getParameter("applianceval");
				if (applianceval != null) {
					SearchBean bean = bal.SearchBAL.searchByAppNumber(applianceval);
					String email = request.getParameter("userEmail");
					String password = request.getParameter("userPassword");
					UserBean userbean = UserBAL.checkUser(email, password);
					request.setAttribute("email", userbean);
					request.setAttribute("search", bean);
					RequestDispatcher rd = request.getRequestDispatcher("CustomerServiceLogin");
					rd.forward(request, response);

				}
			} else if (option.equals("searchcustomer")) {
				System.out.println("YE BEAN HYugrhuyfht");
				String applianceval = request.getParameter("applianceval");
				if (applianceval != null) {
					ArrayList<SearchBean> list = bal.SearchBAL.searchByCustomerNumber(applianceval);
					System.out.println(list.isEmpty());
					request.setAttribute("searchlist", list);
					System.out.println("YE BEAN HY" + list);
					RequestDispatcher rd = request.getRequestDispatcher("CustomerServiceLogin");
					rd.forward(request, response);

				} else {
					System.out.print("else");
				}
			} else {
				System.out.println("else");
			}
		} else {
			System.out.print("else");
		}

	}
}