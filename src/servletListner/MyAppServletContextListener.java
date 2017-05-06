package servletListner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import schedule.AlarmsAlertsToFo;
import schedule.CommissionModule;
import schedule.TriggerModule;

@WebListener
public class MyAppServletContextListener implements ServletContextListener {

	TriggerModule triggerModule;
	AlarmsAlertsToFo alarms;
	CommissionModule commModule;

	public MyAppServletContextListener() {
		// TODO Auto-generated constructor stub
		triggerModule = new TriggerModule();
		alarms = new AlarmsAlertsToFo();
		commModule = new CommissionModule();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		triggerModule.start();
		// alarms.start();
		// commModule.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		triggerModule.stop();
		alarms.stop();
		commModule.stop();
	}

}
