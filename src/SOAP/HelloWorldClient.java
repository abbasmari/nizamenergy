package SOAP;

import NET.webserviceX.www.GlobalWeatherLocator;
import NET.webserviceX.www.GlobalWeatherSoap;

public class HelloWorldClient {
	static GlobalWeatherLocator service;
	public static void main(String[] args) throws Exception {
		   
		//URL url = new URL("http://localhost:1238/ws/hello5?wsdl");
		
	        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
	       // QName qname = new QName("http://SOAP/", "HelloWorldImplService");
	       
	        
	      

	        //HelloWorld hello = service.getPort(HelloWorld.class);

	        //GlobalWeatherSoap hello = service.getPort(GlobalWeatherSoap.class);
		
		service=new GlobalWeatherLocator();
		
		GlobalWeatherSoap gw=service.getGlobalWeatherSoap();
		System.out.println(gw.getWeather("karachi", "pakistan"));

	    }
	
}
