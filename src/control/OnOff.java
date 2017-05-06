package control;

import bal.StatusUpdateBAL;
import bal.telenor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;

import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.json.JSONException;

@WebServlet(name = "OnOff", urlPatterns = { "/OnOff" })
public class OnOff extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(OnOff.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("click");
			if (option.equals("on")) {
				String id = request.getParameter("id");
				String gsm = request.getParameter("gsm");
				if (id != null) {
					int idd = Integer.parseInt(id);
					StatusUpdateBAL.insertMessageForHandover(StatusUpdateBAL.getCustomerIdFromApplianceId(idd), "on",
							StatusUpdateBAL.getGsmNumber(idd), 1);
					// StatusUpdateBAL.updateApplianceStatus(idd, 1);
					try {
						telenor.SendSms(gsm, "$5$");
						try {
							telenor.RecieveSms();
						} catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
					} catch (ServiceException e) {
						logger.error(e);
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/ViewServlet?click=viewAppliance&id=" + idd);
					rd.forward(request, response);
				}
			} else if (option.equalsIgnoreCase("off")) {
				String id = request.getParameter("id");
				String gsm = request.getParameter("gsm");
				if (id != null) {
					int idd = Integer.parseInt(id);
					StatusUpdateBAL.insertMessageForHandover(StatusUpdateBAL.getCustomerIdFromApplianceId(idd), "off",
							StatusUpdateBAL.getGsmNumber(idd), 1);
					// StatusUpdateBAL.updateApplianceStatus(idd, 0);
					try {
						telenor.SendSms(gsm, "$4$");
						try {
							telenor.RecieveSms();
						} catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
					} catch (ServiceException e) {
						logger.error(e);
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/ViewServlet?click=viewAppliance&id=" + idd);
					rd.forward(request, response);
				}
			} else if (option.equalsIgnoreCase("Get")) {
				String id = request.getParameter("id");
				String gsm = request.getParameter("gsm");
				if (id != null) {
					int idd = Integer.parseInt(id);
					try {
						telenor.SendSms(gsm, "$1$");
						try {
							telenor.RecieveSms();
						} catch (Exception e) {
							logger.error(e);
							e.printStackTrace();
						}
					} catch (ServiceException e) {
						logger.error(e);
						e.printStackTrace();
					}
					RequestDispatcher rd = request.getRequestDispatcher("/ViewServlet?click=viewAppliance&id=" + idd);
					rd.forward(request, response);
				}

			}
		} catch (SQLException ex) {
			logger.error(ex);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
