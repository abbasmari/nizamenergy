package bal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import bean.CustomerProfileViewBean;
import bean.FinanceBean;
import bean.GuarantorBean;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf_generator {

	final static Logger logger = Logger.getLogger(AccessControlBAL.class);

	public static PdfPTable createTable5() throws DocumentException {
		PdfPTable table = new PdfPTable(3);

		try {
			table.setTotalWidth(new float[] { 144, 72, 72 });
			table.setLockedWidth(true);
			PdfPCell cell, cell1, cell2, cell3;
			cell = new PdfPCell(new Phrase("Table 5"));
			cell1 = new PdfPCell(new Phrase("Table 15"));
			cell2 = new PdfPCell(new Phrase("Table 25"));
			cell3 = new PdfPCell(new Phrase("Table 35"));
			table.addCell(cell);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);

			table.addCell("row 1; cell 1");
			table.addCell("row 1; cell 2");
			table.addCell("row 2; cell 1");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
			table.addCell("row 2; cell 2");
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return table;
	}

	public static void pdg_generate(String customer_phone)
			throws DocumentException, IOException {
		final String RESULT = "C:\\Users\\NizamEnergy\\Desktop\\Pdf\\"
				+ customer_phone + ".pdf";
		try {

			CustomerProfileViewBean customerbean = CustomerBAL
					.getCustomerProfile(customer_phone);
			// step 1
			// landscape format using the rotate() method
			GuarantorBean familygaurantor = CustomerBAL.getGuaranterDetailById(
					customerbean.getCustomerId(), 1);
			GuarantorBean outsidergaurantor = CustomerBAL
					.getGuaranterDetailById(customerbean.getCustomerId(), 2);
			ArrayList<HashMap<String, String>> appliancesInAccount = CustomerBAL
					.getApplianceInAccount(customerbean.getCustomerId());
			Document document = new Document(PageSize.LETTER.rotate());
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(RESULT));
			// step 3
			document.open();
			document.add(new Paragraph(new Date().toString()));
			// step 4

			document.addCreationDate();

			// step 5

			Image image = Image
					.getInstance("C:\\Users\\NizamEnergy\\Desktop\\nizam-solar-energy-logo.png");
			// PdfPCell cell2 = new PdfPCell(image, false);
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
					Font.BOLDITALIC);

			document.add(image);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Customer Details", boldFont));

			// add(new Paragraph("Customer Details"));
			document.add(Chunk.NEWLINE);

			Phrase p = new Phrase(
					"Dr. iText or: How I Learned to Stop Worrying "
							+ "and Love the Portable Document Format.");
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(90);
			PdfPCell cell = new PdfPCell(p);

			table.addCell(new Phrase("Full Name *:", normalFont));
			table.addCell(customerbean.getCustomerName());
			table.addCell(new Phrase("Father Name *:", normalFont));
			cell.setLeading(20f, 0f);
			table.addCell(customerbean.getFatherName());
			table.addCell(new Phrase("Gender *:", normalFont));
			cell.setLeading(3f, 1.2f);
			table.addCell(customerbean.getGender());
			table.addCell(new Phrase("CNIC  *:", normalFont));
			cell.setLeading(0f, 1.2f);
			table.addCell(customerbean.getCnicNo());
			table.addCell(new Phrase("Phone Number (primary) *:", normalFont));
			cell.setLeading(0f, 0f);
			table.addCell(customerbean.getPhoneNo());
			cell = new PdfPCell(
					new Phrase(
							"Dr. iText or: How I Learned to Stop Worrying and Love PDF"));
			table.addCell(new Phrase("Phone Number (Secondary) *:", normalFont));
			cell.setPadding(10);
			table.addCell(customerbean.getPhoneNo2());
			table.addCell(new Phrase("Marital Status *:", normalFont));
			cell.setPadding(0);
			table.addCell(customerbean.getMarritalStatus());
			table.addCell(new Phrase("Education *:", normalFont));
			cell.setPaddingLeft(20);
			cell.setPaddingRight(50);
			cell.setPaddingTop(0);
			cell.setPaddingBottom(5);
			table.addCell(customerbean.getEducation());
			p = new Phrase("iText in Action Second Edition");
			table.getDefaultCell().setPadding(2);
			table.getDefaultCell().setUseAscender(false);
			table.getDefaultCell().setUseDescender(false);
			table.addCell(new Phrase("Current Address:", normalFont));
			table.addCell(customerbean.getAddress());
			document.add(table);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Total Monthly Income", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_income = new PdfPTable(2);
			table_income.setWidthPercentage(90);

			table_income.addCell(new Phrase("Salary/Pension", normalFont));
			table_income.addCell("" + customerbean.getSalary());
			table_income.getDefaultCell().setUseAscender(false);
			table_income.getDefaultCell().setUseDescender(true);
			table_income.addCell(new Phrase("Business", normalFont));
			table_income.addCell(customerbean.getBusinessName());
			table_income.getDefaultCell().setUseAscender(true);
			table_income.getDefaultCell().setUseDescender(true);
			table_income.addCell(new Phrase("Farming", normalFont));

			table_income.addCell("" + customerbean.getFarming());
			table_income.getDefaultCell().setUseAscender(true);
			table_income.getDefaultCell().setUseDescender(true);
			table_income.addCell(new Phrase("Family Contribution", normalFont));
			table_income.addCell(customerbean.getFamilyIncome());
			table_income.getDefaultCell().setUseAscender(true);
			table_income.getDefaultCell().setUseDescender(true);
			table_income.addCell(new Phrase("Other Income", normalFont));

			table_income.getDefaultCell().setPadding(2);
			table_income.getDefaultCell().setUseAscender(false);
			table_income.getDefaultCell().setUseDescender(false);
			table_income.addCell(customerbean.getOtherAssets());
			document.add(table_income);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Business Detail", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_bussiness = new PdfPTable(2);
			table_income.setWidthPercentage(90);

			table_bussiness.addCell(new Phrase("Title *", normalFont));
			table_bussiness.addCell(customerbean.getBusinessName());
			table_bussiness.getDefaultCell().setUseAscender(false);
			table_bussiness.getDefaultCell().setUseDescender(true);
			table_bussiness.addCell(new Phrase("Type *", normalFont));
			table_bussiness.addCell(customerbean.getBusinessType());
			table_bussiness.getDefaultCell().setUseAscender(true);
			table_bussiness.getDefaultCell().setUseDescender(true);
			table_bussiness.addCell(new Phrase("Time Period * ", normalFont));
			table_bussiness.addCell(customerbean.getPeriod());
			table_bussiness.getDefaultCell().setUseAscender(true);
			table_bussiness.getDefaultCell().setUseDescender(true);
			table_bussiness.addCell(new Phrase("Type *", normalFont));
			table_bussiness.addCell("" + customerbean.getBusinessIncome());
			table_bussiness.getDefaultCell().setUseAscender(true);
			table_bussiness.getDefaultCell().setUseDescender(true);

			table_bussiness.addCell(new Phrase("Address * ", normalFont));
			table_bussiness.addCell(customerbean.getBusinessAddress());
			table_bussiness.getDefaultCell().setUseAscender(true);
			table_bussiness.getDefaultCell().setUseDescender(true);

			table_bussiness.addCell(new Phrase("Comments", normalFont));

			table_bussiness.getDefaultCell().setPadding(2);
			table_bussiness.getDefaultCell().setUseAscender(false);
			table_bussiness.getDefaultCell().setUseDescender(false);
			table_bussiness.addCell(p);
			document.add(table_bussiness);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Employment Detail", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_Employment = new PdfPTable(2);
			table_income.setWidthPercentage(90);

			table_Employment.addCell(new Phrase("Company/Org. Name *",
					normalFont));
			table_Employment.addCell(customerbean.getOrganisationName());
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(true);
			table_Employment.addCell(new Phrase("Designation *", normalFont));
			table_Employment.addCell(customerbean.getOccpation());
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(true);
			table_Employment.addCell(new Phrase("Job Period *", normalFont));
			table_Employment.addCell(customerbean.getJobPeriod());
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(true);
			table_Employment
					.addCell(new Phrase("Office Phone No *", normalFont));
			table_Employment.addCell(customerbean.getOfficePhone());
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(true);
			table_Employment
					.addCell(new Phrase("Supervisor Name *", normalFont));
			table_Employment.addCell(customerbean.getSupervisorName());
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(true);

			table_Employment.addCell(new Phrase("Address *", normalFont));
			table_Employment.getDefaultCell().setPadding(2);
			table_Employment.getDefaultCell().setUseAscender(false);
			table_Employment.getDefaultCell().setUseDescender(false);
			table_Employment.addCell(customerbean.getOrgAddress());
			document.add(table_Employment);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Guarantor Details", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_Guarantor = new PdfPTable(5);
			table_income.setWidthPercentage(100);

			table_Guarantor.addCell(new Phrase("Name", normalFont));
			table_Guarantor.addCell(new Phrase("CNIC", normalFont));
			table_Guarantor.addCell(new Phrase("Phone", normalFont));
			table_Guarantor.addCell(new Phrase("Relationship", normalFont));
			table_Guarantor.addCell(new Phrase("Monthly Income", normalFont));

			table_Guarantor.addCell(familygaurantor.getGguarantorName());
			table_Guarantor.addCell(familygaurantor.getGguarantorCnic());
			table_Guarantor.addCell(familygaurantor.getGguarantorPhone());
			table_Guarantor.addCell(familygaurantor.getgRelationToCustomer());
			table_Guarantor.addCell(familygaurantor.getGguarantorIncome());

			table_Guarantor.addCell(outsidergaurantor.getGguarantorName());
			table_Guarantor.addCell(outsidergaurantor.getGguarantorCnic());
			table_Guarantor.addCell(outsidergaurantor.getGguarantorPhone());
			table_Guarantor.addCell(outsidergaurantor.getgRelationToCustomer());
			table_Guarantor.addCell(outsidergaurantor.getGguarantorIncome());

			document.add(table_Guarantor);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Appliance Details", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_appliance = new PdfPTable(3);
			table_income.setWidthPercentage(90);

			table_appliance.addCell(new Phrase("Appliance Name", normalFont));

			table_appliance.addCell(new Phrase("Appliance Price", normalFont));
			table_appliance.addCell(new Phrase("Appliance IMEI", normalFont));
			for (int i = 0; i < appliancesInAccount.size(); i++) {
				table_appliance.addCell(appliancesInAccount.get(i).get(
						"ApplianceName"));
				table_appliance.addCell(appliancesInAccount.get(i).get(
						"appliance_price"));

				table_appliance.addCell(appliancesInAccount.get(i).get(
						"imei_number"));
			}

			document.add(table_appliance);

			// step 5
			document.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void payment_Report() throws DocumentException,
			MalformedURLException, IOException {
		Date current_date = null;
		final String RESULT = "C:\\Users\\NizamEnergy\\Desktop\\Pdf\\"
				+ current_date + ".pdf";

		try {

			// step 1
			// landscape format using the rotate() method
			Document document = new Document(PageSize.LETTER.rotate());
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(RESULT));
			// step 3
			document.open();
			document.add(new Paragraph(new Date().toString()));
			// step 4

			document.addCreationDate();

			// step 5

			Image image = Image
					.getInstance("C:\\Users\\NizamEnergy\\Desktop\\nizam-solar-energy-logo.png");
			// PdfPCell cell2 = new PdfPCell(image, false);
			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 13,
					Font.BOLDITALIC);

			document.add(image);
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			document.add(new Phrase(" Total Transactions Details", boldFont));

			// add(new Paragraph("Customer Details"));
			document.add(Chunk.NEWLINE);
			double Down_payment = 0;
			double total_installments = 0;

			List<FinanceBean> list = FinanceBAL.getPayments();

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getInstallment_paid() > 1500) {
					Down_payment = Down_payment
							+ list.get(i).getInstallment_paid();
				}
				total_installments = total_installments
						+ list.get(i).getInstallment_paid();
			}

			Phrase p = new Phrase(
					"Dr. iText or: How I Learned to Stop Worrying "
							+ "and Love the Portable Document Format.");
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(70);
			table.setSpacingBefore(0f);
			table.setSpacingAfter(0f);

			PdfPCell cell = new PdfPCell(p);

			table.addCell(new Phrase("Total Down Payments *:", normalFont));
			table.addCell("Rs:" + Down_payment);
			table.addCell(new Phrase("Total First Installments *:", normalFont));
			cell.setLeading(20f, 0f);
			table.addCell("Rs:" + total_installments);
			table.addCell(new Phrase("Total Monthly Installments  *:",
					normalFont));
			cell.setLeading(3f, 1.2f);
			table.addCell(p);
			table.addCell(new Phrase("Total Installments After Due date  *:",
					normalFont));
			cell.setLeading(0f, 1.2f);
			table.addCell(p);
			table.addCell(new Phrase("Total Installments Before Due date *:",
					normalFont));
			cell.setLeading(0f, 0f);
			table.addCell(p);
			cell = new PdfPCell(
					new Phrase(
							"Dr. iText or: How I Learned to Stop Worrying and Love PDF"));
			table.addCell(new Phrase("Total Amount :", normalFont));
			table.setWidthPercentage(100);
			table.addCell("Rs:" + total_installments + Down_payment);
			document.add(table);

			document.add(Chunk.NEWLINE);
			document.add(new Phrase("Transactions Details", boldFont));
			document.add(Chunk.NEWLINE);
			PdfPTable table_Guarantor = new PdfPTable(6);
			table_Guarantor.setWidthPercentage(100);
			table_Guarantor.setSpacingBefore(0f);
			table_Guarantor.setSpacingAfter(0f);

			table_Guarantor.addCell(new Phrase("Customer Name", normalFont));
			table_Guarantor.addCell(new Phrase("Bank Name", normalFont));
			table_Guarantor.addCell(new Phrase("Amount Paid", normalFont));

			table_Guarantor.addCell(new Phrase("Paid Date", normalFont));
			table_Guarantor.addCell(new Phrase("IMEI Number", normalFont));
			table_Guarantor
					.addCell(new Phrase("Transaction Number", normalFont));
			for (int i = 0; i < list.size(); i++) {
				table_Guarantor.addCell(list.get(i).getCustomer_name());
				table_Guarantor.addCell(list.get(i).getBank_name());
				table_Guarantor.addCell("Rs: "
						+ list.get(i).getInstallment_paid());

				table_Guarantor.addCell(list.get(i).getDate());
				table_Guarantor.addCell(list.get(i).getImei_number());
				table_Guarantor.addCell(list.get(i).getTransaction_id());

			}

			document.add(table_Guarantor);
			document.add(Chunk.NEWLINE);
			document.close();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}

		// step 5

	}

	// Read more:
	// http://javarevisited.blogspot.com/2012/04/java-program-to-print-prime-numbers-in.html#ixzz421JDQgMj
	public static void main(String argv[]) throws DocumentException,
			IOException {
		Document document = new Document();
		// payment_Report();
		pdg_generate("923002023880");
		// - See more at:
		// http://www.java2novice.com/java-interview-programs/duplicate-number/#sthash.mskk9Oxz.dpuf

		// step 2
		// PdfWriter.getInstance(document, new FileOutputStream(RESULT));
		// step 3
		// document.open();
		// step 4
		// payment_Report();
		// Date current_date = new Date();
		// System.out.println(current_date);
		// PdfPTable table = createTable5();
		// document.add(table);
		// step 5
		// document.close();
	}
}
