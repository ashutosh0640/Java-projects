package com.zommato.deliveryKaro.service;


import java.util.Map;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.zommato.deliveryKaro.config.AppConstant;

@Service
public class DeliveryService {
	
	
	private final KafkaTemplate<String, Map<String, Double>> kafkaTemplate;
	
	
	public DeliveryService(KafkaTemplate<String, Map<String, Double>> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	public boolean updateLocation(Map<String, Double> coordinates) {
		System.out.println(coordinates.toString());
		try {
			this.kafkaTemplate.send(AppConstant.LOCATION_TOPIC_NAME, coordinates);
			return true;
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

}
