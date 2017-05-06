package bal;

import java.rmi.RemoteException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.systems.pg.partner.transaction.IPartnerBusinessService;
import com.systems.pg.partner.transaction.PartnerBusinessServiceLocator;
import com.systems.pg.partner.transaction.dto.InitiateTransactionRequestType;
import com.systems.pg.partner.transaction.dto.InitiateTransactionResponseType;
import com.systems.pg.partner.transaction.dto.TransactionType;

public class Easypay {
	
	final static Logger logger = Logger.getLogger(Easypay.class);

	public static void CreateToken(String number) throws RemoteException,
			ServiceException {

		InitiateTransactionRequestType parameters1 = new InitiateTransactionRequestType();

		SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		parameters1.setEmailAddress("");
		parameters1.setMobileAccountNo("");
		parameters1.setMsisdn(number);
		parameters1.setOrderId("1235abc12");
		parameters1.setPassword("edef520a41ed2372220295a204680679");
		parameters1.setStoreId(1426);

		parameters1.setTransactionAmount(123.01);
		TransactionType type = null;
		parameters1.setTransactionType(type.OTC);

		parameters1.setUsername("NizamE");
		PartnerBusinessServiceLocator serviceloc = new PartnerBusinessServiceLocator();

		IPartnerBusinessService service = (IPartnerBusinessService) serviceloc
				.getPartnerBusinessServicePort();
		InitiateTransactionResponseType outp = null;

		System.out.println("Here ...");
		System.out.println("Password   " + parameters1.getPassword());
		outp = service.initiateTransaction(parameters1);
		System.out.println("Response code " + outp.getResponseCode());
		System.out.println("Token " + outp.getPaymentToken());
		System.out.println("OrderID " + outp.getOrderId());
		System.out.println("Transaction time "
				+ outp.getTransactionDateTime().getTime());
		String date = mdyFormat.format(outp.getTransactionDateTime().getTime());
		System.out.println("String in Date formate  " + date);
		insertTokendetails(outp.getPaymentToken(), outp.getOrderId(), date);
		System.out.println("Transaction " + outp.getTransactionRefNumber());
		String newNumber = number.substring(1);
		newNumber = "+92" + newNumber;
		// telenor.SendSms(newNumber,
		// "Dear Customer please go to any easypaisa shop and pay to bill against this token number "+outp.getPaymentToken());
	}

	public static int insertTokendetails(String token_name, String order_id,
			String transaction_time) {

		String query = "INSERT INTO tokensinfo (`token_name`,`token_order_id`,`token_time`)\n"
				+ " VALUES('"
				+ token_name
				+ "', '"
				+ order_id
				+ "','"
				+ transaction_time + "');";
		int row = 0;
		System.out.println(query);
		try {
			Connection con = connection.Connect.getConnection();
			PreparedStatement stmt = (PreparedStatement) con
					.prepareStatement(query);
			row = stmt.executeUpdate();
			if (row > 0) {
				System.out.println("Data inserted");
			} else {
				System.out.print("Data Not Inserted");
			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}

		return row;
	}

	public static void main(String[] argv) throws RemoteException,
			ServiceException {
		CreateToken("03400201775");
		// insertTokendetails()
		// telenor.SendSms("+923400201775", "0306373sd9933");
	}
}
