package com.example.demo.rabbitmq.configuration;

import java.security.KeyManagementException;
import java.util.concurrent.Executors;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryClass {

	@Autowired
	private RabbitMQConfig rabbitMQConfigurationAware;

	@Bean(name = "rabbitConnectionFactoryobject")
	org.springframework.amqp.rabbit.connection.ConnectionFactory getConnectionFactory() throws KeyManagementException {
		com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
		connectionFactory.setVirtualHost(rabbitMQConfigurationAware.getRabbitmqVhost());
		connectionFactory.setUsername(rabbitMQConfigurationAware.getRabbitmqUserName());
		connectionFactory.setPassword(rabbitMQConfigurationAware.getRabbitmqPassword());
		// adding addresses for creating connection
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
		cachingConnectionFactory.setAddresses(rabbitMQConfigurationAware.getRabbitmqHost()); 
		cachingConnectionFactory.setExecutor(Executors.newCachedThreadPool()); 
		cachingConnectionFactory.getRabbitConnectionFactory().setMaxInboundMessageBodySize(50003);
		return cachingConnectionFactory;
	}


}