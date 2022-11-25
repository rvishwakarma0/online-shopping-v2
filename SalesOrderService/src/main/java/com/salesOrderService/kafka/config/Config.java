package com.salesOrderService.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.dtos.CustomerEvent;

@EnableKafka
@Configuration
public class Config {

	@Bean
	public ConsumerFactory<String, CustomerEvent>
	customerConsumer()
	{

		Map<String, Object> map
			= new HashMap<>();

		map.put(ConsumerConfig
					.BOOTSTRAP_SERVERS_CONFIG,
				"127.0.0.1:9092");

		map.put(ConsumerConfig
					.GROUP_ID_CONFIG,
				"id");
		map.put(ConsumerConfig
					.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		map.put(ConsumerConfig
					.VALUE_DESERIALIZER_CLASS_CONFIG,
				JsonDeserializer.class);

		
		return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), new JsonDeserializer<>(CustomerEvent.class));
		
		}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,
												CustomerEvent>
	customerListner()
	{
		ConcurrentKafkaListenerContainerFactory<String,
		CustomerEvent>
			factory
			= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(customerConsumer());
		return factory;
	}
}

