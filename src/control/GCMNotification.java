package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bean.UserBean;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/GCMNotification")
public class GCMNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ForgotPassword.class);

	private static final String GOOGLE_SERVER_KEY = "AIzaSyD6lQBRFynfH_OC8DIP8-E2KSfldz7kwqI";
	static final String MESSAGE_KEY = "message";
	static final String REG_ID_STORE = "GCMRegId.txt";

	public GCMNotification() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		MulticastResult result = null;

		String share = request.getParameter("shareRegId");

		if (share != null && !share.isEmpty()) {
			writeToFile(share);
			request.setAttribute("pushStatus", "GCM RegId Received.");
			request.getRequestDispatcher("Index").forward(request, response);
		} else {
			try {
				String userMessage = request.getParameter("message");
				Sender sender = new Sender(GOOGLE_SERVER_KEY);
				Message message = new Message.Builder().timeToLive(3000).delayWhileIdle(true)
						.addData(MESSAGE_KEY, userMessage).build();
				Set regIdSet = readFromFile();
				System.out.println("regId: " + regIdSet);
				List regIdList = new ArrayList();
				regIdList.addAll(regIdSet);
				result = sender.send(message, regIdList, 1);
				request.setAttribute("pushStatus", result.toString());
			} catch (IOException ioe) {
				logger.error(ioe);
				ioe.printStackTrace();
				request.setAttribute("pushStatus", "RegId required: " + ioe.toString());
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				request.setAttribute("pushStatus", e.toString());
			}
			request.getRequestDispatcher("Index").forward(request, response);
		}
	}

	private void writeToFile(String regId) throws IOException {
		Set regIdSet = readFromFile();

		if (!regIdSet.contains(regId)) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(REG_ID_STORE, true)));
			out.println(regId);
			out.close();
		}

	}

	private Set readFromFile() throws IOException {

		File f = new File(REG_ID_STORE);
		if (!f.exists()) {
			f.createNewFile();
		}
		BufferedReader br = new BufferedReader(new FileReader(REG_ID_STORE));
		String regId = "";
		Set regIdSet = new HashSet();
		while ((regId = br.readLine()) != null) {
			regIdSet.add(regId);
		}
		br.close();
		return regIdSet;
	}
}