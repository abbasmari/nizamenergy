package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import bal.CustomerBAL;
import bal.SalesmanBAL;
import bal.TargetsBAL;
import bean.SalesManBean;
import bean.TargetBean;

import bean.UserBean;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/CustomerFilter")
public class CustomerFilter extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(CustomerFilter.class);

	public CustomerFilter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try (PrintWriter out = response.getWriter()) {

			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			String salesid = request.getParameter("salesmanid");
			if (salesid.equalsIgnoreCase("")) {

			} else {

				JSONArray SalesArray = new JSONArray();
				JSONObject salesJSON = new JSONObject();

				System.err.println(salesid + " ========");
				int salesmanId = Integer.parseInt(salesid);
				SalesManBean firstfilter = SalesmanBAL.getFirstFilterSallesman(salesmanId);
				if (firstfilter != null) {
					int per = 0;

					TargetBean targetBean = TargetsBAL.getTargetsBySalesmanId(firstfilter.getSalesmanId());
					int achive = TargetsBAL.getRecovries(firstfilter.getSalesmanId(), targetBean.getTarget_start_on(),
							targetBean.getTarget_end_on());
					System.out.println("Achive::::::: " + achive);
					String[][] customers = CustomerBAL.getCustomerBySalesmanId(firstfilter.getSalesmanId());
					System.err.println("Length===" + customers.length);

					try {
						per = (100 / customers.length) * achive;

						if (per >= 98) {
							System.err.println("persentage " + per);
							int sales = TargetsBAL.getSalesOfCurrentMonthBySalesmanId(salesmanId);
							if (sales != 0) {
								if (sales == 50) {
									salesJSON.put("salesmanName",
											"Mr. " + firstfilter.getName() + " your are cross the limit of Appliance");
								} else {
									salesJSON.put("salesmanName",
											"Mr. " + firstfilter.getName() + " your have assigned new Appliance");
								}
							} else {
							}

						} else {

							System.err.println("persentage else " + per);
							salesJSON.put("salesmanName",
									"You have not Issued More Appliances Mr. " + firstfilter.getName());
							System.err.println(firstfilter.getName() + " Name");

						}
						System.out.print(per + " persentage\n");
					} catch (Exception e) {
						e.getMessage();
					}
				} else {
					System.err.println(salesid + " is not a new customer");
				}
				SalesArray.put(salesJSON);
				out.println(SalesArray);
			}
		}
	}

}
