package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.SalesmanBAL;
import bean.UserBean;

/**
 * Servlet implementation class DoSalesmanController
 */
@WebServlet("/DoSalesmanController")
public class DoSalesmanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoSalesmanController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoSalesmanController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("action").equalsIgnoreCase("getDoSalesmanTable")) {

				int foId = Integer.parseInt(request.getParameter("foId"));
				int userDistrict = ubean.getUser_district();
				int doId = ubean.getUserId();
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
				System.out.println("=================== cahed value" + search[0]);

				if (!search[0].isEmpty()) {
					int count = SalesmanBAL.getDoSalesmanListSearchCount(orderDir, orderBy, doId, foId, search[0]);
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("salesmanCount", count);
					jobject.put("data", SalesmanBAL.getDoSalesmanListSearch(start, length, orderDir, orderBy, doId,
							foId, search[0]));
					out.print(jobject);
				} else {
					int count = SalesmanBAL.getDoSalesmanListCount(orderDir, orderBy, doId, foId);
					JSONObject jobject = new JSONObject();
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("salesmanCount", count);
					jobject.put("data", SalesmanBAL.getDoSalesmanList(start, length, orderDir, orderBy, doId, foId));
					out.print(jobject);
				}

			} else if (request.getParameter("action").equalsIgnoreCase("doView")) {

				int id = Integer.parseInt(request.getParameter("sali"));
				System.out.println("********************** salesman id" + id);

				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]); // (Page
																	// No/Start
																	// Position)

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

				if (!search[0].isEmpty()) {

					int count = SalesmanBAL.getDoCustomersWithSalesmanIdSearchCount(search[0], id);
					// JSONArray jarray = new JSONArray();
					JSONObject jobject = new JSONObject();
					// // System.out.println(searchLetter);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("salesmanCount", count);
					jobject.put("data", SalesmanBAL.getDoCustomersWithSalesmanIdSearch(start, length, search[0], id));
					out.print(jobject);
				} else {

					int count = SalesmanBAL.getDoCustomersWithSalesmanIdCount(id);
					// JSONArray jarray = new JSONArray();
					JSONObject jobject = new JSONObject();
					// System.out.println(searchLetter);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("salesmanCount", count);
					jobject.put("data", SalesmanBAL.getDoCustomersWithSalesmanId(start, length, orderBy, orderDir, id));
					// jarray.put(jobject);
					out.print(jobject);
				}

				// int id = Integer.parseInt(request.getParameter("sali"));
				// System.out.println("********************** salesman id"+id);
				// JSONObject jobject = new JSONObject();
				// jobject.put("data",
				// SalesmanBAL.getDoCustomersWithSalesmanId(id));
				// out.print(jobject);

			} else if (request.getParameter("action").equalsIgnoreCase("doDeployment")) {

				int userId = ubean.getUserId();

				Map<String, String[]> parameterMap = request.getParameterMap();
				// extract draw or page no from request parameter map
				String[] drawStringArray = parameterMap.get("draw");
				int draw = Integer.parseInt(drawStringArray[0]); // (Page
																	// No/Start
																	// Position)

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

				if (!search[0].isEmpty()) {

					int count = SalesmanBAL.getDoDeploymentListSearchCount(orderDir, orderBy, userId, search[0]);
					// JSONArray jarray = new JSONArray();
					JSONObject jobject = new JSONObject();
					// // System.out.println(searchLetter);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data",
							SalesmanBAL.getDoDeploymentListSearch(start, length, orderDir, orderBy, userId, search[0]));
					out.print(jobject);
				} else {

					int count = SalesmanBAL.getDoDeploymentListCount(orderDir, orderBy, userId);
					// JSONArray jarray = new JSONArray();
					JSONObject jobject = new JSONObject();
					// System.out.println(searchLetter);
					jobject.put("draw", draw);
					jobject.put("recordsTotal", count);
					jobject.put("recordsFiltered", count);
					jobject.put("deploymentCount", count);
					jobject.put("data", SalesmanBAL.getDoDeploymentList(start, length, orderDir, orderBy, userId));
					// jarray.put(jobject);
					out.print(jobject);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
