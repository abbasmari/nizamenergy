package bal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class update_finance implements Runnable {
	
	final static Logger logger = Logger.getLogger(update_finance.class);

	@Override
	public void run() {
		try {

			LoanPaymentBAL.update_fo_defaulter_loans();

			LoanPaymentBAL.UpdateFoDateCrossOneMonth();
			LoanPaymentBAL.UpdateVLEDateCrossOneMonth();

			LoanPaymentBAL.update_fo_defaulter_commission();
			LoanPaymentBAL.update_vle_defaulter_commission();

		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}
}