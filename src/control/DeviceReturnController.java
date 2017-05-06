package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bal.ApplianceBAL;
import bal.mapBAL;
import bean.ApplianceBean;
import bean.InfowindowBean;
import bean.UserBean;

/**
 * Servlet implementation class DeviceReturnController
 */
@WebServlet("/DeviceReturnController")
public class DeviceReturnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DeviceReturnController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeviceReturnController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("click");
		if (option.equals("return")) {
			int applianceId = Integer.parseInt(request.getParameter("applianceId"));
			ApplianceBean bean = null;
			try {
				ApplianceBAL.updateReturned(applianceId);
				bean = ApplianceBAL.getApplianceInfo(applianceId);
			} catch (Exception e) {
				logger.error(e);
			}
			request.setAttribute("bean", bean);
			InfowindowBean bean1 = mapBAL.viewCashbean(applianceId);
			if (bean != null && session.getAttribute("latitud" + applianceId) == null && bean1 != null) {
				session.setAttribute("latitud" + bean.getApplianceId(), bean1.getLati());
				session.setAttribute("longi" + bean.getApplianceId(), bean1.getLongi());
			} else {
				session.setAttribute("latitud" + bean.getApplianceId(), 30.0);
				session.setAttribute("longi" + bean.getApplianceId(), 69.0);
			}
			RequestDispatcher rd = request.getRequestDispatcher("ApplianceView");
			rd.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// // TODO Auto-generated method stub
		// doGet(request, response);
	}

}
