package com.example.demo.rabbitmq.configuration;

import java.security.KeyManagementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class FactoryClass {

	@Autowired
	private ApplicationConfig rabbitMQConfigurationAware;

	@Bean(name = "rabbitConnectionFactoryobject")
	ConnectionFactory getConnectionFactory() throws KeyManagementException {
		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setVirtualHost(rabbitMQConfigurationAware.getRabbitmqVhost());
		connectionFactory.setUsername(rabbitMQConfigurationAware.getRabbitmqUserName());
		connectionFactory.setPassword(rabbitMQConfigurationAware.getRabbitmqPassword());
		connectionFactory.setAutomaticRecoveryEnabled(true);
		return connectionFactory;
	}

}