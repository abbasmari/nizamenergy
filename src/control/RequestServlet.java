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

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.EligibilityBAL;
import bal.Payment_loanNewBAL;
import bean.UserBean;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(RequestServlet.class);

	public RequestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));
			if (request.getParameter("action").equals("getRequest")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
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

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = EligibilityBAL.getLoanRequest(start, length, orderBy,
							orderDir, search[0]);
					int count = EligibilityBAL.getLoanRequestCount(search[0]);

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("sa_loan_apps_counts", count);
					json.put("data", list);

				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("getDoRequest")) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
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

				JSONObject json = new JSONObject();
				try {
					ArrayList<HashMap<String, String>> list = EligibilityBAL.getDoLoanRequest(start, length, orderBy,
							orderDir, search[0], ubean.getUserId());
					int count = EligibilityBAL.getDoLoanRequestCount(search[0], ubean.getUserId());

					json.put("draw", draw);
					json.put("recordsTotal", count);
					json.put("recordsFiltered", count);
					json.put("loan_apps_count", count);
					json.put("data", list);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			}
			if (request.getParameter("action").equals("countFilters")) {
				try {
					HashMap<String, Integer> map = EligibilityBAL.countDoLoanRequestFilters(ubean.getUserId());
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			if (request.getParameter("action").equals("countSAFilters")) {
				try {
					HashMap<String, Integer> map = EligibilityBAL.countSALoanRequestFilters();
					JSONObject jsonObject = new JSONObject(map);
					out.print(jsonObject);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
			if (request.getParameter("action").equals("filterSALoanApplications")) {
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
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = EligibilityBAL.countSALoanRequestFiltersWithSearch(search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = EligibilityBAL.getSALoanRequestPending(start, length, orderBy, orderDir,
								search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = EligibilityBAL.getSALoanRequestAccepted(start, length, orderBy, orderDir,
								search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = EligibilityBAL.getSALoanRequestVarified(start, length, orderBy, orderDir,
								search[0]);
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("sa_loan_apps_counts", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);
			}
			if (request.getParameter("action").equals("filterLoanApplications")) {
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
				int count = 0;
				ArrayList<HashMap<String, String>> loanApplications = new ArrayList<>();
				HashMap<String, Integer> map = EligibilityBAL.countDoLoanRequestFiltersWithSearch(ubean.getUserId(),
						search[0]);

				if (filters[0].equals("pending")) {
					count = map.get("pending");
					try {
						loanApplications = EligibilityBAL.getDoLoanRequestPending(start, length, orderBy, orderDir,
								search[0], ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("accepted")) {
					count = map.get("accepted");
					try {
						loanApplications = EligibilityBAL.getDoLoanRequestAccepted(start, length, orderBy, orderDir,
								search[0], ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				} else if (filters[0].equals("varified")) {
					count = map.get("varified");
					try {
						loanApplications = EligibilityBAL.getDoLoanRequestVarified(start, length, orderBy, orderDir,
								search[0], ubean.getUserId());
					} catch (Exception e) {
						logger.error("", e);
						e.printStackTrace();
					}
				}
				JSONObject jobject = new JSONObject();
				try {
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("loan_apps_count", count);
					jobject.put("data", loanApplications);
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(jobject);

			}

		}

	}

}
