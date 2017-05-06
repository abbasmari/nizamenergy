package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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

import bean.UserBean;
import schedule.ScheduleBAL;

/**
 * Servlet implementation class SuspendedCustomer
 */
@WebServlet("/SuspendedCustomer")
public class SuspendedCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SuspendedCustomer.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuspendedCustomer() {
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

		PrintWriter out = response.getWriter();
		ArrayList<HashMap<String, String>> list = ScheduleBAL.getSuspended();
		JSONObject jobject = new JSONObject();
		JSONArray jarr = new JSONArray();

		for (HashMap<String, String> hashMap : list) {
			System.err.println(hashMap.get("customer_id"));
			try {
				jobject.put("suspendedlist", hashMap);
			} catch (JSONException e) {
				logger.error(e);
				e.printStackTrace();
			}
			jarr.put(jobject);
			out.print(jobject);
		}

		request.setAttribute("suspendedlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("Customer");
		rd.forward(request, response);
	}

}
