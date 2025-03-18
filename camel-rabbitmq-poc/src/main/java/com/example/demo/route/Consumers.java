package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constant.RabbitMQQueueConstants;
import com.example.demo.idempotent.processor.MessageProcessor;
import com.example.demo.rabbitmq.configuration.ApplicationConfig;

@Component
public class Consumers extends RouteBuilder {

//	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(RouteBuilderBase.class);

	@Autowired
	ApplicationConfig rabbitMQConfigurationAware;

	@Autowired
	MessageProcessor jpaMessageProcessor;

	@Override
	public void configure() throws Exception {
		onMessage("message.processor.test",10,10).process(jpaMessageProcessor).end();
	}

	public RouteDefinition onMessage(String queueName, int consumerCount) {

		return from(RabbitMQQueueConstants.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange() + RabbitMQQueueConstants.Q
				+ getRabbitQParams(queueName, consumerCount) + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY
				+ RabbitMQQueueConstants.EQUALS_CHAR + queueName + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE
				+ RabbitMQQueueConstants.EQUALS_CHAR + rabbitMQConfigurationAware.getRabbitmqAutoDelete() + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.CONSUMERS + RabbitMQQueueConstants.EQUALS_CHAR + consumerCount + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.QUEUE_ARGS_CONFIG +"&threadPoolSize=10").routeId(queueName);
	}

	public RouteDefinition onMessage(String queueName, int consumerCount, int threadPoolSize) {
		return from(RabbitMQQueueConstants.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange() + RabbitMQQueueConstants.Q
				+ getRabbitQParams(queueName, threadPoolSize) + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY
				+ RabbitMQQueueConstants.EQUALS_CHAR + queueName + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE
				+ RabbitMQQueueConstants.EQUALS_CHAR
				+ false/* rabbitMQConfigurationAware.getRabbitmqAutoDelete() */+"&reQueue=false" + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.CONSUMERS + RabbitMQQueueConstants.EQUALS_CHAR + consumerCount + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.QUEUE_ARGS_CONFIG).routeId(queueName);
	}

	public RouteDefinition onMessage(String queueName, String routingKey, String exchangeName, String exchangeType,
			int consumerCount, boolean isAutodelete) {

		return from(RabbitMQQueueConstants.RABBITMQ_URL + exchangeName + RabbitMQQueueConstants.Q
				+ getRabbitQParams(queueName, consumerCount) + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.ROUTING_KEY
				+ RabbitMQQueueConstants.EQUALS_CHAR + routingKey + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.AUTO_DELETE
				+ RabbitMQQueueConstants.EQUALS_CHAR + isAutodelete + RabbitMQQueueConstants.AND + RabbitMQQueueConstants.CONSUMERS
				+ RabbitMQQueueConstants.EQUALS_CHAR + consumerCount + "&exchangeType=" + exchangeType + RabbitMQQueueConstants.AND
				+ RabbitMQQueueConstants.QUEUE_ARGS_CONFIG).routeId(queueName);
	}

	private String getRabbitQParams(String queueName, int threadPoolSize) {
		StringBuilder sb = new StringBuilder();

		prepareParams(queueName, sb, threadPoolSize);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.PREFETCH_COUNT);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(1);

		return sb.toString();
	}

	private String getRabbitQParams(String queueName, int prefetchCount, int threadPoolSize) {
		StringBuilder sb = new StringBuilder();

		prepareParams(queueName, sb, threadPoolSize);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.PREFETCH_COUNT);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(prefetchCount);

		return sb.toString();
	}

	private void prepareParams(String queueName, StringBuilder sb, int threadPoolSize) {
		sb.append(RabbitMQQueueConstants.AUTO_ACT);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(Boolean.FALSE);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.PREFETCH_ENABLED);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(Boolean.TRUE);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.PREFETCH_GLOBAL);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(Boolean.FALSE);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.QUEUE);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(queueName);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.ADDRESSES);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(rabbitMQConfigurationAware.getRabbitmqHost());

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.THREADPOOLSIZE);
		sb.append(RabbitMQQueueConstants.EQUALS_CHAR);
		sb.append(threadPoolSize);

		sb.append(RabbitMQQueueConstants.AND);
		sb.append(RabbitMQQueueConstants.QUEUE_CONNECTION_FACTORY_OPTION);

	}

}
