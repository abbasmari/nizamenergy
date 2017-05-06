package control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import connection.Connect;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet("/Hello2")
public class Hello2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Hello2.class);

	public Hello2() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : "
				+ ubean.getUserId()+" , IP : "+request.getRemoteAddr());

		int id = Integer.parseInt(request.getParameter("customerId"));

		System.out.println("the ID is: " + id);
		Blob photo = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String query = "select customr_image from customer where  customer_id ="
				+ id + "";
		ServletOutputStream out = response.getOutputStream();

		try {
		} catch (Exception e) {
			response.setContentType("text/html");
			out.println("<html><head><title>Person Photo</title></head>");
			out.println("<body><h1>Database Connection Problem.</h1></body></html>");
			return;
		}
		Connection con = Connect.getConnection();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				photo = rs.getBlob(1);
			} else {
				response.setContentType("text/html");
				out.println("<html><head><title>Person Photo</title></head>");
				out.println("<body><h1>No photo found for id= 001 </h1></body></html>");
				return;
			}

			response.setContentType("image/jpg");
			InputStream in = photo.getBinaryStream();
			int length = (int) photo.length();

			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			while ((length = in.read(buffer)) != -1) {
				System.out.println("writing " + length + " bytes");
				out.write(buffer, 0, length);

			}

			in.close();
			out.flush();
			System.err.print("This is heeloo");
		} catch (SQLException e) {
			response.setContentType("text/html");
			out.println("<html><head><title>Error: Person Photo</title></head>");
			out.println("<body><h1>Error=" + e.getMessage()
					+ "</h1></body></html>");
			return;
		} finally {
			try {
				if (conn != null) {
					rs.close();
					stmt.close();
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}