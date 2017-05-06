package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import bal.ApplianceBAL;
import bal.CallingXML;
import bal.CustomerBAL;
import bal.EligibilityBAL;
import bal.LoanPaymentBAL;
import bal.StatusUpdateBAL;
import bal.telenor;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(asyncSupported = true, urlPatterns = { "/LoanPaymentController" })
public class LoanPaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoanPaymentController.class);

	public LoanPaymentController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		proccess(request, response);
	}

	protected void proccess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));
		String click = request.getParameter("click");

		if (click != null) {
			if (click.equals("assignImei")) {
				String applianceIdString = request.getParameter("applianceId");
				String imei = request.getParameter("imei");
				String gsm = request.getParameter("gsm");
				String csId = request.getParameter("customerId");
				String consumerNum = request.getParameter("consumerNum");
				System.out.println("applianceIdString" + applianceIdString);
				System.out.println("imeiString" + imei);
				try {
					if (applianceIdString != null) {
						int applianceId = Integer.parseInt(applianceIdString);
						ApplianceBAL.updateImei(applianceId, imei, gsm, csId,
								consumerNum);
						// EligibilityBAL.updateEligibiltyStatus(applianceId);
						String str = StatusUpdateBAL.getDatails(applianceId);
						String arr[] = str.split(" ");
						String phone = arr[0];
						String customerName = arr[1];
						String applianceName = arr[2];
						String msg = "Aap ka Consumer number hai "
								+ consumerNum
								+ ". Nizam Bijli ki mahaana adaaigi aap ko Jazz cash k zaryay "
								+ "karni hai. Brahay meharbani apnay mobile main jazzcash account banana k lia humaray Field "
								+ "officer/ Nizam dost se raabta karein. Jab aap ka jazzcash ka account ban jae toh *786# dial "
								+ "kijiyay. Is k baad 2. Pay bill press kijiyay, 1. Electricity bill press kijiyay, 13. Nizam Bijli "
								+ "press kijiyay aur apna Consumer number darj karein.";
						try {
							telenor.SendSms(phone, msg);
						} catch (Exception e) {
							logger.error("", e);
							e.printStackTrace();
						}
					}
					response.sendRedirect("doDeployment.jsp");
				} catch (Exception e) {
					logger.error("", e);
				}
			}
			if (click.equals("checkGsm")) {
				if (ApplianceBAL.checkGsm(request.getParameter("GSM"))) {
					out.println("0");
				} else {
					out.println("1");
				}

			}

			if (click.equals("checkImei")) {
				System.err.println("in to checkImei lpc");
				if (ApplianceBAL.checkImei(request.getParameter("Imei"))) {
					out.println("0");
				} else {
					out.println("1");
				}

			}

			if (click.equals("checkConsumer")) {
				if (ApplianceBAL
						.checkConsumer(request.getParameter("Consumer"))) {
					out.println("0");
				} else {
					out.println("1");
				}

			}

			if (click.equals("revoke")) {
				int csId = Integer.parseInt(request.getParameter("customerId"));
				int appid = Integer.parseInt(request
						.getParameter("applianceId"));
				try {
					ApplianceBAL.revokeConsumerNum(appid, csId);
				} catch (SQLException e) {
					logger.error("", e);
				}
			}
			if (click.equals("install")) {
				int appId = Integer.parseInt(request
						.getParameter("applianceId"));
				try {
					ApplianceBAL.installDevice(appId);
				} catch (SQLException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				response.sendRedirect("Appliances");
			}if (click.equals("varifySignals")) {
				int appId = Integer.parseInt(request.getParameter("applianceId"));
				try {
					ApplianceBAL.updateNoSignalDevice(appId);
				} catch (SQLException e) {
					logger.error("", e);
					e.printStackTrace();
				}
				response.sendRedirect("Appliances");
			}

		}
	}
}