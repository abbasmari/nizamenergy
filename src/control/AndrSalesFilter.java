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
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.SalesmanBAL;
import bal.TargetsBAL;
import bean.UserBean;

@WebServlet("/AndrSalesFilter")
public class AndrSalesFilter extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrSalesFilter.class);

	public AndrSalesFilter() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("salesmanId") != null) {
				int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
				String targetStartDate = request.getParameter("targetStartDate");
				String targetEndDate = request.getParameter("targetEndDate");
				int allowSales = SalesmanBAL.getTotalUnitsBySalesmanId(salesmanId, targetStartDate, targetEndDate);
				int recovery = TargetsBAL.getRecovries(salesmanId, targetStartDate, targetEndDate);
				int per = 0;
				if (allowSales > 0) {
					per = (100 / allowSales) * recovery;
				}
				System.out.println("Percentage : " + per);
				int rs = SalesmanBAL.getTotalAmountDateWiseBySalemanId(salesmanId, targetStartDate, targetEndDate);
				try {
					JSONArray jArray = new JSONArray();

					String[][] filtercustomers = CustomerBAL.getCustomerDateWiseBySalesmanId(salesmanId,
							targetStartDate, targetEndDate);
					JSONObject jsonObject1 = new JSONObject();
					jsonObject1.put("allowSales", allowSales);
					jsonObject1.put("rs", rs);
					jsonObject1.put("per", per);
					jsonObject1.put("salesAchive", filtercustomers.length);
					jArray.put(jsonObject1);
					for (String[] strings : filtercustomers) {
						JSONObject jsonObject = new JSONObject();

						jsonObject.put("customerId", strings[0]);
						jsonObject.put("customerName", strings[1] + "/" + strings[5]);
						jsonObject.put("customerCnic", strings[2]);
						jsonObject.put("customerPhone", strings[3]);
						jsonObject.put("customerStatus", strings[4]);
						jsonObject.put("applianceName", strings[5]);
						jsonObject.put("applianceGSMno", strings[6]);
						jsonObject.put("applianceStatus", strings[7]);
						jsonObject.put("cityName", strings[8]);
						jsonObject.put("applianceId", strings[9]);
						jsonObject.put("activatedDate", strings[10]);
						jsonObject.put("customerAdd", strings[11]);
						jArray.put(jsonObject);
					}
					System.out.println(jArray.toString());
					out.println(jArray);

				} catch (Exception e) {
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