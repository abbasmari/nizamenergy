package security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bal.CallingXML;
import bal.SendmailBAL;
import bal.UserBAL;

/**
 * Servlet implementation class SendRandomGeneratedPassword
 */
@WebServlet("/SendRandomGeneratedPassword")
public class SendRandomGeneratedPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRandomGeneratedPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hsdgfh");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			String key = request.getParameter("key");
			String message = request.getParameter("password");
			String number = "92"+request.getParameter("mobileNumber");
			if(key.equalsIgnoreCase("sendMsg")){
	
				System.out.println("Number : "+number);
				
			}else if(key.equalsIgnoreCase("sendEmail")){
				
				
				String email = UserBAL.getUserEmailByMobileNumber(number);
				int r = (int)(Math.random()*10000);
		        String password = number+""+r;
		        String pass = Hashing.getMD5(password);
		        UserBAL.updateUserPassword(pass, email);
		      //SendmailBAL.sendEmailForNewPassword(email, password);
		        String Str="Your new password is :"+password;
		        CallingXML.SendMessage(number, Str);
			}
			/*response.sendRedirect("SolarHomeSystemLogin");*/
			RequestDispatcher rs = request.getRequestDispatcher("SolarHomeSystemLogin");
            rs.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
