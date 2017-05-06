package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import bal.TargetsBAL;
import bean.UserBean;

@WebServlet("/AndrGetDates")
public class AndrGetDates extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrGetDates.class);

	public AndrGetDates() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("salesmanId") != null) {
				int salemanId = Integer.parseInt(request.getParameter("salesmanId"));
				System.out.println(salemanId);
				JSONArray jArray = new JSONArray();
				try {
					List<String> endTarget = TargetsBAL.getTargetsDateForSales(salemanId);
					JSONObject date = null;

					for (String string : endTarget) {
						date = new JSONObject();
						date.put("endTarget", string);
						jArray.put(date);
					}
					out.println(jArray);

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
