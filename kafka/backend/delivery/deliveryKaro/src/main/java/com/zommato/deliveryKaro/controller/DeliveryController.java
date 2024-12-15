package com.zommato.deliveryKaro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zommato.deliveryKaro.entity.Coordinate;
import com.zommato.deliveryKaro.service.DeliveryService;

@RestController
@RequestMapping("/location")
public class DeliveryController {
	
	private DeliveryService deliveryService;

	public DeliveryController(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateDeliveryLocation() {
//		Coordinate coordinate = new Coordinate();
//		coordinate.setLatitude(Math.random()*100);
//		coordinate.setLongitude(Math.random()*100);
		Map<String, Double> coordinates = new HashMap<>();
		coordinates.put("lat", Math.random()*100);
		coordinates.put("lon", Math.random()*100);
		
		
		
		this.deliveryService.updateLocation(coordinates);
		
		return new ResponseEntity<>("Location updated.", HttpStatus.OK);
		
	}
	
}
