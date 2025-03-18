package com.example.demo.rabbitmq.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${queue.url:<server-ip>}")
	private String rabbitmqHost;

	@Value("${queue.autodelete:false}")
	private String rabbitmqAutoDelete;
	
	@Value("${tenant.id:cpenv}")
	private String rabbitmqVhost;
	
	@Value("${queue.password:admin}")
	private String rabbitmqPassword;
	
	@Value("${queue.username:admin}")
	private String rabbitmqUserName;
	
	@Value("${queue.exchange:amq.direct}")
	private String rabbitmqExchange;
	
	@Value("${queue.ssl.enabled:false}")
	private String sslEnabled;
	
	@Value("${queue.management.port:15672}")
	private String managementPort;

	@Value("${queue.producer.channelpool.maxsize:20}")
	private int channelPoolMaxSize;
	
	@Value("${queue.producer.channelpool.maxwait:10000}")
	private int channelPoolMaxWait;
	
	public String getManagementPort() {
		return managementPort;
	}

	public void setManagementPort(String managementPort) {
		this.managementPort = managementPort;
	}
	public String getRabbitmqAutoDelete() {
		return rabbitmqAutoDelete;
	}

	public void setRabbitmqAutoDelete(String rabbitmqAutoDelete) {
		this.rabbitmqAutoDelete = rabbitmqAutoDelete;
	}
	
	public String getRabbitmqUserName() {
		return rabbitmqUserName;
	}

	public void setRabbitmqUserName(String rabbitmqUserName) {
		this.rabbitmqUserName = rabbitmqUserName;
	}

	public String getRabbitmqPassword() {
		return rabbitmqPassword;
	}

	public void setRabbitmqPassword(String rabbitmqPassword) {
		this.rabbitmqPassword = rabbitmqPassword;
	}

	public String getRabbitmqHost() {
		return rabbitmqHost;
	}

	public void setRabbitmqHost(String rabbitmqHost) {
		this.rabbitmqHost = rabbitmqHost;
	}

	public String getRabbitmqExchange() {
		return rabbitmqExchange;
	}

	public void setRabbitmqExchange(String rabbitmqExchange) {
		this.rabbitmqExchange = rabbitmqExchange;
	}

	public String getRabbitmqVhost() {
		return rabbitmqVhost;
	}

	public void setRabbitmqVhost(String rabbitmqVhost) {
		this.rabbitmqVhost = rabbitmqVhost;
	}
	
	public int getChannelPoolMaxSize() {
		return channelPoolMaxSize;
	}

	public void setChannelPoolMaxSize(int channelPoolMaxSize) {
		this.channelPoolMaxSize = channelPoolMaxSize;
	}

	public int getChannelPoolMaxWait() {
		return channelPoolMaxWait;
	}

	public void setChannelPoolMaxWait(int channelPoolMaxWait) {
		this.channelPoolMaxWait = channelPoolMaxWait;
	}
}
