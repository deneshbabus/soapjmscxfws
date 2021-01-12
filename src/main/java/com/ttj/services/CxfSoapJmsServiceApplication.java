package com.ttj.services;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@SpringBootApplication
public class CxfSoapJmsServiceApplication {
	@Autowired
	private JmsTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(CxfSoapJmsServiceApplication.class, args);
	}
	
	
	  
	  @EventListener(ApplicationReadyEvent.class) public void
	  applicationReadyEvent()throws JMSException { String msg =
	  "<soapenv:Envelope \n" +
	  "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
	  "xmlns:ser=\"http://services.ttj.com/\"\n" +
	  "xmlns:hs=\"http://services.ttj.com/hello-service\">\n" +
	  "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" +
	  "      <ser:sayHelloRequest>\n" + "         <arg0>\n" +
	  "            <hs:userName>Black swan</hs:userName>\n" + "         </arg0>\n"
	  + "      </ser:sayHelloRequest>\n" + "   </soapenv:Body>\n" +
	  "</soapenv:Envelope>";
	  
	  Session session = template.getConnectionFactory().createConnection()
	  .createSession(false, Session.AUTO_ACKNOWLEDGE);
	  
	  TextMessage message = session.createTextMessage(msg);
	  message.setJMSReplyTo(new ActiveMQQueue("poc.response.queue"));
	  message.setJMSCorrelationID(message.getJMSMessageID());
	  
	  template.convertAndSend("poc.request.queue", message);
	  
	  System.out.println("Jms Message sent"); }
	  
	  
	  
	  
	  @JmsListener(destination = "poc.response.queue") public void
	  receiveMessage(final Message message) throws JMSException {
	  
	  listenerReading();
	  
	  
	  String messageData = null; System.out.println("Received message-> " +
	  message); String response = null; if(message instanceof TextMessage) {
	  TextMessage textMessage = (TextMessage)message; messageData =
	  textMessage.getText(); System.out.println("Message data-> "+messageData);
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  } }
	  
	  private void listenerReading() {
	  
	  }
	 
    
    }