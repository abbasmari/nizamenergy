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
import org.json.JSONException;
import org.json.JSONObject;

import bal.SalesmanBAL;
import bean.UserBean;

@WebServlet("/AndroidSales")
public class AndroidSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndroidSales.class);

	public AndroidSales() {
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
				int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				JSONObject jsonObject = null;
				try {
					int number = SalesmanBAL.getNumberOfSoldApplianceBySalesmanId(salesmanId);
					int curMonSell = SalesmanBAL.getTotalAmountDateWiseBySalemanId(salesmanId, startDate, endDate);
					jsonObject = new JSONObject();
					jsonObject.put("appliences", number);
					jsonObject.put("cur_mon_sell", curMonSell);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
				out.print(jsonObject);
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
