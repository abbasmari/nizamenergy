package SOAP;

import java.util.ArrayList;
import javax.jws.WebService;
import bean.OccupationBean;
import bal.CustomerBAL;

@WebService(endpointInterface = "SOAP.HelloWorld")
public class HelloWorldImpl implements HelloWorld{

	@Override
	public String getHelloWorldAsString(String name) {
		return "Hello from waseem computer dear = "+name;
	}	

	@Override
	public String getHelloWorldDB() {
	//	ArrayList<InventoryBean> list = InventoryBAL.getAppliances();
		ArrayList<OccupationBean> list= CustomerBAL.getOccupations();
		                
		                
		                String str=list.toString();
		return str;
	}
	
}
