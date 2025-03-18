package com.example.demo.constant;

public class RabbitConstant {

	private RabbitConstant() {}
	public static final String WORKITEMBROADCASTEXCHANGE = "workitem.fanout"; 
	public static final String AUTO_DELETE = "args.queue.autoDelete";
	public static final String ROUTING_KEY = "routingKey";
	public static final String DIRECTEXCHANGETYPE = "direct";
	public static final String FANOUTEXCHANGETYPE = "fanout";
	public static final String QUEUE = "queue";

	public static final String QUEUES = "queues";
	public static final String SSLPROTOCOL = "sslProtocol";
	public static final String Q = "?";
	public static final String AND = "&";
	public static final String EQUALS_CHAR = "=";
	public static final String PROCESS_METHOD = "process";
	public static final String AMQDIRECTEXCHANGE = "amq.direct"; 
	
	public static final String QUEUE_ARGS_CONFIG = "arg.queue.x-max-priority=3";
	public static final String QUEUE_SAC_ARGS_CONFIG = "arg.queue.x-max-priority=3&arg.queue.x-single-active-consumer=true";
	public static final String QUEUE_CONNECTION_FACTORY_OPTION = "connectionFactory=#rabbitConnectionFactoryobject"; 
	public static final String RABBITMQ_URL = "spring-rabbitmq://";
	 
	public static final String DELIVERY_MODE="CamelSpringRabbitmqDeliveryMode"; 
	public static final String PRIORITY_HEADER="CamelSpringRabbitmqPriority";
	 
	public static final String USER_NAME = "username";
	public static final String PASS_KEY = "password";
	public static final String CONSUMERS = "concurrentConsumers";
	public static final String WORKITEM_ID="WORKITEM_ID";
	public static final String ROUTER="router-";
	public static final String VHOST = "vhost";
	public static final String PARENT_SPAN_ID = "PARENT_SPAN_ID";

	public static final String USER_ID = "USER_ID";

	public static final String USER2 = "USER";
	
	public static final String USER_CONTEXT = "USER_CONTEXT";

	public static final String SPAN_ID = "SPAN_ID";

	public static final String INTERACTION_ID = "INTERACTION_ID";
	
	public static final String TRANSACTION_ID ="TRANSACTION_ID";
	
	
	public static final String PREFETCH_ENABLED = "prefetchEnabled";
	
	public static final String PREFETCH_COUNT = "prefetchCount";
	
	public static final String PREFETCH_GLOBAL = "prefetchGlobal";
	public static final String ADDRESSES = "addresses";
	
	public static final String AUTOMATICRECOVERYENABLED = "automaticRecoveryEnabled";
	
	public static final String TRUE = "true";
	
	public static final String IGNIO_EXCHANGE_DIRECTDELAYED = "ignio.exchange.directdelayed";
	
	public static final String DELAY_HEADER = "x-delay"; 
	
	public static final String AUTODECLARE = "autoDeclare";
	public static final String DURABLE = "args.queue.durable";
}
