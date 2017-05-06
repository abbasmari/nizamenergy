package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.TargetsBAL;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/UpdateTarget")
public class UpdateTarget extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateTarget.class);

	public UpdateTarget() {
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

		try {
			int salesmanId = Integer.parseInt(request.getParameter("salesmanId"));
			int targetId = Integer.parseInt(request.getParameter("targetId"));
			int units = Integer.parseInt(request.getParameter("units"));
			System.out.println("SalesmanID : " + salesmanId);
			System.out.println("TargetID : " + targetId);
			System.out.println("Units : " + units);
			int row = TargetsBAL.updateTarget(targetId, units);

			PrintWriter out = response.getWriter();
			if (row > 0) {
				out.println("Target Updated");
			} else {
				out.println("Target Updating failed");
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
}