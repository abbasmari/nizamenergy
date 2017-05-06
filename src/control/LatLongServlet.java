package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/LatLongServlet")
public class LatLongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LatLongServlet.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		try (PrintWriter out = response.getWriter()) {
			String latitude = request.getParameter("lati");
			String longitude = request.getParameter("longi");
			String gsm = request.getParameter("app_id");
			String voltage = request.getParameter("voltage");
			String temprature = request.getParameter("temp");
			String environment = request.getParameter("environment");
			double voltaa = Double.parseDouble(voltage);
			double lati = Double.parseDouble(latitude);
			double longi = Double.parseDouble(longitude);
			int app_id = Integer.parseInt(gsm);
			double temp = Double.parseDouble(temprature);
			System.out.println("Url fired");
			bal.GeofencingBAL.insertstatus(app_id, voltaa, 2.2, temp, environment, lati, longi);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
