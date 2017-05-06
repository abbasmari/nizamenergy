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
import org.json.JSONException;
import org.json.JSONObject;

import bal.TargetsBAL;
import bean.UserBean;

@WebServlet("/AndrCommissionFo")
public class AndrCommissionFo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrCommissionFo.class);

	public AndrCommissionFo() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			try {
				HttpSession session = request.getSession();

				UserBean bean = (UserBean) session.getAttribute("email");
				logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
						+ request.getSession().getAttribute("ipAddress"));
				
				int foId = Integer.parseInt(request.getParameter("foId"));
				String startDate = request.getParameter("formDate");
				String endDate = request.getParameter("endDate");

				JSONArray jArray = new JSONArray();

				JSONObject BT50W = new JSONObject();
				BT50W.put("BT50W", TargetsBAL.getBeforeTimeForFO(startDate, endDate, foId, "50 W"));
				jArray.put(BT50W);

				JSONObject BT80W = new JSONObject();
				BT80W.put("BT80W", TargetsBAL.getBeforeTimeForFO(startDate, endDate, foId, "80 W"));
				jArray.put(BT80W);

				JSONObject BT100W = new JSONObject();
				BT100W.put("BT100W", TargetsBAL.getBeforeTimeForFO(startDate, endDate, foId, "100 W"));
				jArray.put(BT100W);

				JSONObject OT50W = new JSONObject();
				OT50W.put("OT50W", TargetsBAL.getOnTimeForFo(startDate, endDate, foId, "50 W"));
				jArray.put(OT50W);

				JSONObject OT80W = new JSONObject();
				OT80W.put("OT80W", TargetsBAL.getOnTimeForFo(startDate, endDate, foId, "80 W"));
				jArray.put(OT80W);

				JSONObject OT100W = new JSONObject();
				OT100W.put("OT100W", TargetsBAL.getOnTimeForFo(startDate, endDate, foId, "100 W"));
				jArray.put(OT100W);

				JSONObject AT50W = new JSONObject();
				AT50W.put("AT50W", TargetsBAL.getAfterTimeForFo(startDate, endDate, foId, "50 W"));
				jArray.put(AT50W);

				JSONObject AT80W = new JSONObject();
				AT80W.put("AT80W", TargetsBAL.getAfterTimeForFo(startDate, endDate, foId, "80 W"));
				jArray.put(AT80W);

				JSONObject AT100W = new JSONObject();
				AT100W.put("AT100W", TargetsBAL.getAfterTimeForFo(startDate, endDate, foId, "100 W"));
				jArray.put(AT100W);

				out.println(jArray);

			} catch (JSONException e) {
				e.printStackTrace();
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