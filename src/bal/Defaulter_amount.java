package bal;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Defaulter_amount implements Runnable{
	
	final static Logger logger = Logger.getLogger(Defaulter_amount.class);

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("RUNNING******************DEFAULTER AMOUNT***************");
			LoanPaymentBAL.update_fo_defaulter_loans();
			LoanPaymentBAL.update_fo_defaulter_commission();
			LoanPaymentBAL.update_vle_defaulter_commission();
			LoanPaymentBAL.update_onhold_commission();
			
		} catch (SQLException e) {
			logger.error("", e);
			e.printStackTrace();
		}
		
	}

	
}
