package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import bal.Payment_loanNewBAL;
import bal.ReportAnalyticsBAL;
import bean.UserBean;

/**
 * Servlet implementation class FutureLoanBookController
 */
@WebServlet("/FutureLoanBookController")
public class FutureLoanBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger
			.getLogger(FutureLoanBookController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FutureLoanBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("application/json");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("action").equals("getFutureLoanBooks")) {
				try {
					ArrayList<HashMap<String, String>> map = Payment_loanNewBAL
							.futureLoanBooks();
					JSONArray json = new JSONArray(
							Payment_loanNewBAL.futureLoanBooks());
					out.println(json);
					System.err.println("getLoanBookTableToDate: ===== " + map);
				} catch (Exception e) {
					logger.error("", e);
				}
			}
		}
	}
}
