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

import bal.GetMessage;
import bean.UserBean;

/**
 * Servlet implementation class LoanBookAnalytics
 */
@WebServlet("/LoanBookAnalytics")
public class LoanBookAnalytics extends HttpServlet {
	final static Logger logger = Logger.getLogger(SmsAnalytics.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			System.out.println("this is SMS SERVLET");
			JSONArray jarray = new JSONArray();
			JSONObject jobject = new JSONObject();
			try {
				jobject.put("sms", GetMessage.get_FinanceGraph_LoanBook());
				jarray.put(jobject);
				out.print(jarray);
			} catch (JSONException e) {
				logger.error("", e);
				e.printStackTrace();
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
		System.out.println("this is foget of ");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("this is post of ");
		processRequest(request, response);
	}
}
