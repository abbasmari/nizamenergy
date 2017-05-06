package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

import bal.CustomerLoanBAL;
import bal.Payment_loanNewBAL;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;
import bean.UserBean;

/**
 * Servlet implementation class DoLoanBookController
 */
@WebServlet("/DoLoanBookController")
public class DoLoanBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoLoanBookController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoLoanBookController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("action");
		if (option.equals("viewloan")) {
			String id = request.getParameter("applianceId");
			if (id != null) {
				int applianceId = Integer.parseInt((id));
				CustomerLoanBean bean = Payment_loanNewBAL.getApplianceDetailsForLoanAccount(applianceId);
				List<MonthlyWisePayment> monthlyPayments = Payment_loanNewBAL.loanBookByApplianceId(applianceId,
						bean.getApplianceName());
				String arr[] = Payment_loanNewBAL.getLoanOutStandingAndEndDateByApplianceId(applianceId);

				request.setAttribute("appliance", bean);
				request.setAttribute("loanBook", monthlyPayments);
				if (arr[0].equalsIgnoreCase("0")) {
					request.setAttribute("terminatAt", "Paid Off");
				} else {
					request.setAttribute("terminatAt", arr[1]);
				}

				RequestDispatcher rd = request.getRequestDispatcher("doLoanbookView.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*");
		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		int userId = ubean.getUserId();

		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase("getDoLoanBookTable")) {
				Map<String, String[]> parameterMap = request.getParameterMap();

				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

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
				System.out.println("-**-* serach" + search[0]);
				int count = Payment_loanNewBAL.getDoLoanBookSearchCount(userId, search[0]);
				ArrayList<HashMap<String, String>> list = Payment_loanNewBAL.getDoLoanBook(start, length, orderDir,
						orderBy, userId, search[0]);
				System.out.println("-**-*" + count + "" + list);
				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("loanbook_count", count);
				jobject.put("data", list);
				out.print(jobject);

			}

			if (request.getParameter("action").equals("filterLoanBook")) {
				Map<String, String[]> parameterMap = request.getParameterMap();

				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]);

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

				if (filters[0].equals("maintained")) {
					count = payment_loanNewBAL.countDoLoanBookMaintained(userId, search[0]);
					loanBook = payment_loanNewBAL.getDoLoanBookMaintained(start, length, orderDir, orderBy, userId,
							search[0]);

				} else if (filters[0].equals("late")) {
					count = payment_loanNewBAL.countDoLoanBookLate(userId, search[0]);
					loanBook = payment_loanNewBAL.getDoLoanBookLate(start, length, orderDir, orderBy, userId,
							search[0]);
				} else if (filters[0].equals("defaulter")) {
					count = payment_loanNewBAL.countDoLoanBookDefaulter(userId, search[0]);
					loanBook = payment_loanNewBAL.getDoLoanBookDefaulter(start, length, orderDir, orderBy, userId,
							search[0]);
				} else if (filters[0].equals("owned")) {
					count = payment_loanNewBAL.countDoLoanBookOwned(userId, search[0]);
					loanBook = payment_loanNewBAL.getDoLoanBookOwned(start, length, orderDir, orderBy, userId,
							search[0]);
				}
				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("loanbook_count", count);
				jobject.put("data", loanBook);
				out.print(jobject);

			}

			if (request.getParameter("action").equals("countFilters")) {
				Payment_loanNewBAL payment_loanNewBAL = new Payment_loanNewBAL();
				HashMap<String, Integer> countLoanBookFilters = payment_loanNewBAL.countDoLoanBookFilters(userId);
				JSONObject jsonObject = new JSONObject(countLoanBookFilters);
				out.print(jsonObject);
			}

		} catch (JSONException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
