package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.SalesmanBAL;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/SalesmanBarChart")
public class SalesmanBarChart extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SalesmanBarChart.class);

	public SalesmanBarChart() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			JSONObject jobject = new JSONObject();
			try {
				System.out.println("============request get " + request.getParameter("salesmanMonth"));
				String dateGet = request.getParameter("salesmanMonth");
				String yearGet = request.getParameter("salesmanYear");
				System.out.println("============== request year get " + yearGet);
				jobject.put("yearValues", SalesmanBAL.getToltaYearsOfSalesmans());

				jobject.put("salesman", SalesmanBAL.getAllSalesmansCommission(0.10, 0.08, 0.04, yearGet));

				jobject.put("salesmansRecoveries", SalesmanBAL.getSuperAdminSalesmansRecoveriesTotal(dateGet));

				jobject.put("dateValues", SalesmanBAL.getCurrentMonthlDates());

				out.print(jobject);
			} catch (JSONException e) {
				logger.error(e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}