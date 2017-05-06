package schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import bal.LoanPaymentBAL;
import bal.Payment_loanNewBAL;
import bal.update_finance;

public class CommissionModule extends Thread {

	private final static Logger logger = Logger.getLogger(CommissionModule.class);

	public void run() {
		try {
			while (true) {
				// System.err.println("Running Update Commission Table...");
				Date today = new Date();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(today);

				calendar.add(Calendar.MONTH, 0);
				calendar.set(Calendar.DAY_OF_MONTH, 0);
				calendar.set(Calendar.DATE, 1);

				calendar.set(Calendar.HOUR_OF_DAY, 00);
				calendar.set(Calendar.MINUTE, 7);
				// calendar.set(Calendar.SECOND, 59);
				// calendar.set(Calendar.MILLISECOND, 999);

				Date lastDayOfMonth = (Date) calendar.getTime();

				DateFormat df = new SimpleDateFormat("HH:mm");
				Date date = new Date();
				if (df.format(date).equals("00:03") || df.format(date).equals("00:04")) {
					new update_finance().run();
					System.out.println("True.. Daily wise  ");
				} else {
					// System.out.println("False.. Daily wise ");

				}

				if (isSameDay(today, lastDayOfMonth)) {
					LoanPaymentBAL.updateCurrentMonthCommission();
					System.err.println("True.. Monthly wise ");
				} else {
					// System.err.println("False.. Monthly wise ");
				}
				Thread.sleep(50000);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	public static boolean isSameDay(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		boolean sameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
		boolean sameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
		boolean sameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
		boolean sameHour = calendar1.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY);
		boolean sameMinute = calendar1.get(Calendar.MINUTE) == calendar2.get(Calendar.MINUTE);
		return (sameMinute && sameHour && sameDay && sameMonth && sameYear);

	}
}
