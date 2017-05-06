package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import bal.FinanceBAL;

@WebServlet("/Payments")
public class Payments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Payments() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			response.setContentType("application/json;charset=UTF-8");

			String action = request.getParameter("action");
			String userId = request.getParameter("userId");
			String userType = request.getParameter("userType");
			String amount = request.getParameter("amount");
			String status = request.getParameter("status");
			System.out.println(action + " => " + userId + " => " + userType + " => " + amount + " => " + status);

			JSONObject obj = new JSONObject();

			try {
				obj.put("status", "ok");
				if (action != null && !action.isEmpty() && userId != null && !userId.isEmpty() && userType != null
						&& !userType.isEmpty() && amount != null && !amount.isEmpty()) {
					if (action.equals("payment")) {
						FinanceBAL.addPayment(userId, userType, amount);

					} else if (action.equals("commission")) {

						FinanceBAL.updateCommission(userId, userType, amount, status);

					} else {
						obj.put("status", "error");
						obj.put("message", "action not found");
					}
				} else {
					obj.put("status", "error");
					obj.put("message", "parameters not found");
				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}

			out.print(obj);
		}
	}

}
