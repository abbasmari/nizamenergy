package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.Appliance_Message;
import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/ApplianceDeviceMessages")
public class ApplianceDeviceMessages extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ApplianceDeviceMessages.class);

	public ApplianceDeviceMessages() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean bean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));
			
			String smsFrom = request.getParameter("SMSFROM");
			String msgto = request.getParameter("MSGTO");
			String date = request.getParameter("DATE");
			String message = request.getParameter("MESSAGE");

			Appliance_Message.insertAppliance_Message(smsFrom, msgto, date, message, 0);

			out.print("Message added from appliance");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}