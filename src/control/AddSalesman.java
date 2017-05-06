package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.SalesmanBAL;
import bal.telenor;
import bean.UserBean;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet(name = "AddSalesman", urlPatterns = { "/AddSalesman" })
@MultipartConfig
public class AddSalesman extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddSalesman.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// processRequest(request, response);

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			HttpSession session = request.getSession();

			UserBean bean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String option = request.getParameter("click");
			if (option.equalsIgnoreCase("Submit") || option.equalsIgnoreCase("Update")) {

				String firstName = request.getParameter("firstname");
				String gender = request.getParameter("gender");
				String bloodGroup = request.getParameter("bloodGroup");
				String imageId = "0";
				String address = request.getParameter("address");
				String dateOfBirth = request.getParameter("dateOfBirth");
				String phone1 = request.getParameter("phone1");
				String phone2 = request.getParameter("phone2");
				String cnic = request.getParameter("cnic");
				String basic_salary = "0";
				double salary = Double.parseDouble(basic_salary);
				String email = request.getParameter("email");
				String joiningDate = request.getParameter("joiningDate");
				String marritalStatus = request.getParameter("marritalStatus");

				String vehicle = request.getParameter("toggle");

				String licence = request.getParameter("licence");
				String educated = request.getParameter("education");
				System.out.println(basic_salary + " date : " + dateOfBirth);

				String college = request.getParameter("college");
				String degree = request.getParameter("degree");
				String dateOfStart = request.getParameter("dateOfStart");
				if (dateOfStart.isEmpty()) {
					dateOfStart = "0000:00:00";
				}
				String dateOfEnd = request.getParameter("dateOfEnd");
				if (dateOfEnd.isEmpty()) {
					dateOfEnd = "0000:00:00";
				}
				String perc = request.getParameter("percentage");
				double percentage = 0.0;
				if (perc.isEmpty()) {
					percentage = 0.0;
				} else {
					percentage = Double.parseDouble(perc);
				}

				System.out.println("jhshj" + percentage);
				String onTime = request.getParameter("onTime");
				String beforeTime = "0";

				// beforeTime = request.getParameter("beforeTime");
				if (beforeTime.length() == 1) {
					beforeTime = "0" + beforeTime;
				}
				if (onTime.length() == 1) {
					onTime = "0" + onTime;
				}
				String afterTime = "0";

				// afterTime = request.getParameter("afterTime");
				if (afterTime.length() == 1) {
					afterTime = "0" + afterTime;
				}

				String accnumber = request.getParameter("accnumber");
				String percellcomm = request.getParameter("percellcomm");

				System.err.println("accnumber : " + accnumber + " " + percellcomm + " " + onTime);

				int r = (int) (Math.random() * 10000);
				String password = phone1 + "" + r;
				System.out.println("randome : " + r);

				HashMap<String, String> salesmap = new HashMap<>();
				salesmap.put("firstName", "" + firstName);
				salesmap.put("email", "" + email);
				salesmap.put("password", "" + password);
				salesmap.put("cnic", "" + cnic);
				salesmap.put("gender", "" + gender);
				salesmap.put("salary", "" + salary);
				salesmap.put("phone1", "" + "92" + phone1.replace("-", ""));
				salesmap.put("address", "" + address);
				salesmap.put("dateOfBirth", "" + dateOfBirth);
				salesmap.put("joiningDate", "" + joiningDate);
				salesmap.put("educated", "" + educated);
				salesmap.put("marritalStatus", "" + marritalStatus);
				salesmap.put("bloodGroup", "" + bloodGroup);
				salesmap.put("licence", "" + licence);
				salesmap.put("phone2", "" + "92" + phone2.replace("-", ""));
				salesmap.put("degree", "" + degree);
				salesmap.put("college", "" + college);
				salesmap.put("dateOfStart", "" + dateOfStart);
				salesmap.put("dateOfEnd", "" + dateOfEnd);
				salesmap.put("percentage", "" + percentage);
				salesmap.put("vehicle", "" + vehicle);
				// salesmap.put("input", ""+input);

				salesmap.put("beforeTime", "" + beforeTime);
				salesmap.put("onTime", "" + onTime);
				salesmap.put("afterTime", "" + afterTime);
				salesmap.put("percellcomm", percellcomm);
				salesmap.put("accnumber", accnumber);

				salesmap.put("imageId", "" + imageId);
				System.out.println("============================================== " + imageId);

				if (option.equals("Submit")) {
					int foid = Integer.parseInt(request.getParameter("fo"));
					int cityId = Integer.parseInt(request.getParameter("city"));

					int district_id = Integer.parseInt(request.getParameter("district"));
					int doId = Integer.parseInt(request.getParameter("doId"));

					salesmap.put("district_id", "" + district_id);
					salesmap.put("cityId", "" + cityId);
					salesmap.put("foid", "" + foid);

					System.out.println("###############################Aqeel " + salesmap);
					out.print(salesmap);
					SalesmanBAL.insertSalesMan(salesmap, doId);
					String str = "Welcome to Nizam Energy as our VLE.  Please contact your District Officer for further instructions";
					try {
						telenor.SendSms("92" + phone1.replace("-", ""), str);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.print(e);
					}
					response.sendRedirect("doSalesman.jsp");
					// response.sendRedirect("DoSalesmen?fo_id=" + foid);
				} else if (option.equals("Update")) {

					String vleId = request.getParameter("vleId");
					Integer.parseInt(vleId);
					salesmap.put("vleId", "" + vleId);
					System.out.println("###############################Yaseen " + salesmap);
					out.print(salesmap);
					SalesmanBAL.updateVLE(salesmap);
					response.sendRedirect("Salesmen");
				}

			} else if (option.equalsIgnoreCase("Add Field officer") || option.equalsIgnoreCase("UpdateFo")) {

				String firstName = request.getParameter("sfirstname");
				String gender = request.getParameter("sgender");
				String bloodGroup = request.getParameter("sbloodGroup");
				String address = request.getParameter("saddress");
				String dateOfBirth = request.getParameter("sdateOfBirth");
				String phone1 = request.getParameter("sphone1").replace("-", "");
				String phone2 = request.getParameter("sphone2").replace("-", "");
				String cnic = request.getParameter("scnic");

				String basic_salary = request.getParameter("ssalary");
				double salary = Double.parseDouble(basic_salary);
				String joiningDate = request.getParameter("sjoiningDate");
				String marritalStatus = request.getParameter("smarritalStatus");

				String vehicle = request.getParameter("stoggle");

				String licence = request.getParameter("slicence");
				String educated = request.getParameter("seducation");

				System.err.println(basic_salary + " date : " + dateOfBirth);

				String college = request.getParameter("sCollege");
				String degree = request.getParameter("sDegree");
				String dateOfStart = request.getParameter("sDateOfStart");
				if (dateOfStart.isEmpty()) {
					dateOfStart = "0000:00:00";
				}
				String dateOfEnd = request.getParameter("sDateOfEnd");
				if (dateOfEnd.isEmpty()) {
					dateOfEnd = "0000:00:00";
				}
				String perc = request.getParameter("sPercentage");
				double percentage = 0.0;
				if (perc.isEmpty()) {
					percentage = 0.0;
				} else {
					percentage = Double.parseDouble(perc);
				}

				System.out.println("jhshj" + percentage);
				String onTime = request.getParameter("sonTime");
				String beforeTime = "0";
				// beforeTime = request.getParameter("sbeforeTime");
				if (beforeTime.length() == 1) {
					beforeTime = "0" + beforeTime;
				}
				if (onTime.length() == 1) {
					onTime = "0" + onTime;
				}
				String afterTime = "0";
				// afterTime = request.getParameter("safterTime");
				if (afterTime.length() == 1) {
					afterTime = "0" + afterTime;
				}

				String accnumber = request.getParameter("accnumber");
				String percellcomm = request.getParameter("percellcomm");

				System.err.println("0000000000 000000 000 accnumber : " + accnumber + " " + onTime + " " + percellcomm);
				String imageId = "0";
				// imageId = request.getParameter("imageIdfo");
				int r = (int) (Math.random() * 10000);
				String password = phone1 + "" + r;
				System.out.println("randome : " + r);

				HashMap<String, String> salesmap = new HashMap<>();
				salesmap.put("firstName", "" + firstName);
				salesmap.put("cnic", "" + cnic);
				salesmap.put("gender", "" + gender);
				salesmap.put("salary", "" + salary);
				salesmap.put("phone1", "" + "92" + phone1.replace("-", ""));
				salesmap.put("address", "" + address);
				salesmap.put("dateOfBirth", "" + dateOfBirth);
				salesmap.put("joiningDate", "" + joiningDate);
				salesmap.put("educated", "" + educated);
				salesmap.put("marritalStatus", "" + marritalStatus);
				salesmap.put("bloodGroup", "" + bloodGroup);
				salesmap.put("licence", "" + licence);
				salesmap.put("phone2", "" + "92" + phone2.replace("-", ""));
				salesmap.put("degree", "" + degree);
				salesmap.put("college", "" + college);
				salesmap.put("dateOfStart", "" + dateOfStart);
				salesmap.put("dateOfEnd", "" + dateOfEnd);
				salesmap.put("percentage", "" + percentage);
				salesmap.put("vehicle", "" + vehicle);

				salesmap.put("beforeTime", "" + beforeTime);
				salesmap.put("onTime", "" + onTime);
				salesmap.put("afterTime", "" + afterTime);
				salesmap.put("accnumber", accnumber);
				salesmap.put("percellcomm", percellcomm);
				salesmap.put("password", "" + password);
				salesmap.put("imageId1", imageId);

				if (option.equals("Add Field officer")) {

					String district = request.getParameter("sdistrict");
					int district_id = Integer.parseInt(district);
					String city = request.getParameter("scity");
					int cityId = Integer.parseInt(city);
					int doId = Integer.parseInt(request.getParameter("sdoId"));

					salesmap.put("cityId", "" + cityId);
					salesmap.put("district_id", "" + district_id);

					SalesmanBAL.insertFieldOfficer(salesmap, doId);
					String str = "Welcome to Nizam Energy as a Field Officer. Please collect mobile application from your District Officer. Your mobile application's password is: "
							+ password;
					try {
						telenor.SendSms("92" + phone1.replace("-", ""), str);
						System.out.println("ENd msgs");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.print(e);
					}
					response.sendRedirect("doFieldOfficer.jsp");

				} else if (option.equals("UpdateFo")) {
					System.err.println("0000000000000000000000000000000");
					String foId = request.getParameter("foId");
					Integer.parseInt(foId);
					salesmap.put("foId", "" + foId);
					out.print(salesmap);
					SalesmanBAL.updateFo(salesmap);
					response.sendRedirect("superAdminFO.jsp");
				}

			}
		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
