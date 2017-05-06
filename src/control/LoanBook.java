package control;

import bal.Payment_loanNewBAL;
import bean.MonthlyWisePayment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "LoanBook", urlPatterns = { "/LoanBook" })
public class LoanBook extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoanBook.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			int applianceId = Integer.parseInt(request.getParameter("applianceId"));
			String applianceName = request.getParameter("applianceName");
			List<MonthlyWisePayment> payment_Daywises = Payment_loanNewBAL.loanBookByApplianceId(applianceId,
					applianceName);
			String arr[] = Payment_loanNewBAL.getLoanOutStandingAndEndDateByApplianceId(applianceId);
			try {
				JSONArray jsonArray = new JSONArray();
				for (MonthlyWisePayment payment_Daywise : payment_Daywises) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("dueDate", payment_Daywise.getDue_date());
					jsonObject.put("paidAmount", payment_Daywise.getPaid_amount());
					jsonObject.put("paidDate", "" + payment_Daywise.getPaid_date());
					jsonObject.put("activateDays", "" + payment_Daywise.getDays_activated());
					jsonObject.put("transctionId", "" + payment_Daywise.getTrasnction_id());
					jsonObject.put("loanOutStanding", "" + arr[0]);
					jsonArray.put(jsonObject);

				}

				out.println(jsonArray);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}

		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
