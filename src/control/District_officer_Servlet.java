package control;

import bal.DistrictOfficerBAL;
import bean.DistrictOfficerBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "District_officer_Servlet", urlPatterns = { "/District_officer_Servlet" })
public class District_officer_Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(District_officer_Servlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		String id = request.getParameter("do_id");
		try {
			if (id != null) {
				int districtId = Integer.parseInt(id);
				System.out.println(districtId);
				DistrictOfficerBean bean = DistrictOfficerBAL.getDO_details(districtId);
				request.setAttribute("bean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("Do_Profile");
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