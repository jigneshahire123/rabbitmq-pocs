package com.example.demo.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.springrabbit.SpringRabbitMQEndpoint;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constant.RabbitConstant;
import com.example.demo.idempotent.processor.MyJpaMessageProcessor;
import com.example.demo.rabbitmq.configuration.RabbitMQConfig;

@Component
public class Consumer extends RouteBuilder {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	RabbitMQConfig rabbitMQConfigurationAware;

	@Autowired
	MyJpaMessageProcessor jpaMessageProcessor;

	@Autowired
	CamelContext context;

	@Override
	public void configure() throws Exception {
		onException(Exception.class).maximumRedeliveries(0).handled(true).process("exceptionHandler");
		onMessage("ABC-test", 1, 1).process(jpaMessageProcessor).end();
	}

	public RouteDefinition onMessage(String queueName, int consumerCount, int threadPoolSize) throws Exception {

		String endpointUriForDelayedExchange = RabbitConstant.RABBITMQ_URL
				+ rabbitMQConfigurationAware.getRabbitmqExchange() + RabbitConstant.Q
				+ getRabbitQParams(queueName, threadPoolSize) + RabbitConstant.AND + RabbitConstant.ROUTING_KEY
				+ RabbitConstant.EQUALS_CHAR + queueName + RabbitConstant.AND + RabbitConstant.AUTO_DELETE
				+ RabbitConstant.EQUALS_CHAR + false + RabbitConstant.AND + "args.exchange.autoDelete=false&"
				+ RabbitConstant.CONSUMERS + RabbitConstant.EQUALS_CHAR + consumerCount + RabbitConstant.AND
				+ RabbitConstant.QUEUE_ARGS_CONFIG
				+"&args.queue.x-consumer-timeout=undefined";

		SpringRabbitMQEndpoint rabbitmqEndpointDelayed = context.getEndpoint(endpointUriForDelayedExchange, SpringRabbitMQEndpoint.class);
//			rabbitmqEndpointDelayed.getArgs().put("queue.x-consumer-timeout", 180000);
//			rabbitmqEndpointDelayed.getArgs().put("queue.x-consumer-timeout", "undefined");
//			rabbitmqEndpointDelayed.getArgs().replace("queue.x-consumer-timeout", 1900000);
			
		return from(rabbitmqEndpointDelayed).routeId(queueName);
	}

	public RouteDefinition onMessage(String queueName, String routingKey, String exchangeName, String exchangeType,
			int consumerCount, boolean isAutodelete) throws Exception {

		return from(RabbitConstant.RABBITMQ_URL + exchangeName + RabbitConstant.Q
				+ getRabbitQParams(queueName, consumerCount) + RabbitConstant.AND + RabbitConstant.ROUTING_KEY
				+ RabbitConstant.EQUALS_CHAR + routingKey + RabbitConstant.AND + RabbitConstant.AUTO_DELETE
				+ RabbitConstant.EQUALS_CHAR + isAutodelete + RabbitConstant.AND + RabbitConstant.CONSUMERS
				+ RabbitConstant.EQUALS_CHAR + consumerCount + "&exchangeType=" + exchangeType + RabbitConstant.AND
				+ "args.exchange.autoDelete=true&" + RabbitConstant.QUEUE_ARGS_CONFIG).routeId(queueName);
	}

	public RouteDefinition onWorkItem(String queueName, int consumerCount) {

		return from(RabbitConstant.RABBITMQ_URL + rabbitMQConfigurationAware.getRabbitmqExchange() + RabbitConstant.Q
				+ getRabbitQParams(queueName, consumerCount) + RabbitConstant.AND + RabbitConstant.ROUTING_KEY
				+ RabbitConstant.EQUALS_CHAR + queueName + RabbitConstant.AND + RabbitConstant.AUTO_DELETE
				+ RabbitConstant.EQUALS_CHAR + rabbitMQConfigurationAware.getRabbitmqAutoDelete() + RabbitConstant.AND
				+ RabbitConstant.CONSUMERS + RabbitConstant.EQUALS_CHAR + consumerCount + RabbitConstant.AND
				+ RabbitConstant.QUEUE_ARGS_CONFIG).process(jpaMessageProcessor).routeId(queueName);
	}

	private String getRabbitQParams(String queueName, int threadPoolSize) {
		StringBuilder sb = new StringBuilder();

		prepareParams(queueName, sb, threadPoolSize);

		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.PREFETCH_COUNT);
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append(1);

		return sb.toString();
	}

	private String getRabbitQParams(String queueName, int prefetchCount, int threadPoolSize) {
		StringBuilder sb = new StringBuilder();

		prepareParams(queueName, sb, threadPoolSize);

		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.PREFETCH_COUNT);
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append(prefetchCount);

		return sb.toString();
	}

	private void prepareParams(String queueName, StringBuilder sb, int threadPoolSize) {

		sb.append("acknowledgeMode");
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append("AUTO");

		sb.append(RabbitConstant.AND);
		sb.append("maximumRetryAttempts");
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append("0");

		/* Assigning queueName to queues */
		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.QUEUES);
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append(queueName);

		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.QUEUE_CONNECTION_FACTORY_OPTION);

		/* Setting args.queue.durable = true */
		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.DURABLE);
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append(true);

		/* Setting autoDeclare = true */
		sb.append(RabbitConstant.AND);
		sb.append(RabbitConstant.AUTODECLARE);
		sb.append(RabbitConstant.EQUALS_CHAR);
		sb.append(true);

	}
}
