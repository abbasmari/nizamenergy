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

import bal.ApplianceBAL;
import bean.UserBean;

/**
 * Servlet implementation class UpdateAppliance
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateAppliance" })
public class UpdateAppliance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateAppliance.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAppliance() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		response.setContentType("text/json;charset=UTF-8");
		String applianceGSM = request.getParameter("appGsm");
		String applianceImei = request.getParameter("appImei");
		applianceGSM = "92" + applianceGSM.replace("-", "");
		int appId = Integer.parseInt(request.getParameter("appId"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		System.out.println("customerId : " + customerId);
		String number = ApplianceBAL.updateAppliance(applianceGSM,
				applianceImei, appId, customerId);
		JSONObject json = new JSONObject();
		try (PrintWriter out = response.getWriter()) {
			json.put("data", number);
			out.print(json);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
