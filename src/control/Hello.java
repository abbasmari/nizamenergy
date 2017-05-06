package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import connection.Connect;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class Hello extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Hello.class);

	static Connection con = null;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserBean ubean = (UserBean) session.getAttribute("email");
		logger.info("User Name : " + ubean.getUserName() + " , UserID : " + ubean.getUserId()+" , IP : "+request.getRemoteAddr());

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

			response.setContentType("image/gif");
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
			/*response.sendRedirect("Newjsp2");*/
			RequestDispatcher rd = request.getRequestDispatcher("Newjsp2");
			rd.forward(request, response);
			rs.close();
			stmt.close();
			if(conn != null){
				conn.close();				
			}
		} catch (SQLException e) {
			response.setContentType("text/html");
			out.println("<html><head><title>Error: Person Photo</title></head>");
			out.println("<body><h1>Error=" + e.getMessage()
					+ "</h1></body></html>");
			return;
		} finally {
		}

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	public static Connection getMySqlConnection() throws Exception {
		Properties properties = new Properties();
		File file = new File("src/database.properties");
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		}

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/noornizam";
		String username = "root";
		String password = "root";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, properties);
		return conn;
	}
}
