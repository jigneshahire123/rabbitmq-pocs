package com.example.demo.idempotent.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "MyJpaMessageProcessor")
public class MessageProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String message = exchange.getIn().getBody(String.class);
		System.out.println(message);
	}
}
