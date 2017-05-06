package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

import bal.FinanceBAL;
import bean.FinanceBean;
import bean.UserBean;

/**
 * Servlet implementation class LateRecoveryController
 */
@WebServlet("/LateRecoveryController")
public class LateRecoveryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(finainceController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public LateRecoveryController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		// PrintWriter out = response.getWriter();
		try (PrintWriter out = response.getWriter()) {
			if (request.getParameter("action").equalsIgnoreCase("getpaymentorderby")) {

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
				int col = Integer.parseInt(orderByArrayString[0]);

				String[] orderDirArrayString = parameterMap.get("order[0][dir]");
				String order = orderDirArrayString[0];

				String[] search = parameterMap.get("search[value]");

				List<FinanceBean> list = null;
				int count = 0;
				double total_istallments = 0, down_payments = 0, total_amount = 0;
				if (!search[0].isEmpty()) {

					list = FinanceBAL.getLatePayments_Search_fliter(start, length, col, order, search[0]);
					count = FinanceBAL.getLateRecoverySearchCount(search[0]);

				} else {
					list = FinanceBAL.getLateRecovery_fliter(start, length, col, order);
					count = FinanceBAL.getLateRecoveryCount();
				}

				if (count > 0) {
					FinanceBean financeBean = list.get(0);
					total_istallments = financeBean.getTotal_installement_payments();
					down_payments = financeBean.getTotal_down_pament();
					// total_amount = financeBean.getTotal_amount();
					total_amount = down_payments + total_istallments;
				}

				JSONObject jobject = new JSONObject();
				jobject.put("draw", draw);
				jobject.put("recordsTotal", count);
				jobject.put("recordsFiltered", count);
				jobject.put("paymentsCount", count);
				jobject.put("data", list);

				jobject.put("total_istallments", total_istallments);
				jobject.put("down_payments", down_payments);
				jobject.put("total_amount", total_amount);

				out.print(jobject);

			}
		} catch (JSONException e) {
			logger.equals(e);
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
