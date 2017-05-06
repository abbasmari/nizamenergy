package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/Search_CustomerByNumber")
public class Search_CustomerByNumber extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Search_CustomerByNumber.class);

	public Search_CustomerByNumber() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String gsm = request.getParameter("Send_search_element");
			System.err.println(gsm);
			System.out.println("GSM from Servlet " + gsm);
			JSONObject studentJSON = new JSONObject();
			JSONArray jArray = new JSONArray();

			studentJSON.put("gsm", gsm);

			jArray.put(studentJSON);

			out.println(jArray);
		} catch (JSONException e) {
			logger.error(e);
			e.printStackTrace();
		}

	}

}
