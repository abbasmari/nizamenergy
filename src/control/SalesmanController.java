package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.SalesmanBAL;
import bean.Salesman;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/SalesmanController")
public class SalesmanController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SalesmanController.class);

	public SalesmanController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String click = request.getParameter("click");
		System.out.println(click);
		if (click != null && !click.isEmpty()) {
			PrintWriter out = response.getWriter();
			if (click.equalsIgnoreCase("getSalesmansByDistrictId")) {
				JSONArray json = null;
				int districtId = Integer.parseInt(request.getParameter("districtid"));
				try {
					ArrayList<Salesman> salesmanList = SalesmanBAL.getSalesmanByDistrictId(districtId);
					json = new JSONArray(salesmanList);
				} catch (Exception e) {
					logger.error("", e);
				}
				out.println(json);
			}
			if (click.equalsIgnoreCase("getAllSalesman")) {
				SalesmanBAL salesmanBal = new SalesmanBAL();
				JSONArray json = null;
				try {
					ArrayList<HashMap<String, String>> allSalesmanBySoldTo = salesmanBal.getAllSalesmanBySoldTo();
					json = new JSONArray(allSalesmanBySoldTo);
				} catch (Exception e) {
					logger.error("", e);
				}
				out.println(json);
			}
		}
	}
}