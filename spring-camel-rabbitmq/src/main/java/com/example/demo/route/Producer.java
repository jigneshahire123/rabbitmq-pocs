package com.example.demo.route;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constant.RabbitConstant;
import com.example.demo.rabbitmq.configuration.RabbitMQConfig;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class Producer {

	private static final String EXCEPTION_WHILE_STOPPING_PRODUCER_TEMPLATE = "exception while stopping producer template";

	private static final String EXCEPTION_WHILE_STARTING_PRODUCER_TEMPLATE = "exception while starting producer template";

	private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

	@Autowired
	RabbitMQConfig rabbitMQConfigurationAware;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	CamelContext context;

	@PostConstruct
	private void init() {
		try {
			producerTemplate.start();
			producerTemplate.setDefaultEndpointUri(RabbitConstant.RABBITMQ_URL
					+ rabbitMQConfigurationAware.getRabbitmqExchange()/* +"?autoDeclare=true" */);
		} catch (Exception e) {
			LOG.error(EXCEPTION_WHILE_STARTING_PRODUCER_TEMPLATE, e);
		}
	}

	@PreDestroy
	private void destroy() {
		try {
			producerTemplate.stop();
		} catch (Exception e) {
			LOG.error(EXCEPTION_WHILE_STOPPING_PRODUCER_TEMPLATE, e);
		}
	}

	public void routeMessage(String body, String queuename, String exchangeType, boolean autodelete,
			String exchangeName, String routingKey, int priority) {
		if (queuename != null && !"null".equalsIgnoreCase(queuename)) {
			producerTemplate.sendBody(RabbitConstant.RABBITMQ_URL + exchangeName + RabbitConstant.Q
					+ RabbitConstant.QUEUES + "=" + queuename + RabbitConstant.AND + RabbitConstant.ROUTING_KEY + "="
					+ routingKey + RabbitConstant.AND + RabbitConstant.AUTO_DELETE + "=" + autodelete
					+ "&args.exchange.autoDelete=false" + RabbitConstant.AND + RabbitConstant.QUEUE_ARGS_CONFIG
					+ "&exchangeType=" + exchangeType + RabbitConstant.AND
					+ RabbitConstant.QUEUE_CONNECTION_FACTORY_OPTION, body);
		}
	}

	public void sendMessage(String body, String queueName) {
		if (queueName != null && !"null".equalsIgnoreCase(queueName)) {
			producerTemplate.sendBody(RabbitConstant.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange()
					+ RabbitConstant.Q + RabbitConstant.QUEUES + "=" + queueName + RabbitConstant.AND
					+ RabbitConstant.ROUTING_KEY + "=" + queueName + RabbitConstant.AND + RabbitConstant.AUTO_DELETE
					+ "=" + rabbitMQConfigurationAware.getRabbitmqAutoDelete() + RabbitConstant.AND
					+ RabbitConstant.QUEUE_ARGS_CONFIG + RabbitConstant.AND
					+ RabbitConstant.QUEUE_CONNECTION_FACTORY_OPTION, body);
		}
	}

	public void sendMessage(String body, String queueName, int priority) {
		if (queueName != null && !"null".equalsIgnoreCase(queueName)) {
			producerTemplate.sendBody(RabbitConstant.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange()
					+ RabbitConstant.Q + RabbitConstant.QUEUES + "=" + queueName + RabbitConstant.AND
					+ RabbitConstant.ROUTING_KEY + "=" + queueName + RabbitConstant.AND + RabbitConstant.AUTO_DELETE
					+ "=" + rabbitMQConfigurationAware.getRabbitmqAutoDelete() + RabbitConstant.AND
					+ RabbitConstant.QUEUE_ARGS_CONFIG + RabbitConstant.AND
					+ RabbitConstant.QUEUE_CONNECTION_FACTORY_OPTION, body);
		}
	}
}
