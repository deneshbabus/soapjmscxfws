package com.ttj.services.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ttj.services.HelloServiceEndpoint;



@Configuration
public class SoapEndpointsConfig {

    @Bean
    public Endpoint endpoint(SpringBus bus) {
        EndpointImpl endpoint = new EndpointImpl(bus, new HelloServiceEndpoint());
        endpoint.publish("/helloService");
        return endpoint;
    }
}
