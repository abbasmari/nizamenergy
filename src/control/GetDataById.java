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

import bal.CustomerBAL;
import bal.FieldOfficerBAL;
import bean.UserBean;

/**
 * Servlet implementation class GetDataById
 */
@WebServlet("/GetDataById")
public class GetDataById extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(GetDataById.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDataById() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();
			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String action = request.getParameter("action");
			String id = request.getParameter("id");

			if (action.equals("FOInfo")) {
				JSONObject FOJSON = new JSONObject();
				try {
					FOJSON.put("FODetail", FieldOfficerBAL.getFieldOfficerDetailForUpdate(Integer.parseInt(id)));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				out.print(FOJSON);
			} else if (action.equals("VLEInfo")) {
				JSONObject VLEJSON = new JSONObject();

				out.print(VLEJSON);
			} else if (action.equals("doInfo")) {

				JSONObject DOJSON = new JSONObject();
				try {
					DOJSON.put("doDetail", CustomerBAL.getDoDetailForUpdate(Integer.parseInt(id)));
				} catch (NumberFormatException e) {
					logger.error("", e);
					e.printStackTrace();
				} catch (JSONException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				out.print(DOJSON);
			}

		}

	}

}
