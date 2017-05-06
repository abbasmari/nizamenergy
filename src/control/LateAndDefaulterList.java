package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import bal.Payment_loanNewBAL;
import bean.UserBean;

/**
 * Servlet implementation class LateAndDefaulterList
 */
@WebServlet("/LateAndDefaulterList")
public class LateAndDefaulterList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(LateAndDefaulterList.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LateAndDefaulterList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSession session = request.getSession();
		// UserBean ubean = (UserBean) session.getAttribute("email");
		// logger.info("User Name : " + ubean.getUserName() + " , UserID : " +
		// ubean.getUserId() + " , IP : "
		// + request.getSession().getAttribute("ipAddress"));

		String action = request.getParameter("click");
		if (action.equals("Generate Late and Defaulter Report")) {
			String category = request.getParameter("categ");
			if (category.equals("DO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("LateAndDefaulterofDistrict");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("DO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Late");
				header.createCell(3).setCellValue("Defaulters");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getDosLateDefaulters();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("doName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("late"));
					dataRow.createCell(3).setCellValue(list.get(i).get("defaulter"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=LateAndDefaulterofDistrict.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("FO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("LateAndDefaulterofFieldOfficer");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Late");
				header.createCell(3).setCellValue("Defaulters");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getFosLateDefaulters();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("late"));
					dataRow.createCell(3).setCellValue(list.get(i).get("defaulter"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=LateAndDefaulterofFieldOfficer.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("customer")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("LateAndDefaulterCustomersList");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Duedate");
				header.createCell(1).setCellValue("Remaining Days");
				header.createCell(2).setCellValue("customerName");
				header.createCell(3).setCellValue("customerNumber");
				header.createCell(4).setCellValue("NdName");
				header.createCell(5).setCellValue("NdNumber");
				header.createCell(6).setCellValue("foName");
				header.createCell(7).setCellValue("foNumber");
				header.createCell(8).setCellValue("doName");
				header.createCell(9).setCellValue("doNumber");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getCustomersLateDefaulters();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("duedate"));
					dataRow.createCell(1).setCellValue(list.get(i).get("remaining_days"));
					dataRow.createCell(2).setCellValue(list.get(i).get("customerName"));
					dataRow.createCell(3).setCellValue(list.get(i).get("customerNumber"));
					dataRow.createCell(4).setCellValue(list.get(i).get("NdName"));
					dataRow.createCell(5).setCellValue(list.get(i).get("NdNumber"));
					dataRow.createCell(6).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(7).setCellValue(list.get(i).get("foNumber"));
					dataRow.createCell(8).setCellValue(list.get(i).get("doName"));
					dataRow.createCell(9).setCellValue(list.get(i).get("doNumber"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=LateAndDefaulterCustomersList.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			}
		} else if (action.equals("Generate Loan Apps Report")) {
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			String category = request.getParameter("category");
			if (category.equals("DO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Loan Apps");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("DO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Pending");
				header.createCell(3).setCellValue("Accepted");
				header.createCell(4).setCellValue("Verified");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getDoWiseLoanApps(from,to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("doName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("pending"));
					dataRow.createCell(3).setCellValue(list.get(i).get("accepted"));
					dataRow.createCell(4).setCellValue(list.get(i).get("varified"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=DOLoanApplicationsReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("FO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Loan Apps");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Pending");
				header.createCell(3).setCellValue("Accepted");
				header.createCell(4).setCellValue("Verified");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getFoWiseLoanApps(from,to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("pending"));
					dataRow.createCell(3).setCellValue(list.get(i).get("accepted"));
					dataRow.createCell(4).setCellValue(list.get(i).get("varified"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=FOLoanApplicationsReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			}
		} else if (action.equals("Generate Last Sale Average Report")) {
			String category = request.getParameter("categ");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			if (category.equals("DO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("DistrictOfficerSalesAverage");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("DO Name");
				header.createCell(1).setCellValue("Distrcit");
				header.createCell(2).setCellValue("Days since Last Sale");
				header.createCell(3).setCellValue("Average");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getDoSalesAverage(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("doName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("handover"));
					dataRow.createCell(3).setCellValue(list.get(i).get("average"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=DistrictOfficerSalesAverage.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("FO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("FieldOfficerSalesAverage");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("Distrcit");
				header.createCell(2).setCellValue("Days since Last Sale");
				header.createCell(3).setCellValue("Average");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getFoSalesAverage(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("handover"));
					dataRow.createCell(3).setCellValue(list.get(i).get("average"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=FieldOfficerSalesAverage.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("ND")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("NizamDostSalesAverage");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("ND Name");
				header.createCell(1).setCellValue("FO Name");
				header.createCell(2).setCellValue("Distrcit");
				header.createCell(3).setCellValue("Days since Last Sale");
				header.createCell(4).setCellValue("Average");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getNdSalesAverage(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("ndName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(2).setCellValue(list.get(i).get("district"));
					dataRow.createCell(3).setCellValue(list.get(i).get("handover"));
					dataRow.createCell(4).setCellValue(list.get(i).get("average"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=NizamDostSalesAverage.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			}
		} else if (action.equals("Generate Loan Apps Report")) {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Loan Apps");
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("DO Name");
			header.createCell(1).setCellValue("Pending");
			header.createCell(2).setCellValue("Accepted");
			header.createCell(3).setCellValue("Varified");
			ArrayList<HashMap<String, String>> list = null;
//			list = Payment_loanNewBAL.getDoWiseLoanApps();
			for (int i = 0; i < list.size(); i++) {
				Row dataRow = sheet.createRow(i + 1);
				dataRow.createCell(0).setCellValue(list.get(i).get("doName"));
				dataRow.createCell(1).setCellValue(list.get(i).get("pending"));
				dataRow.createCell(2).setCellValue(list.get(i).get("accepted"));
				dataRow.createCell(3).setCellValue(list.get(i).get("varified"));
			}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=LoanApplicationsReport.xls");
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
		} else if (action.equals("Generate TOP Report")) {
			String category = request.getParameter("category");
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			if (category.equals("FO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("FO Performance Report");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Sales");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getFoSalesByTop(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("sales"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=TopFOReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("ND")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("DO Performance Report");

				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("ND Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("Sales");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getNdSalesByTop(from, to);
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("ndName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("sales"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=TopNDReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			}
		} else if (action.equals("Generate CreditScore Report")) {
			String category = request.getParameter("category");
			if (category.equals("DO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Credit Score");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("DO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("rating");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getDoRating();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("doName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("rating"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=DoCreditScoreReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("FO")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Credit Score");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("FO Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("rating");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getFoRating();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("rating"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=FOCreditScoreReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("ND")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Credit Score");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("ND Name");
				header.createCell(1).setCellValue("District");
				header.createCell(2).setCellValue("rating");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getNizamDostRating();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("NdName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("district"));
					dataRow.createCell(2).setCellValue(list.get(i).get("rating"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=NdCreditScoreReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			} else if (category.equals("Customer")) {
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Credit Score");
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Customer");
				header.createCell(1).setCellValue("Rating");
				header.createCell(2).setCellValue("ND Name");
				header.createCell(3).setCellValue("Fo Name");
				header.createCell(4).setCellValue("Do Name");
				ArrayList<HashMap<String, String>> list = null;
				list = Payment_loanNewBAL.getCustomersRating();
				for (int i = 0; i < list.size(); i++) {
					Row dataRow = sheet.createRow(i + 1);
					dataRow.createCell(0).setCellValue(list.get(i).get("customerName"));
					dataRow.createCell(1).setCellValue(list.get(i).get("rating"));
					dataRow.createCell(2).setCellValue(list.get(i).get("NdName"));
					dataRow.createCell(3).setCellValue(list.get(i).get("foName"));
					dataRow.createCell(4).setCellValue(list.get(i).get("doName"));
				}
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=CustomerCreditScoreReport.xls");
				workbook.write(response.getOutputStream());
				response.getOutputStream().flush();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
