package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.SendmailBAL;
import bal.UserBAL;
import bal.telenor;
import bean.UserBean;

@WebServlet(name = "AddUsersServlet", urlPatterns = { "/AddUsersServlet" })
@MultipartConfig
public class AddUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(AddUsersServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		UserBean bean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + bean.getUserName() + " , UserID : " + bean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		String option = request.getParameter("btn");

		int userId = 0;
		String firstName = request.getParameter("firstname");

		String joiningDate = request.getParameter("joiningDate");

		String dateOfBirth = request.getParameter("dateOfBirth");
		String cnic = request.getParameter("cnic");

		String gender = request.getParameter("gender");
		String marritalStatus = request.getParameter("marritalStatus");
		String bloodGroup = request.getParameter("bloodGroup");

		String phone1 = request.getParameter("phone1");
		phone1 = phone1.replace("-", "");
		String phone2 = request.getParameter("phone2");
		phone2 = phone2.replace("-", "");

		String email = request.getParameter("email");

		String vehicle = request.getParameter("toggle");
		String licence = request.getParameter("licence");

		String address = request.getParameter("address");

		String educated = request.getParameter("education");

		String college = request.getParameter("college");
		String degree = request.getParameter("degree");
		String dateOfStart = request.getParameter("dateOfStart");

		String dateOfEnd = request.getParameter("dateOfEnd");
		if (dateOfEnd.isEmpty()) {
			dateOfEnd = "0000:00:00";
		}
		if (dateOfStart.isEmpty()) {
			dateOfStart = "0000:00:00";
		}
		String perc = request.getParameter("percentage");
		double percentage = 0.0;
		if (perc.isEmpty()) {
			percentage = 0.0;
		} else {
			percentage = Double.parseDouble(perc);
		}

		String basic_salary = request.getParameter("salary");
		System.out.print(basic_salary);
		double salary = 20000.0;
		if (basic_salary != null) {
			salary = Double.parseDouble(basic_salary);
		}

		String ImageId = "0";
		// String ImageId=request.getParameter("imageId");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + ImageId);

		int r = (int) (Math.random() * 10000);
		String password = phone1 + "" + r;

		System.out.println("======================" + email);

		HashMap<String, String> usermap = new HashMap<>();
		usermap.put("firstName", "" + firstName);
		usermap.put("email", "" + email);
		usermap.put("password", "" + password);
		usermap.put("cnic", "" + cnic);
		usermap.put("gender", "" + gender);
		usermap.put("salary", "" + salary);
		usermap.put("phone1", "" + "92" + phone1);
		usermap.put("address", "" + address);
		usermap.put("dateOfBirth", "" + dateOfBirth);
		usermap.put("joiningDate", "" + joiningDate);
		usermap.put("educated", "" + educated);
		usermap.put("marritalStatus", "" + marritalStatus);
		usermap.put("bloodGroup", "" + bloodGroup);
		usermap.put("licence", "" + licence);
		usermap.put("phone2", "" + "92" + phone2);
		usermap.put("degree", "" + degree);
		usermap.put("college", "" + college);
		usermap.put("dateOfStart", "" + dateOfStart);
		usermap.put("dateOfEnd", "" + dateOfEnd);
		usermap.put("percentage", "" + percentage);
		usermap.put("vehicle", "" + vehicle);
		usermap.put("imageId", ImageId + "");

		if (option.equals("Submit")) {

			String district = request.getParameter("district");

			System.out.print("jhfjh " + district);
			int d_id = Integer.parseInt(district);
			// int imageId=Integer.parseInt(ImageId);
			// DistrictOfficerBAL.updatedistrict(d_id);

			String user_type_name = "District Officer";
			int user_type_value = 101;

			Part filePart = request.getPart("photo");
			InputStream input = null;
			if (filePart != null) {
				String fileName = getFileName(filePart);
				if (fileName != null) {
					System.out.println("File:" + fileName);
				}
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				input = filePart.getInputStream();
			}

			usermap.put("user_type_value", "" + user_type_value);
			usermap.put("d_id", "" + d_id);
			usermap.put("input", "" + input);

			if (UserBAL.checkDistrict(d_id) == false || UserBAL.checkEmail(email) == false) {
				System.out.println("UserId : " + userId);

				ArrayList<HashMap<String, String>> clist = new ArrayList<HashMap<String, String>>();
				HashMap<String, String> childmap = new HashMap<>();

				String str1 = "Welcome to Nizam Energy. Your login credentials are: Userid: " + email + " || Password: "
						+ password;
				String str2 = "Welcome " + firstName
						+ " to Nizam Energy as Service Operation Cotroller. your login id/email is: " + email
						+ " and password is: " + password + " .";
				String str3 = "Welcome " + firstName + " to Nizam Energy as Call Center Agent. your login id/email is: "
						+ email + " and password is: " + password + " .";

				// HashMap<String, String> mailmap = new HashMap<>();
				// if (email.length() > 1) {
				// // SendmailBAL.sendEmailRegisterSolarPayGo(email, password,
				// // firstName, user_type_name);
				// mailmap.put("email", "" + email);
				// mailmap.put("password", "" + password);
				// mailmap.put("firstName", "" + firstName);
				// mailmap.put("user_type_name", "" + user_type_name);
				// }

				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$dfsdfsaf" + ImageId);

				int id = UserBAL.insertUser(usermap);
				if (id == 0) {
					request.setAttribute("msg", "somthing going to wrong");
					response.sendRedirect("SuperAdminForm");
				} else {

					// if(user_type_value == 101){
					try {
						System.out.println("===========================");
						System.out.println(phone1);
						System.out.println("=============================");
						telenor.SendSms("92" + phone1.replace("-", ""), str1);
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					response.sendRedirect("DistrictOfficerr");
				}

			} else {
				out.println("<h3>Useremail or District is already added.</h3>");
			}

		} else if (option.equals("Update")) {

			String user_id = request.getParameter("doId");
			usermap.put("user_id", "" + user_id);
			System.out.println("####################Yaseen " + usermap);
			out.print(usermap);
			UserBAL.updateUser(usermap);
			/*
			 * String str1 =
			 * "Welcome to Nizam Energy. Your login credentials are: Userid: "
			 * +email+" || Password: "+password ; try {
			 * telenor.SendSms("92"+phone1.replace("-", ""), str1); } catch
			 * (ServiceException | JSONException | SQLException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */
			response.sendRedirect("DistrictOfficerr");

		}

	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	private static String getFileName(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE
																													// fix.
			}
		}
		return null;
	}
}
