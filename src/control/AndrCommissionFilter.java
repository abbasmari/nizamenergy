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

@WebServlet("/AndrCommissionFilter")
public class AndrCommissionFilter extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrCommissionFilter.class);
	public AndrCommissionFilter() {
		super();
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			try {
				HttpSession session = request.getSession();

		    	UserBean bean = (UserBean) session.getAttribute("email");
		    	logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
						+ request.getSession().getAttribute("ipAddress"));
				
				int salesmanId = Integer.parseInt(request
						.getParameter("salesmanId"));
				String targetStartDate = request
						.getParameter("targetStartDate");
				String targetEndDate = request.getParameter("targetEndDate");
				String bt = "0." + request.getParameter("beforeTime");
				String ot = "0." + request.getParameter("onTime");
				String at = "0." + request.getParameter("afterTime");
				double bbt = Double.parseDouble(bt);
				double oot = Double.parseDouble(ot);
				double aat = Double.parseDouble(at);

				System.out.println("bt " + request.getParameter("beforeTime"));
				System.out.println("bt after roundOff " + bt);

				JSONArray jArray = new JSONArray();

				JSONObject BT50W = new JSONObject();
				BT50W.put("BT50W", TargetsBAL.getBeforeTime(targetStartDate,
						targetEndDate, salesmanId, "50 W"));
				jArray.put(BT50W);

				JSONObject BT80W = new JSONObject();
				BT80W.put("BT80W", TargetsBAL.getBeforeTime(targetStartDate,
						targetEndDate, salesmanId, "80 W"));
				jArray.put(BT80W);

				JSONObject BT100W = new JSONObject();
				BT100W.put("BT100W", TargetsBAL.getBeforeTime(targetStartDate,
						targetEndDate, salesmanId, "100 W"));
				jArray.put(BT100W);

				JSONObject OT50W = new JSONObject();
				OT50W.put("OT50W", TargetsBAL.getOnTime(targetStartDate,
						targetEndDate, salesmanId, "50 W"));
				jArray.put(OT50W);

				JSONObject OT80W = new JSONObject();
				OT80W.put("OT80W", TargetsBAL.getOnTime(targetStartDate,
						targetEndDate, salesmanId, "80 W"));
				jArray.put(OT80W);

				JSONObject OT100W = new JSONObject();
				OT100W.put("OT100W", TargetsBAL.getOnTime(targetStartDate,
						targetEndDate, salesmanId, "100 W"));
				jArray.put(OT100W);

				JSONObject AT50W = new JSONObject();
				AT50W.put("AT50W", TargetsBAL.getAfterTime(targetStartDate,
						targetEndDate, salesmanId, "50 W"));
				jArray.put(AT50W);

				JSONObject AT80W = new JSONObject();
				AT80W.put("AT80W", TargetsBAL.getAfterTime(targetStartDate,
						targetEndDate, salesmanId, "80 W"));
				jArray.put(AT80W);

				JSONObject AT100W = new JSONObject();
				AT100W.put("AT100W", TargetsBAL.getAfterTime(targetStartDate,
						targetEndDate, salesmanId, "100 W"));
				jArray.put(AT100W);

				JSONObject callable = new JSONObject();
				callable.put("TARGETDATE", TargetsBAL
						.getDateDurationOfCustomerWithData(salesmanId, bbt,
								oot, aat));
				jArray.put(callable);

				List<String> endTarget = TargetsBAL
						.getTargetsDateForSales(Integer.parseInt(request
								.getParameter("salesmanId")));

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

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}