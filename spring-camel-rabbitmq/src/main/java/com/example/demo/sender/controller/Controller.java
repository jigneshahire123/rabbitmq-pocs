package com.example.demo.sender.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.route.Producer;

@RestController
public class Controller {

	@Autowired
	private Producer messageSender;
	
	@GetMapping("/time")
	public void conversion() {
		
		Timestamp object1 = java.sql.Timestamp.valueOf(LocalDateTime.now());
//		long timeConverasion = TimestampConversionUtils.getTimeConverasion(object1);
		System.out.println(TimestampConversionUtils.getTimeConverasion(object1));
		
	}
	
	@GetMapping("/size")
	public void getThreadCount() {
		System.out.println("##############################################");
//		Integer poolSize = context.getExecutorServiceManager().getDefaultThreadPoolProfile().getPoolSize();
//		System.out.println("##############	" + poolSize + "   ###############");
		System.out.println("Number of threads " + Thread.activeCount());
//		System.out.println("Current Thread Group - " + Thread.currentThread().getThreadGroup().getName());
		
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		threadPoolExecutor.getActiveCount();
		System.out.println("Number of threads " + threadPoolExecutor.getActiveCount());
		System.out.println("##############################################");
	}
	
	@GetMapping("/single")
	public void singleMessage() {
		messageSender.sendMessage("u", "ABC-test");
		
	}

}
