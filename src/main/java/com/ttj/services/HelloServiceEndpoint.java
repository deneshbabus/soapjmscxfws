package com.ttj.services;




import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.ttj.services.hello_service.SayHelloRequest;

@WebService(serviceName = "hello-service")
public class HelloServiceEndpoint {
    @WebMethod(action="sayHello", operationName = "sayHelloRequest")
    @WebResult(name = "message")
    public String sayHello(SayHelloRequest request){
    	
    	System.out.println("soap service invoked to get the response from Mainframe");
    	
        return "Response from MF>>>>>>> Hello "+request.getUserName()+"!!!";
    }
}