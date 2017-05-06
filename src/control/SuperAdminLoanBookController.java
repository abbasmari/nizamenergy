// created by Junaid Ali

package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.Payment_loanNewBAL;
import bal.SalesmanBAL;
import bean.UserBean;

/**
 * Servlet implementation class SuperAdminLoanBookController
 */
@WebServlet("/SuperAdminLoanBookController")
public class SuperAdminLoanBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SuperAdminLoanBookController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuperAdminLoanBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("action").equalsIgnoreCase("getLoanBookTable")) {

				Map<String, String[]> parameterMap = request.getParameterMap();
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");
				System.out.println("=================== cahed value" + search[0]);
				JSONObject jobject = new JSONObject();
				try {
					int count = Payment_loanNewBAL.getCountLoanBookSearchCount(search[0]);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data",
							Payment_loanNewBAL.getSuperAdminLoanBook(start, length, orderDir, orderBy, search[0]));
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);

			}
			if (request.getParameter("action").equals("filterLoanBook")) {
				Map<String, String[]> parameterMap = request.getParameterMap();

				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);
				// extract start from request parameter map
				String[] startStringArray = parameterMap.get("start");
				int start = Integer.parseInt(startStringArray[0]);

				// extract length / total records from request parameter map
				String[] lengthStringArray = parameterMap.get("length");
				int length = Integer.parseInt(lengthStringArray[0]);

				String[] orderByArrayString = parameterMap.get("order[0][column]");
				int orderBy = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap.get("order[0][dir]");
				String orderDir = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				String[] filters = parameterMap.get("filter");
				Payment_loanNewBAL payment_loanNewBAL = new Payment_loanNewBAL();
				int count = 0;
				ArrayList<HashMap<String, String>> loanBook = new ArrayList<>();
				JSONObject jobject = new JSONObject();
				try {
					if (filters[0].equals("maintained")) {
						count = payment_loanNewBAL.countLoanBookMaintained(search[0]);
						loanBook = payment_loanNewBAL.getLoanBookMaintained(start, length, orderDir, orderBy,
								search[0]);
					} else if (filters[0].equals("late")) {
						count = payment_loanNewBAL.countLoanBookLate(search[0]);
						loanBook = payment_loanNewBAL.getLoanBookLate(start, length, orderDir, orderBy, search[0]);
					} else if (filters[0].equals("defaulter")) {
						count = payment_loanNewBAL.countLoanBookDefaulter(search[0]);
						loanBook = payment_loanNewBAL.getLoanBookDefaulter(start, length, orderDir, orderBy, search[0]);
					} else if (filters[0].equals("owned")) {
						count = payment_loanNewBAL.countLoanBookOwned(search[0]);
						loanBook = payment_loanNewBAL.getLoanBookOwned(start, length, orderDir, orderBy, search[0]);
					}
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loanBooksCount", count);
					jobject.put("data", loanBook);
				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jobject);

			}
			if (request.getParameter("action").equals("countFilters")) {
				Payment_loanNewBAL payment_loanNewBAL = new Payment_loanNewBAL();
				HashMap<String, String> countLoanBookFilters = payment_loanNewBAL.countLoanBookFilters();
				JSONObject jsonObject = new JSONObject(countLoanBookFilters);
				out.print(jsonObject);
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
