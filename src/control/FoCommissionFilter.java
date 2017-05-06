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

/**
 * Servlet implementation class FoCommissionFilter
 */
@WebServlet("/FoCommissionFilter")
public class FoCommissionFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FoCommissionFilter.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoCommissionFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			try {
				int foId = Integer.parseInt(request.getParameter("foId"));
				String startDate = request.getParameter("formDate");
				String endDate = request.getParameter("endDate");
				String bt = "0." + request.getParameter("beforeTime");
				String ot = "0." + request.getParameter("onTime");
				String at = "0." + request.getParameter("afterTime");
				double bbt = Double.parseDouble(bt);
				double oot = Double.parseDouble(ot);
				double aat = Double.parseDouble(at);

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
				// }
			} catch (JSONException e) {
				logger.error(e);
				e.printStackTrace();
			}

		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
