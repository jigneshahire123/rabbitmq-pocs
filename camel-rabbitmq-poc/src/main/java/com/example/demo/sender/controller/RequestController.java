package com.example.demo.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.route.Producer;

@RestController
public class RequestController {
	
	@Autowired
	private Producer messageSender;
	
	@GetMapping("/send-message")
	public void sendMessage(){
		messageSender.routeMessage("test-message",  "message.processor.test", "direct", false, "amq.direct", "message.processor.test");
	}
}
 