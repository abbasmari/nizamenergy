package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.SalesmanBAL;
import bean.UserBean;

/**
 * Servlet implementation class MobileSalesmanController
 */
@WebServlet("/m.SalesmanController")
public class MobileSalesmanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Message.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MobileSalesmanController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		String action = request.getParameter("action");
		try (PrintWriter out = response.getWriter()) {
			JSONObject json = new JSONObject();
			if (action != null) {
				if (!action.isEmpty()) {
					if (action.equals("getAcceptedCustomers")) {
						String salesmanIdString = request.getParameter("salesmanId");
						if (salesmanIdString != null) {
							if (!salesmanIdString.isEmpty()) {
								int salesmanId = Integer.parseInt(salesmanIdString);
								JSONArray jsonArray = new JSONArray(SalesmanBAL.getSalesmanAcceptedRequest(salesmanId));
								json.put("salesman", jsonArray);
							}
						}
					} else if (action.equals("getSalesman")) {
						String salesmanIdString = request.getParameter("salesmanId");
						try {
							if (salesmanIdString != null) {
								if (!salesmanIdString.isEmpty()) {
									json.put("status", "ok");
									json.put("salesman",
											SalesmanBAL.getSalesManByID(Integer.parseInt(salesmanIdString)));
								} else {
									json.put("status", "error");
									json.put("message", "salesmanId is not initialized");
								}
							} else {
								json.put("status", "error");
								json.put("message", "salesmanId is not declared");
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {

					}

				}
			}
			out.println(json);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}
