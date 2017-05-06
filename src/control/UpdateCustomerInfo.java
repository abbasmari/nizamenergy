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

import org.apache.log4j.Logger;

import bal.CustomerBAL;
import bal.UpdateCustomerBAL;
import bean.UserBean;

/**
 * Servlet implementation class UpdateCustomerInfo
 */
@WebServlet("/UpdateCustomerInfo")
public class UpdateCustomerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UpdateCustomerInfo.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCustomerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
				+ request.getSession().getAttribute("ipAddress"));

		PrintWriter out = response.getWriter();
		String option = request.getParameter("click");
		System.out.println(option);
		if (option.equalsIgnoreCase("update")) {
			int id = Integer.parseInt(request.getParameter("CustomerId"));
			String name = request.getParameter("fullname");
			String cnic = request.getParameter("cnic");
			String datofb = request.getParameter("dob");
			String address = request.getParameter("address");
			String city1 = request.getParameter("city");
			int city = Integer.parseInt(city1);
			String phone = request.getParameter("phone");
			String phone2 = request.getParameter("phone2");

			int monthlyIncom = Integer.parseInt(request.getParameter("income"));
			int familyIncome = Integer.parseInt(request.getParameter("fincome"));

			String fatherName = request.getParameter("fathername");
			String motherName = request.getParameter("mothername");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			// int age=Integer.parseInt(request.getParameter("age"));
			String relation = request.getParameter("status");
			String iseducated = request.getParameter("educated");
			boolean educated = false;
			if (!iseducated.isEmpty()) {
				educated = true;
			} else {
				educated = false;
			}
			String education = request.getParameter("education");
			String IncomeSource = request.getParameter("source");
			String number = (String) request.getAttribute("number");
			UpdateCustomerBAL.UpdateCustomerInfo(name, cnic, datofb, address, city, "92" + phone, phone2, monthlyIncom,
					familyIncome, fatherName, motherName, email, gender, 23, relation, educated, education,
					IncomeSource, 0, "pakory wala", "92" + phone);
			String customrId = request.getParameter("customerid");
			int customerId = 0;
			CustomerBAL.updateCustomerNotifi(id);
			RequestDispatcher rd = request.getRequestDispatcher("LoanRequest");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
