package bal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.json.JSONException;


import connection.Connect;
 
public class AfterFiveMinutes {
 
	
    public static void main(String args[]){
        //Displaying current time
        System.out.println("Time now is -> " + new Date());
 
        //Creating timer which executes once after five seconds
      //  Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TaskExampleRepeating(), 10000, 5000);
    }
}
 
class TaskExampleRepeating extends TimerTask{
	final static Logger logger = Logger.getLogger(Connect.class);
    //This task will repeat every five seconds
    CallingXML obj=new CallingXML();
	public void run(){
		System.out.println("TaskExampleRepeating.run()");
		try {
//			System.out.println(obj.RecieveSMS());
//		} catch (IOException | JSONException e) {
//			logger.error("",e);
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("",e);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.error("",e);
		}
        System.out.println(new Date() + " ->" + " I will repeat every 5 seconds.");
    }
}