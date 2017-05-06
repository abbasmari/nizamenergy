/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.json.JSONException;

import connection.Connect;

/**
 *
 * @author waseem
 */
public class NewClass implements ServletContextListener{
    
	final static Logger logger = Logger.getLogger(NewClass.class);
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	
         System.out.println("ServletContextListener start");
          
        Calendar date = Calendar.getInstance();
        //Setting the date from when you want to activate scheduling
        Date newdate=new Date();
        date.setTime(newdate);
         Calendar cal = Calendar.getInstance();
        Timer time = new Timer(); // Instantiate Timer Object
		
        try {
			//CallingXML.RecieveSMS();
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}
                cal.setTime(newdate);
        //execute every 3 seconds
        time.schedule(new timeforupdate(), cal.getTime(),60*60*24*100);
    
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener destroyed");
       //To change body of generated methods, choose Tools | Templates.
    }
}
