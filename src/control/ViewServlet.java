package control;

import bal.AlertsBAL;
import bal.ApplianceBAL;
import bal.CustomerBAL;
import bal.CustomerRetrieveDataBAL;
import bal.mapBAL;
import bean.AlertsBean;
import bean.ApplianceBean;
import bean.CustomerProfileViewBean;
import bean.InfowindowBean;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import security.EncryptDecrypt;
import bean.UserBean;

import org.apache.log4j.Logger;

@WebServlet(name = "ViewServlet", urlPatterns = { "/ViewServlet" })
public class ViewServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ViewServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		PrintWriter out = response.getWriter();
		try {
			String option = request.getParameter("click");
			if (option.equals("doview")) {
				String cnic = request.getParameter("cnic");
				int appID = Integer.parseInt(request.getParameter("applianceId"));
				if (cnic != null) {
					CustomerProfileViewBean customerList = CustomerRetrieveDataBAL.getCustomerProfile(cnic);
					request.setAttribute("customerInformation", customerList);
					request.setAttribute("appId", appID);
					request.setAttribute("key", 2);
					RequestDispatcher rd = request.getRequestDispatcher("doCustomerProfile.jsp");
					rd.forward(request, response);
				}
			} // end of if
			else if (option.equals("view")) {
				String cnic = request.getParameter("cnic");
				int appID = Integer.parseInt(request.getParameter("applianceId"));
				if (cnic != null) {
					CustomerProfileViewBean customerList = CustomerRetrieveDataBAL.getCustomerProfile(cnic);
					System.out.println("Locals" + customerList);
					request.setAttribute("customerInformation", customerList);
					request.setAttribute("appId", appID);
					request.setAttribute("key", 2);
					/* response.sendRedirect("ProfileV2"); */
					RequestDispatcher rd = request.getRequestDispatcher("ProfileV2");
					rd.forward(request, response);
				}
			} // end of else if
			else if (option.equals("viewRequest")) {
				String cnic = request.getParameter("cnic");
				String appname = request.getParameter("appName");
				String appId = request.getParameter("appId");
				if (cnic != null) {
					CustomerProfileViewBean customerList = CustomerRetrieveDataBAL.getCustomerProfile(cnic);
					request.setAttribute("customerInformation", customerList);
					request.setAttribute("appName", appname);
					request.setAttribute("appId", appId);
					request.setAttribute("key", 1);
					RequestDispatcher rd = request.getRequestDispatcher("ProfileV2");
					rd.forward(request, response);

				}
			} // end of else if

			else if (option.equals("viewAppliance")) {
				try {
					String id = request.getParameter("id");

					if (id != null) {
						int applianceId = Integer.parseInt(id);
						System.err.println("applianceId "+applianceId);
						ApplianceBean bean = ApplianceBAL.getApplianceInfo(applianceId);
						request.setAttribute("bean", bean);
						InfowindowBean bean1 = mapBAL.viewCashbean(applianceId);
						System.err.println("atttrsibut" + session.getAttribute("latitud" + id));
						if (bean != null && session.getAttribute("latitud" + id) == null && bean1 != null) {
							session.setAttribute("latitud" + bean.getApplianceId(), bean1.getLati());
							System.out.println("id" + bean.getApplianceId() + "   lati   " + bean1.getLati());
							session.setAttribute("longi" + bean.getApplianceId(), bean1.getLongi());
						} else {
							session.setAttribute("latitud" + bean.getApplianceId(), 30.0);
							session.setAttribute("longi" + bean.getApplianceId(), 69.0);
						}
						RequestDispatcher rd = request.getRequestDispatcher("ApplianceView");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

				}
			} // end of else if
			else if (option.equals("viewDoAppliance")) {
				try {
					String id = request.getParameter("id");
					if (id != null) {
						int applianceId = Integer.parseInt(id);

						System.out.println("Id " + id);
						ApplianceBean bean = ApplianceBAL.getApplianceInfo(applianceId);
						System.out.println("Bean: " + bean);
						request.setAttribute("bean", bean);
						InfowindowBean bean1 = mapBAL.viewCashbean(applianceId);
						System.out.println("ye bean hy" + bean1);
						System.err.println("atttrsibut" + session.getAttribute("latitud" + id));
						if (bean != null && session.getAttribute("latitud" + id) == null && bean1 != null) {
							session.setAttribute("latitud" + bean.getApplianceId(), bean1.getLati());
							System.out.println("id" + bean.getApplianceId() + "   lati   " + bean1.getLati());
							session.setAttribute("longi" + bean.getApplianceId(), bean1.getLongi());
						} else {
							session.setAttribute("latitud" + bean.getApplianceId(), 30.0);
							session.setAttribute("longi" + bean.getApplianceId(), 69.0);
						}
						RequestDispatcher rd = request.getRequestDispatcher("doApplianceView.jsp");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

				}
			} // end of else if
			else if (option.equals("viewAlerts")) {
				String id = request.getParameter("id");
				if (id != null) {
					int applianceId = Integer.parseInt(id);
					System.out.println("Id: " + id);
					AlertsBean list = AlertsBAL.getAlerts(applianceId);
					System.err.println("LIST: " + list);
					request.setAttribute("list", list);
					RequestDispatcher rd = request.getRequestDispatcher("Alertss");
					rd.forward(request, response);
				}
			} // end of else if

			else if (option.equals("searchResults")) {
				String id = request.getParameter("id");
				if (id != null) {
					int applianceID = Integer.parseInt(id);
					ApplianceBean bean = ApplianceBAL.getApplianceInfo(applianceID);
					System.out.println("Bean: " + bean);
					request.setAttribute("bean", bean);

					RequestDispatcher rd = request.getRequestDispatcher("ServiceOperationControllerApplianceView");
					rd.forward(request, response);
				}
			} else if (option.equals("applianceAlert")) {

				try {
					String id = request.getParameter("id");
					String mid = request.getParameter("msg_id");
					// HttpSession session=request.getSession();
					if (id != null) {
						int applianceId = Integer.parseInt(id);
						System.out.println("Id " + id);

						int msg_id = Integer.parseInt(mid);

						CustomerBAL.updateAlert(msg_id);

						ApplianceBean bean = ApplianceBAL.getApplianceInfo(applianceId);
						System.out.println("Bean: " + bean);
						request.setAttribute("bean", bean);
						InfowindowBean bean1 = mapBAL.viewCashbean(applianceId);
						System.out.println("ye bean hy" + bean1);
						System.err.println("atttrsibut" + session.getAttribute("latitud" + id));
						if (bean != null && session.getAttribute("latitud" + id) == null && bean1 != null) {
							request.getSession().setAttribute("latitud" + bean.getApplianceId(), bean1.getLati());
							System.out.println("id" + bean.getApplianceId() + "   lati   " + bean1.getLati());
							request.getSession().setAttribute("longi" + bean.getApplianceId(), bean1.getLongi());
						} else {
							// request.getSession().setAttribute(
							// "latitud" + bean.getApplianceId(), 30.0);
							// request.getSession().setAttribute(
							// "longi" + bean.getApplianceId(), 69.0);
						}
						RequestDispatcher rd = request.getRequestDispatcher("ApplianceView");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();

				}

			} else if (option.equals("applianceStatus")) {

				try {
					String id = request.getParameter("id");
					String mid = request.getParameter("msg_id");
					if (id != null) {
						int applianceId = Integer.parseInt(id);
						System.out.println("Id " + id);

						int msg_id = Integer.parseInt(mid);

						CustomerBAL.updateStatusAlerts(msg_id);

						ApplianceBean bean = ApplianceBAL.getApplianceInfo(applianceId);
						System.out.println("Bean: " + bean);
						request.setAttribute("bean", bean);
						InfowindowBean bean1 = mapBAL.viewCashbean(applianceId);
						System.out.println("ye bean hy" + bean1);
						System.err.println("atttrsibut" + session.getAttribute("latitud" + id));
						if (bean != null && session.getAttribute("latitud" + id) == null && bean1 != null) {
							request.getSession().setAttribute("latitud" + bean.getApplianceId(), bean1.getLati());
							System.out.println("id" + bean.getApplianceId() + "   lati   " + bean1.getLati());
							request.getSession().setAttribute("longi" + bean.getApplianceId(), bean1.getLongi());
						} else {
							// request.getSession().setAttribute(
							// "latitud" + bean.getApplianceId(), 30.0);
							// request.getSession().setAttribute(
							// "longi" + bean.getApplianceId(), 69.0);
						}

						RequestDispatcher rd = request.getRequestDispatcher("ApplianceView");
						rd.forward(request, response);

					}
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			} else if (option.equals("applianceAlertUpdate")) {

				CustomerBAL.updateAllApplianceMessageAlerts();

				RequestDispatcher rd = request.getRequestDispatcher("SuperAdminNock");
				rd.forward(request, response);

			} else if (option.equals("applianceStatusUpdate")) {

				CustomerBAL.updateAllApplianceMessageStatusAlerts();

				RequestDispatcher rd = request.getRequestDispatcher("SuperAdminNock");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			logger.error(e);
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
