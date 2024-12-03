package com.reactive.webFlux01;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@SpringBootTest
class WebFlux01ApplicationTests {

	
	@Test
	public void test() {
		FluxLearnTest fluxLearn = new FluxLearnTest();
		fluxLearn.testing();
		System.out.println("-----------------");
		System.out.println("Test started");
		
		Mono<String> monoPublisher = Mono.just("Just Testing");
		monoPublisher.subscribe(new CoreSubscriber<String>() {

			@Override
			public void onNext(String t) {
				System.out.println("data: "+t);
				
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("error: "+t.getMessage());
				
			}

			@Override
			public void onComplete() {
				System.out.println("Completed");
				
			}

			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("Subscription done!");
				s.request(6);
				
			}
			
		});
		System.out.println("-----------------");
		
		
	}
	
	
	@Test
	public void test01() {
		Mono<String> m1 = Mono.just("This is first test data!");
		Mono<String> m2 = Mono.just("This is second test data!");
		Mono<Integer> m3 = Mono.just(343434);
		
		Mono<String> m1Upper = m1.map(String::toUpperCase);
		
		Mono<Tuple3<String, String, Integer>> combinedMono = Mono.zip(m1Upper, m2, m3);
		
		combinedMono.subscribe(data -> {
			System.out.println(data.getT1());
			System.out.println(data.getT2());
			System.out.println(data.getT3());
			
		});
		
		System.out.println("-----------------");
		
		Mono<String> m4 = Mono.just("This is flat map test data.");
		Mono<String[]> m4Array = m4.flatMap(data->Mono.just(data.split(" ")));
		m4Array.subscribe(data -> {
			for(String s : data) {
				System.out.println("m4FlatMap: "+s.toUpperCase());
			}
		});
		
		System.out.println("-----------------");
		
		Flux<String> m4Flux = m4.flatMapMany(data -> Flux.just(data.split(" ")));
		m4Flux.subscribe(data -> {
			System.out.println("m4Flux: "+data);
		});
		
		System.out.println("-----------------");
		
		Flux<String> m1ConcatWithM2 = m1.concatWith(m2).log();
		m1ConcatWithM2.subscribe(System.out::println);
		
		System.out.println("-----------------");
	}

}
