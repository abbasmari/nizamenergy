package control;

import bal.CallingXML;
import bal.DistrictOfficerBAL;
import bal.LoginHistoryBAL;
import bal.SendmailBAL;
import bal.UserBAL;
import bal.telenor;
import bean.DistrictOfficerBean;
import bean.UserBean;

//import java.io.DataInputStream;
//import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import schedule.AlarmsAlertsToFo;
import security.Hashing;

@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LoginServlet.class);

	public LoginServlet() {
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Start================//
		String operatingSystem = "";
		try {
			String browserDetails = request.getHeader("User-Agent");
			String userAgent = browserDetails;
			// String user = userAgent.toLowerCase();
			System.out.println(userAgent);

			// =================OS=======================
			if (userAgent.toLowerCase().indexOf("windows") >= 0) {
				operatingSystem = "Windows";
			} else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
				operatingSystem = "Mac";
			} else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
				operatingSystem = "Unix";
			} else if (userAgent.toLowerCase().indexOf("android") >= 0) {
				operatingSystem = "Android";
			} else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
				operatingSystem = "IPhone";
			} else {
				operatingSystem = "UnKnown";
			}
			System.out.println("Operating System : " + operatingSystem);
		} catch (Exception ex) {
			logger.error(ex);
			ex.printStackTrace();
		}

		try {
			HttpSession session = request.getSession();
			String option = request.getParameter("click");
			session.setMaxInactiveInterval(30 * 60);
			if (option != null) {
				if (option.equals("checkMail")) {
					System.out.println("LoginServlet.processRequest() : checkMail");
					if (UserBAL.checkEmail(request.getParameter("userEmail"))) {
						out.println("");
					} else {
						out.println("Invalid email");
					}
				} else if (option.equals("logout")) {
					session.invalidate();
					response.sendRedirect("SolarHomeSystemLogin");
				} else if (session.getAttribute("userType") == null) {
					if (option.equals("login")) {
						System.out.println("Loginnnnnn");
						String email = request.getParameter("userEmail");
						String password = request.getParameter("userPassword");
						String ipAddress = request.getParameter("ipAddress");
						System.err.println("ipAddress " + ipAddress);
						UserBean bean = UserBAL.checkUser(email, password);
						password = Hashing.getMD5(password);
						if (bean != null) {
							System.out.println(bean.getUserName());
							session.setAttribute("userType", bean.getUserType());
							session.setAttribute("ipAddress", ipAddress);
							int user_type = bean.getUserType();
							if (!password.equals(bean.getPassword())) {
								request.setAttribute("msg", "Invalid password");
								response.sendRedirect("SolarHomeSystemLogin");

							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& user_type == 100) {
								session.setAttribute("email", bean);
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								request.setAttribute("msg", "");
								response.sendRedirect("SuperAdminDashboard");

							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& user_type == 101) {
								DistrictOfficerBean dobean = DistrictOfficerBAL.getDO_details(bean.getUserId());
								System.out.println("########### " + bean.getUserName());
								session.setAttribute("email", bean);
								session.setAttribute("dobean", dobean);
								// request.setAttribute(email, out);
								request.setAttribute("msg", "");
								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request.getRequestDispatcher("DistrictOfficerDashboard");
								rs.forward(request, response);

							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& user_type == 103) {
								session.setAttribute("email", bean);
								request.setAttribute("msg", "");
								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request
										.getRequestDispatcher("ServiceOperationControllerDashboard");
								rs.forward(request, response);
							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& user_type == 104) {
								session.setAttribute("email", bean);
								request.setAttribute("msg", "");
								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request.getRequestDispatcher("CustomerServiceLogin");
								rs.forward(request, response);

							}
						} else {
							logger.error("User name or password is incorrect");
							request.setAttribute("msg", "Invalid password");
							RequestDispatcher rd = request.getRequestDispatcher("SolarHomeSystemLogin");
							rd.include(request, response);
						}
					}
				} else {
					System.out.println(session.getAttribute("userType"));
					if (option.equals("login")) {
						String email = request.getParameter("userEmail");
						String password = request.getParameter("userPassword");
						UserBean bean = UserBAL.checkUser(email, password);
						password = Hashing.getMD5(password);
						if (bean != null) {
							DistrictOfficerBean dobean = DistrictOfficerBAL.getDO_details(bean.getUserId());
							session.setAttribute("email", bean);
							session.setAttribute("dobean", dobean);
							session.setAttribute("userType", bean.getUserType());
							session.setAttribute("telenor", telenor.get_TelenorStatus());
							session.setAttribute("mobilink", CallingXML.get_mobilink_status());

							if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& session.getAttribute("userType").toString().equals("100")) {
								System.out.println("100");
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request.getRequestDispatcher("SuperAdminDashboard");
								rs.forward(request, response);

							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& session.getAttribute("userType").toString().equals("101")) {
								session.setAttribute("email", bean);
								session.setAttribute("dobean", dobean);
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request.getRequestDispatcher("DistrictOfficerDashboard");
								rs.forward(request, response);

							} else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& session.getAttribute("userType").toString().equals("103")) {
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request
										.getRequestDispatcher("ServiceOperationControllerDashboard");
								rs.forward(request, response);

							}

							else if (email.equals(bean.getEmail()) && password.equals(bean.getPassword())
									&& session.getAttribute("userType").toString().equals("104")) {
								request.setAttribute("msg", "");

								logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId()
										+ " , IP : " + request.getSession().getAttribute("ipAddress")
										+ " , Operating System : " + operatingSystem + ", Mac Address : "
										+ UserBAL.getMac(request.getSession().getAttribute("ipAddress").toString()));

								RequestDispatcher rs = request.getRequestDispatcher("CustomerServiceLogin");
								rs.forward(request, response);

							} //

						} else {
							request.setAttribute("msg", "invalid password");
							RequestDispatcher rd = request.getRequestDispatcher("SolarHomeSystemLogin");
							rd.forward(request, response);
						}
					} else if (session.getAttribute("userType").toString().equals("100")) {
						System.out.println("100");
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("SuperAdminDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").toString().equals("101")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("DistrictOfficerDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").toString().equals("103")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("ServiceOperationControllerDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").toString().equals("104")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("CustomerServiceLogin");
						rs.forward(request, response);
					}
				}
			} else {
				System.out.println("Option is null");
				if (session.getAttribute("userType") != null) {
					System.out.println("If on option null");
					System.out.println("User Type " + session.getAttribute("userType"));
					if (session.getAttribute("userType").toString().equals("100")) {
						System.out.println("User Type:  1000");
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("SuperAdminDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").equals("101")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("DistrictOfficerDashboard");
						rs.forward(request, response);
					} else if (session.getAttribute("userType").equals("103")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("ServiceOperationControllerDashboard");
						rs.forward(request, response);

					} else if (session.getAttribute("userType").equals("104")) {
						request.setAttribute("msg", "");
						RequestDispatcher rs = request.getRequestDispatcher("CustomerServiceLogin");
						rs.forward(request, response);

					}
				} else {
					System.out.println("else on option null");
					request.setAttribute("msg", "Invalid password");
					RequestDispatcher rd = request.getRequestDispatcher("SolarHomeSystemLogin");
					rd.forward(request, response);
				}

			}

		} catch (Exception e) {
			logger.error(e);
			request.setAttribute("msg", "Invalid password");
			RequestDispatcher rd = request.getRequestDispatcher("SolarHomeSystomLogin");
			rd.forward(request, response);
			e.printStackTrace();
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
