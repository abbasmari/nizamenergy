package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import bal.CallingXML;
import bal.CashSale;

/**
 * Servlet implementation class AddCashCustomer
 */
@WebServlet("/AddCashCustomer")
public class AddCashCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCashCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// TODO Auto-generated method stub
		int scity = Integer.parseInt(request.getParameter("scity"));
		String sfirstname = request.getParameter("sfirstname");
		String customerNic = request.getParameter("customerNic");
		String saddress = request.getParameter("saddress");
		int fo_id = Integer.parseInt(request.getParameter("fo_id"));
		int nd_id = Integer.parseInt(request.getParameter("nd_id"));
		String phone1 =request.getParameter("phone1");
		phone1="92"+phone1.replaceAll("-","");
		String options[] =request.getParameterValues("product_id");
		String msg=null;
		int product_price = Integer.parseInt(request.getParameter("product_id"));
		String consumer_no = request.getParameter("consumer_no");
		String payments = request.getParameter("payment_method");
		String gsm_no = request.getParameter("gsm_no");
		System.out.println("  scity  "+ scity );
		System.out.println("sfirstname    "+ sfirstname );
		System.out.println("customerNic    "+ customerNic );
		System.out.println(" saddress   "+  saddress);
		System.out.println(" fo_id   "+ fo_id );
		System.out.println(" nd_id   "+ nd_id );
		System.out.println(" product_id   "+ product_price );
		System.out.println(" consumer_no   "+  consumer_no);
		System.out.println(" gsm_no   "+ gsm_no );
		System.out.println("*****************************");
		System.out.println(" payments   "+ payments );
		System.out.println("*****************************");
		if(payments.equals("Cash")){
			
			System.out.println("Cash PAYMENTSSS  ");
			msg="Thank you for purchasing our system . Hope this appliance lightened your life.";
			CashSale.enter_cash_sale_OvertheCounter(fo_id, nd_id, scity, product_price, sfirstname,customerNic, phone1, saddress, consumer_no, gsm_no,options[0]);
			request.setAttribute("msg",msg);
			RequestDispatcher rd = request.getRequestDispatcher("CashCustomer.jsp");
		}
		else {
		String msg_send_string="Aapko Nizam Bijli mein khush amdeed kehte he, Aap apna bill Mobile Money k zariae apni device ka bill ada karwae iss CONSUMER par "+consumer_no ;
		if(fo_id==0){
			
			if(!CashSale.enter_cash_sale_oneCommission(nd_id, 101, scity, product_price, sfirstname,customerNic, phone1, saddress, consumer_no, gsm_no,options[0])){
				try {
					CallingXML.SendMessage(phone1, msg_send_string);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg="<p>Your application is submited successfully. Please pay bill with Consumer no i.e  "+consumer_no+"</p><br> Please Click <a href='/NizamEnergyProject/DoCashCustomer.jsp'>Here </a> to view payment details";
				
			}
			else {
				msg="<p>Your application is Not submited successfully. Please Re Submit your Form ";
				
			}
			//CashSale.enter_cash_sale_oneCommission(nd_fo_id, nd_fo_type, tehsil_id, amount, customer_name, customer_cnic, phone, address, imei_number, gsm);
		}
		else if (nd_id==0){
			if(!CashSale.enter_cash_sale_oneCommission(fo_id, 100, scity, product_price, sfirstname,customerNic, phone1, saddress, consumer_no, gsm_no,options[0])){
				try {
					CallingXML.SendMessage(phone1, msg_send_string);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg="<p>Your application is Not submited successfully. Please pay bill with Consumer no i.e  "+consumer_no+"</p><br> Please Click <a href='/NizamEnergyProject/DoCashCustomer.jsp'>Here </a> to view payment details";
				
			}
			else {
				msg="<p>Your application is Not submited successfully. Please Re Submit your Form ";
				
			}
			
		}
		else {
			
			if(!CashSale.enter_cash_sale_TwoCommission(fo_id, nd_id, scity, product_price, sfirstname,customerNic, phone1, saddress, consumer_no, gsm_no,options[0])){
				try {
					CallingXML.SendMessage(phone1, msg_send_string);
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg="<p>Your application is submited successfully. Please pay bill with Consumer no i.e  "+consumer_no+"</p><br> Please Click <a href='/NizamEnergyProject/DoCashCustomer.jsp'>Here </a> to view payment details";
				
			}
			else {
				msg="<p>Your application is Not submited successfully. Please Re Submit your Form ";
				
			}
			//CashSale.enter_cash_sale_TwoCommission(fo_id, nd_id, tehsil_id, amount, customer_name, customer_cnic, phone, address, imei_number, gsm);
		}}
		request.setAttribute("msg",msg);
		RequestDispatcher rd = request.getRequestDispatcher("CashCustomer.jsp");
		rd.forward(request,response);
		
		
	}

}
