package bal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class UpdateSubscriptions implements ServletContextListener {

	@Override

	public void contextInitialized(ServletContextEvent event) {
//		System.out.println("UpdateSubscriptions.contextInitialized()");
//		ScheduledExecutorService as = Executors.newSingleThreadScheduledExecutor();
//		as.scheduleAtFixedRate(new runjobafterfivemint(), 0, 1, TimeUnit.MINUTES);
//		as.scheduleAtFixedRate(new update_finance(), 0, 1, TimeUnit.DAYS);
//		System.err.println("thread of Commission");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// scheduler.shutdownNow();
	}

}