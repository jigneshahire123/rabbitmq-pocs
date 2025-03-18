package com.example.demo.idempotent.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "MyJpaMessageProcessor")
public class MyJpaMessageProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String workitemId = exchange.getIn().getBody(String.class);
		System.out.println("message recieved "+workitemId+" for thread "+Thread.currentThread().getName());
//		Thread.sleep(300000); 
//		int a = 6;
//		int c = a / 0;

//		long startTime = System.currentTimeMillis();
//		
//		System.out.println("start   "+startTime);
//		// Create a large array
//		int[] largeArray = new int[10000000];
//
//		// Initialize the array with values
//		for (int i = 0; i < largeArray.length; i++) {
//			largeArray[i] = i;
//		}
//		
//		// Simulate a time-consuming task by iterating over the array
//		
//		for (int i = 0; i < largeArray.length; i++) {
//			// Do some time-consuming operation
//			for (int j = 0; j < 100000; j++) {
//				// Simulate some work
//			}
//		}
//		long endTime = System.currentTimeMillis();
//
//		// Print the time taken
//		System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
//		System.out.println("end");
//		int a = 6;
////	int c = a / 0;
//		System.out.println(workitemId);

	}
}
