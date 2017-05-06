package control;

import bal.CallingXML;
import bal.SalesmanBAL;
import bean.SalesManBean;
import bean.lat_long_bean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Test.class);

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("ok")) {
				String id = request.getParameter("salesman_id");
				if (id != null) {
					int SalesmanId = Integer.parseInt(id);
					SalesManBean salesman = SalesmanBAL.getSalesManByID(Integer
							.parseInt(id));
					request.setAttribute("salesman", salesman);
					RequestDispatcher rd = request
							.getRequestDispatcher("SalesManView");
					rd.forward(request, response);
				}
			} else if (option.equals("vleprofile")) {
				String id = request.getParameter("salesman_id");
				if (id != null) {
					int SalesmanId = Integer.parseInt(id);
					SalesManBean salesman = SalesmanBAL.getSalesManByID(Integer
							.parseInt(id));
					request.setAttribute("salesman", salesman);
					RequestDispatcher rd = request
							.getRequestDispatcher("doVleProfile.jsp");
					rd.forward(request, response);
				}
			} else if (option.equals("dopage")) {

				String id = request.getParameter("salesman_id");
				String time = request.getParameter("time");
				if (id != null) {
					int SalesmanId = Integer.parseInt(id);
					SalesManBean bean = SalesmanBAL.getSalesManByID(SalesmanId);
					request.setAttribute("bean", bean);
					request.setAttribute("salesman_id", SalesmanId);
					request.setAttribute("time", time);
					RequestDispatcher rd = request
							.getRequestDispatcher("DistrictSalesmanIncome");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
