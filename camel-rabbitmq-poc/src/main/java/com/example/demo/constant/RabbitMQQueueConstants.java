package com.example.demo.constant;

public class RabbitMQQueueConstants {
	private RabbitMQQueueConstants() {}
	public static final String AUTO_DELETE = "autoDelete";
	public static final String ROUTING_KEY = "routingKey";
	public static final String DIRECTEXCHANGETYPE = "direct";
	public static final String FANOUTEXCHANGETYPE = "fanout";
	public static final String QUEUE = "queue";
	public static final String SSLPROTOCOL = "sslProtocol";
	public static final String Q = "?";
	public static final String AND = "&";
	public static final String EQUALS_CHAR = "=";
	public static final String PROCESS_METHOD = "process";
	public static final String AMQDIRECTEXCHANGE = "amq.direct";
	
	public static final String QUEUE_ARGS_CONFIG = "arg.queue.x-max-priority=3";
	public static final String QUEUE_SAC_ARGS_CONFIG = "arg.queue.x-max-priority=3&arg.queue.x-single-active-consumer=true";
	public static final String QUEUE_CONNECTION_FACTORY_OPTION = "connectionFactory=#rabbitConnectionFactoryobject";
	
	public static final String RABBITMQ_URL = "rabbitmq://";
	public static final String PRIORITY_HEADER = "CamelRabbitmqPriority";
	public static final String DELIVERY_MODE = "CamelRabbitmqDeliveryMode";
	public static final String USER_NAME = "username";
	public static final String PASS_KEY = "password";
	public static final String CONSUMERS = "concurrentConsumers";
	public static final String ROUTER="router-";
	public static final String VHOST = "vhost";

	
	public static final String AUTO_ACT = "autoAck";
	
	public static final String PREFETCH_ENABLED = "prefetchEnabled";
//	
	public static final String PREFETCH_COUNT = "prefetchCount";
//	
	public static final String PREFETCH_GLOBAL = "prefetchGlobal";
	public static final String ADDRESSES = "addresses";
	
	
	public static final String TRUE = "true";
	
	
	
	public static final String THREADPOOLSIZE = "threadPoolSize";

	public static final String CHANNEL_POOL_MAX_WAIT = "channelPoolMaxWait";

	public static final String CHANNEL_POOL_MAX_SIZE = "channelPoolMaxSize";

}
