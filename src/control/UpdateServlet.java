package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bal.SalesmanBAL;
import bean.SalesManBean;
import bean.UserBean;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.setContentType("application/json;charset=UTF-8");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		try (PrintWriter out = response.getWriter()) {
			String vleid = request.getParameter("vleid");
			int vle_id = Integer.parseInt(vleid);
			SalesManBean salesman = SalesmanBAL.getSalesManByID(vle_id);
			JSONArray jArray = new JSONArray();
			JSONObject vleJSON = new JSONObject();
			if (salesman != null) {
				try {
					vleJSON.put("name", salesman.getName());
					vleJSON.put("joiningDate", salesman.getDatejoin());
					vleJSON.put("dateOfBirth", salesman.getDate_of_birth());
					vleJSON.put("doCnic", salesman.getCnic());
					vleJSON.put("gender", salesman.getGender());
					vleJSON.put("marritalStatus", salesman.getMarritalStatus());
					vleJSON.put("bloodGroup", salesman.getBlood_group());
					vleJSON.put("dophone1", salesman.getPhone_number());
					vleJSON.put("dophone2", salesman.getPhone_number2());
					vleJSON.put("vehicle", salesman.getVehicle());
					vleJSON.put("licence", salesman.getLicence_no());
					vleJSON.put("address", salesman.getAddress());
					vleJSON.put("educated", salesman.getEducated());
					vleJSON.put("college", salesman.getGender());
					vleJSON.put("degree", salesman.getDegree());
					vleJSON.put("dateOfStart", salesman.getStart_date());
					vleJSON.put("dateOfEnd", salesman.getEnd_date());
					vleJSON.put("percentage", salesman.getPercentage());
					vleJSON.put("accnumber", salesman.getVle_acount_no());
					vleJSON.put("percellcomm", salesman.getPer_sale());
					vleJSON.put("onTime", salesman.getOnTime());
					vleJSON.put("imageId", salesman.getImageId());

					jArray.put(vleJSON);
					out.println(jArray);

				} catch (JSONException e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
			/*
			 * request.setAttribute("vleId", vleid);
			 * request.setAttribute("salesman", salesman);
			 */

			/*
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher("updatevle.jsp");
			 * rd.forward(request, response);
			 */

		}
	}

}
