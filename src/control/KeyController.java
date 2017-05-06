package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import schedule.TestBinary;

/**
 * Servlet implementation class KeyController
 */
@WebServlet("/KeyController")
public class KeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String click = request.getParameter("click");
		System.err.println("************ "+click);
		if (click.equals("insert")) {
			String serial = request.getParameter("serial");
			String duedate = request.getParameter("duedate");
			String code = TestBinary.keyChan(Integer.parseInt(serial),Integer.parseInt(duedate));
			JSONObject json = new JSONObject();
			json.put("key",code);
			out.println(json);
		}else if (click.equals("insertSerial")) {
			String serial = request.getParameter("serial");
			String duedate = request.getParameter("duedate");
			String code = TestBinary.keyChan(Integer.parseInt(serial),876543);
			JSONObject json = new JSONObject();
			json.put("key",code);
			out.println(json);
		}
	}

}
