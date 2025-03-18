package com.example.demo.route;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constant.RabbitMQQueueConstants;
import com.example.demo.rabbitmq.configuration.ApplicationConfig;

@Component
public class Producer {
	private static final String EXCEPTION_WHILE_STOPPING_PRODUCER_TEMPLATE = "exception while stopping producer template";

	private static final String EXCEPTION_WHILE_STARTING_PRODUCER_TEMPLATE = "exception while starting producer template";

	private static final Logger LOG = LoggerFactory.getLogger(Producer.class);

	@Autowired
	ApplicationConfig rabbitMQConfigurationAware;

	@Autowired
	ProducerTemplate producerTemplate;

	@Autowired
	CamelContext context;

	@PostConstruct
	private void init() {
		try {
			producerTemplate.start();
			producerTemplate.setDefaultEndpointUri(
					RabbitMQQueueConstants.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange() + RabbitMQQueueConstants.Q
							+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
							+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
							+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
							+ rabbitMQConfigurationAware.getChannelPoolMaxWait());
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

	public void sendMessage(String body, String queueName, int priority) {
		if (queueName != null && !"null".equalsIgnoreCase(queueName)) {

			producerTemplate.sendBody(RabbitMQQueueConstants.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange()
					+ RabbitMQQueueConstants.Q + RabbitMQQueueConstants.QUEUE + "=" + queueName + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.ROUTING_KEY + "=" + queueName + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE
					+ "=" + rabbitMQConfigurationAware.getRabbitmqAutoDelete() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.QUEUE_ARGS_CONFIG + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ADDRESSES + "="
					+ rabbitMQConfigurationAware.getRabbitmqHost() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxWait(), body);
		}
	}

	public void routeMessage(String body, String queuename, String exchangeType, boolean autodelete,
			String exchangeName, String routingKey) {
		if (queuename != null && !"null".equalsIgnoreCase(queuename)) {

			producerTemplate.sendBody(RabbitMQQueueConstants.RABBITMQ_URL + exchangeName + RabbitMQQueueConstants.Q
					+ RabbitMQQueueConstants.QUEUE + "=" + queuename + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY + "="
					+ routingKey + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE + "=" + autodelete
					+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE_ARGS_CONFIG + "&exchangeType=" + exchangeType
					+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ADDRESSES + "=" + rabbitMQConfigurationAware.getRabbitmqHost()
					+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxWait(), body);
		}
	}

	public void routeMessageToTopic(String body, String exchangeType, boolean autodelete, String exchangeName,
			String routingKey, String queue) {

		producerTemplate.sendBody(RabbitMQQueueConstants.RABBITMQ_URL + exchangeName + RabbitMQQueueConstants.Q
				+ RabbitMQQueueConstants.ROUTING_KEY + "=" + routingKey + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE + "="
				+ autodelete + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE_ARGS_CONFIG + "&exchangeType=" + exchangeType
				+ "&queue=" + queue + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ADDRESSES + "="
				+ rabbitMQConfigurationAware.getRabbitmqHost() + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
				+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
				+ rabbitMQConfigurationAware.getChannelPoolMaxWait(), body);
	}

	public void routeMessage(String body, String queuename, String exchangeType, boolean autodelete,
			String exchangeName, String routingKey, int priority) {
		if (queuename != null && !"null".equalsIgnoreCase(queuename)) {
			try {
				producerTemplate.sendBody(RabbitMQQueueConstants.RABBITMQ_URL + exchangeName + RabbitMQQueueConstants.Q
						+ RabbitMQQueueConstants.QUEUE + "=" + queuename + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY + "="
						+ routingKey + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE + "=" + autodelete
						+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE_ARGS_CONFIG + "&exchangeType=" + exchangeType
						+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION + RabbitMQQueueConstants.AND
						+ RabbitMQQueueConstants.ADDRESSES + "=" + rabbitMQConfigurationAware.getRabbitmqHost()
						+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
						+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
						+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
						+ rabbitMQConfigurationAware.getChannelPoolMaxWait(), body);
			} catch (CamelExecutionException ex) {
				LOG.error("CamelExecutionException while sending message to {} - {}", queuename);
				LOG.error(ex.getMessage());
//				throw new IgnioRuntimeException("CamelExecutionException while sending message to queue",ex);
			}
		}
	}

	public void routeMessage(String body, String queuename, String exchangeType, boolean autodelete,
			String exchangeName, String routingKey, Map<String, Object> headers) {
		if (queuename != null && !"null".equalsIgnoreCase(queuename)) {

			producerTemplate.sendBodyAndHeaders(RabbitMQQueueConstants.RABBITMQ_URL + exchangeName + RabbitMQQueueConstants.Q
					+ "exchangeType=" + exchangeType + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE + "="
					+ autodelete + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY + "=" + routingKey
					+ RabbitMQQueueConstants.AND + RabbitMQQueueConstants.QUEUE + "=" + queuename + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.QUEUE_ARGS_CONFIG + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ADDRESSES + "="
					+ rabbitMQConfigurationAware.getRabbitmqHost() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_SIZE + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxSize() + RabbitMQQueueConstants.AND
					+ RabbitMQQueueConstants.CHANNEL_POOL_MAX_WAIT + RabbitMQQueueConstants.EQUALS_CHAR
					+ rabbitMQConfigurationAware.getChannelPoolMaxWait(), body, headers);
		}
	}
}
