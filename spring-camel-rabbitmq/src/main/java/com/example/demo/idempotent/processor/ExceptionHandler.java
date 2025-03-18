package com.example.demo.idempotent.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler implements Processor {
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
		LOG.error("Exception in the route", exception);

	}

}
