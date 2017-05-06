package control;

import bal.CallingXML;
import bal.CustomerBAL;
import bal.telenor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "SendMessage_All", urlPatterns = { "/SendMessage_All" })
public class SendMessage_All extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(SendMessage_All.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, JSONException, ServiceException, SQLException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String option = request.getParameter("phone");
			String message = request.getParameter("msg");

			String s = option.toString(); // basically what you have ;)
			JSONArray newJArray = new JSONArray(s);

			for (int i = 0; i < newJArray.length(); i++) {
				char[] Str2 = new char[4];
				newJArray.getString(i).replaceAll("\\s", "").getChars(0, 4, Str2, 0);
				// System.out.println(Str2 );
				telenor.SendSms(newJArray.getString(i).replaceAll("\\s", ""), message);

			}
			// "Removing when mobilink will be on we open this code"
			// String newnumber = new String(Str2);
			// System.out.println("TEST CUSTOMER " + newnumber);
			// if (newnumber.equals("9234")) {
			// System.out.println("Telenor");
			// session.setAttribute("telenor", telenor.get_TelenorStatus());
			//
			//
			// if (!telenor.SendSms(
			// newJArray.getString(i).replaceAll("\\s", ""),
			// message).equalsIgnoreCase("0"))
			// {
			// session.setAttribute("telenor", telenor.get_TelenorStatus());
			// telenor.SendSms(newJArray.getString(i).replaceAll("\\s",
			// ""),message);
			//
			// CustomerBAL.insertTotalMessage("Mobilink", newJArray
			// .getString(i).replaceAll("\\s", ""), message);
			// } else {
			// session.setAttribute("telenor", telenor.get_TelenorStatus());
			// CustomerBAL.insertTotalMessage("telenor", newJArray
			// .getString(i).replaceAll("\\s", ""), message);
			// }
			// }
			//
			// else if (newnumber.equals("9230")) {
			// System.out.println("Moblink");
			//
			// if (CallingXML.SendMessage(newJArray.getString(i)
			// .replaceAll("\\s", ""), message) == 1) {
			// System.out.println(telenor.SendSms(
			// newJArray.getString(i).replaceAll("\\s", ""),
			// message) + "Status of telenor ");
			// session.setAttribute("telenor", telenor.get_TelenorStatus());
			// CustomerBAL.insertTotalMessage("telenor", newJArray
			// .getString(i).replaceAll("\\s", ""), message);
			// } else {
			// CustomerBAL.insertTotalMessage("Mobilink", newJArray
			// .getString(i).replaceAll("\\s", ""), message);
			// }
			//
			// }

			// System.out.println(telenor.SendSms(newJArray.getString(i).replaceAll("\\s",""),
			// message));
			System.out.println("mesg " + message);
			System.out.println("test " + option);

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException | ServiceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (JSONException | ServiceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
