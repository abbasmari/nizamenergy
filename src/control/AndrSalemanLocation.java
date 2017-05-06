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

import bal.SalesmanBAL;
import bean.UserBean;

@WebServlet("/AndrSalemanLocation")
public class AndrSalemanLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AndrSalemanLocation.class);

	public AndrSalemanLocation() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		HttpSession session = request.getSession();
		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {

			String salemanIds = request.getParameter("salesmanId");
			String saleLats = request.getParameter("saleLat");
			String saleLngs = request.getParameter("saleLng");
			if (salemanIds != null && saleLats != null && saleLngs != null) {
				int salemanId = Integer.parseInt(salemanIds);
				double saleLat = Double.parseDouble(saleLats);
				double saleLng = Double.parseDouble(saleLngs);

				SalesmanBAL.updateSalemanLocation(salemanId, saleLat, saleLng);

			}

			System.out.print("OK working gooddd " + salemanIds + "   " + saleLats + ",  " + saleLngs);

		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}