package bal;

import java.io.IOException;


import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.json.JSONException;

public class runjobafterfivemintus implements Runnable {
	
	final static Logger logger = Logger.getLogger(runjobafterfivemintus.class);

    @Override
    public void run() {
    	System.out.println("runjobafterfivemintus.run()");
        try {
			CallingXML.RecieveSMSList();
			//telenor.RecieveSms();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
    }

}