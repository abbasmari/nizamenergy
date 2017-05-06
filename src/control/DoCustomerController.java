// created by Junaid Ali

package control;

import java.io.IOException;
import java.io.PrintWriter;
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

import bal.CustomerBAL;
import bal.SalesmanBAL;
import bean.UserBean;

/**
 * Servlet implementation class DoCustomerController
 */
@WebServlet("/DoCustomerController")
public class DoCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DoCustomerController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoCustomerController() {
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

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {

			if (request.getParameter("action").equalsIgnoreCase("getDoCustomerTable")) {
				int userDistrict = ubean.getUser_district();
				int doId = ubean.getUserId();
				System.out.println("+++++++++++ DO ID ++++++++++" + doId);
				System.out.println("+++++++++++ DO DISTRICT ++++++++++" + userDistrict);
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
				try {
					if (!search[0].isEmpty()) {
						int count = CustomerBAL.getDoSalesmanListSearchCount(search[0], userDistrict);
						JSONObject jobject = new JSONObject();
						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("customerCount", count);
						jobject.put("data",
								CustomerBAL.getDoCustomerSearchList(start, length, search[0], userDistrict));
						out.print(jobject);
					} else {
						int count = CustomerBAL.getDoSalesmanListCount(userDistrict);
						JSONObject jobject = new JSONObject();
						jobject.put("draw", draw);
						jobject.put("recordsTotal", count);
						jobject.put("recordsFiltered", count);
						jobject.put("customerCount", count);
						jobject.put("data",
								CustomerBAL.getDoCustomerTable(start, length, orderBy, orderDir, userDistrict));
						out.print(jobject);
					}
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
