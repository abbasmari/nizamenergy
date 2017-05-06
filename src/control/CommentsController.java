package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import bal.ActiveInActiveGraphBAL;
import bal.EligibilityBAL;
import bal.Payment_loanNewBAL;
import bean.CustomerLoanBean;
import bean.MonthlyWisePayment;
import bean.UserBean;

/**
 * Servlet implementation class CommentsController
 */
@WebServlet("/CommentsController")
public class CommentsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CommentsController.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		if (ubean != null) {
			String click = request.getParameter("click");
			if (click.equals("getComments")) {
				String id = request.getParameter("appId");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				int applianceId = Integer.parseInt(id);
				JSONArray json = null;
				try {
					ArrayList<HashMap<String, String>> comments = ActiveInActiveGraphBAL.getAllComments(applianceId,
							from, to);
					ActiveInActiveGraphBAL.updateMessageStatus(applianceId, ubean.getUserId());
					json = new JSONArray(comments);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (click.equals("insertComments")) {
				String id = request.getParameter("appId");
				String text = request.getParameter("message");
				try {
					ActiveInActiveGraphBAL.insertComments(text, ubean.getUserId(), Integer.parseInt(id));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			} else if (click.equals("countUnseenMessages")) {
				JSONObject json = new JSONObject();
				try {
					json.put("countMessages", ActiveInActiveGraphBAL.countUnseenMessages(ubean.getUserId()));
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(json);
			} else if (click.equals("showUnseenMessages")) {
				JSONArray json = null;
				try {
					json = new JSONArray(ActiveInActiveGraphBAL.getUnseenMessagesForLoanBook(ubean.getUserId()));
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.println(json);
			}else if(click.equalsIgnoreCase("doItReturn")){
				try {
					String id = request.getParameter("appId");
					int deviceStatus = Integer.parseInt(request.getParameter("deviceStatus"));
					if(deviceStatus == 0){
						deviceStatus = 1;
					}else{
						deviceStatus = 0;
					}
					System.err.println("appId"+id+" deviceStatus"+deviceStatus);
				Payment_loanNewBAL.setOwnDeviceStatus(Integer.parseInt(id), deviceStatus);
				} catch (Exception e) {
					logger.error("", e);
					e.printStackTrace();
				}
			}
		}
	}

}
