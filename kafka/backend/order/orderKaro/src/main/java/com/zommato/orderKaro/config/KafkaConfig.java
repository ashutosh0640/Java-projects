package com.zommato.orderKaro.config;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {
	
	@KafkaListener(topics=AppConstant.LOCATION_TOPIC_NAME, groupId=AppConstant.GROUP_ID)
	public void updateLocation(Map<String, Double> location) {
		System.out.println("current Location: "+ location.toString());
	}

}
