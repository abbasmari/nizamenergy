package control;

import bal.CallingXML;
import bal.CustomerBAL;
import bal.telenor;
import bean.CustomerProfileViewBean;

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
import javax.xml.rpc.ServiceException;

import org.json.JSONException;

import bean.UserBean;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CustomerMessage", urlPatterns = { "/CustomerMessage" })
public class CustomerMessage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerMessage.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException, JSONException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String option = request.getParameter("click");

			if (option.equals("message")) {

				int id = Integer.parseInt(request.getParameter("id"));
				// int soldId = StatusUpdateBAL.getSoldIdById(id);
				String gsm = request.getParameter("gsmNumber");
				String message = request.getParameter("customerText");

				char[] Str2 = new char[4];
				gsm.getChars(0, 4, Str2, 0);
				// System.out.println(Str2 );

				String newnumber = new String(Str2);
				// System.out.println(newnumber );

				if (newnumber.equals("9234")) {
					System.out.println("Telenor");
					System.out.println("this is gsm " + gsm + " Message " + message);
					telenor.SendSms(gsm, message);
					CustomerBAL.insertIntoMessage(id, message, gsm, 3001);
					CustomerBAL.insertTotalMessage("Telenor", gsm, message);

				}

				else if (newnumber.equals("9230")) {
					System.out.println("Moblink");

					CallingXML.SendMessage(gsm, message);

				}
				CustomerBAL.insertIntoMessage(id, message, gsm, 7005148);
				CustomerProfileViewBean customerList = CustomerBAL.getCustomerProfile(gsm);
				request.setAttribute("customerInformation", customerList);
				request.setAttribute("key", 2);
				// CustomerInfoBean bean = CustomerBAL.getCutomers(soldId);
				// System.out.println(bean);
				// request.setAttribute("bean", bean);
				// ApplianceBean beanAppliance =
				// ApplianceBAL.getApplianceWithCustomerId(bean.getCustomerId());
				// CustomerInfoBean beanCustomer =
				// CustomerBAL.getCustomerWithIdAscendig(bean.getCustomerId());
				// request.setAttribute("beanData", beanAppliance);
				// request.setAttribute("beanDataCustomer", beanCustomer);

				/* response.sendRedirect("ProfileV2"); */
				RequestDispatcher rd = request.getRequestDispatcher("ProfileV2");
				rd.forward(request, response);
			}
		} catch (SQLException ex) {
			Logger.getLogger(CustomerMessage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
