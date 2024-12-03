package com.reactive.webFlux01.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class FluxLearnService {
	
	public Flux<String> getFlux() {
		Flux<String> nameFlux = Flux.just("Ram", "Shyam", "Mohan", "Radha").log();
		return nameFlux;
	}
	
}
