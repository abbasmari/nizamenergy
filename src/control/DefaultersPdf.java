package control;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bal.Payment_loanNewBAL;
import bean.UserBean;

/**
 * Servlet implementation class DefaultersPdf
 */
@WebServlet("/DefaultersPdf")
public class DefaultersPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(DefaultersPdf.class);

	
	public DefaultersPdf() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession();

			UserBean ubean = (UserBean) session.getAttribute("email");
			logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId() + " , IP : "
					+ request.getSession().getAttribute("ipAddress"));

			ArrayList<HashMap<String, String>> list = Payment_loanNewBAL.getLoanBookList();

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();

			Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
			Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLDITALIC);

			document.setMarginMirroring(true);

			Paragraph pa = new Paragraph();
			Chunk top = new Chunk("Customer Information Details", boldFont);
			top.setLocalDestination("top");
			pa.setAlignment(450);
			pa.add(top);

			try {
				document.add(pa);
			} catch (DocumentException e) {
				logger.error(e);
				e.printStackTrace();
			}

			try {
				document.add(Chunk.NEWLINE);
			} catch (DocumentException e) {
				logger.error(e);
				e.printStackTrace();
			}
			document.setMarginMirroring(true);

			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(90);
			// PdfPCell cell = new PdfPCell();

			// table.addCell(new Phrase("Remaining Days", normalFont));
			table.addCell(new Phrase("Due Date", normalFont));
			table.addCell(new Phrase("Customer Name", normalFont));
			table.addCell(new Phrase("Customer Number", normalFont));
			table.addCell(new Phrase("FO Name", normalFont));
			table.addCell(new Phrase("FO Number", normalFont));
			table.addCell(new Phrase("ND Name", normalFont));
			table.addCell(new Phrase("ND Number", normalFont));
			
			table.addCell(new Phrase("Monthly Pay", normalFont));
			// table.addCell(new Phrase("Remaining Balance", normalFont));

			for (int i = 0; i < list.size(); i++) {

				// table.addCell("" + list.get(i).get("remaining_days"));
				table.addCell("" + list.get(i).get("dueDate"));
				// table.addCell("" + list.get(i).get("statusGet"));
				table.addCell("" + list.get(i).get("customerName"));
				table.addCell("" + list.get(i).get("customerNumber"));
				table.addCell("" + list.get(i).get("foName"));
				table.addCell("" + list.get(i).get("foNumber"));
				table.addCell("" + list.get(i).get("ndName"));
				table.addCell("" + list.get(i).get("ndNumber"));
				table.addCell("" + list.get(i).get("monthlyPay"));
			}
			try {
				document.add(table);
			} catch (DocumentException e) {
				logger.error(e);
				e.printStackTrace();
			}

			document.close();

			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");

			response.setContentType("application/pdf");

			response.setContentLength(baos.size());

			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();

		} catch (Exception ee) {
			logger.error(ee);
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
