package SOAP;
import javax.xml.ws.Endpoint;
import SOAP.HelloWorldImpl;

public class HelloWorldPublisher {
	public static void main(String[] args) {
		   Endpoint.publish("http://localhost:1238/ws/hello5", new HelloWorldImpl());
	    }
}
