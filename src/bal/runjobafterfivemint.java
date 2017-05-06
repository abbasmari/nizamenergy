package bal;

import java.sql.SQLException;

import org.apache.log4j.Logger;

public class runjobafterfivemint implements Runnable {

	final static Logger logger = Logger.getLogger(runjobafterfivemint.class);
	@Override
	public void run() {
//		System.out.println("runjobafterfivemint.run()");
		try {
			CallingXML.RecieveSMSList();
//			 telenor.RecieveSms();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
	}

}