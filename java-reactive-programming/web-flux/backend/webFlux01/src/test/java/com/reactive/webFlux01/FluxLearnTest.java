package com.reactive.webFlux01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reactive.webFlux01.service.FluxLearnService;

@SpringBootTest
public class FluxLearnTest {
	
	@Autowired
	private FluxLearnService service;
	
	@Test
	public void testing() {
		System.out.println("FluxLearnTest class testing method.");
		
	}
	
	@Test
	public void serviceTest() {
		service.getFlux().subscribe(data -> {
			System.out.println(data);
			System.out.println("Done with service flux data");
		});
	}
	
}
