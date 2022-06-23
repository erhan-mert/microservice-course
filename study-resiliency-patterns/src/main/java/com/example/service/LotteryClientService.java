package com.example.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class LotteryClientService {
	private String cachedResponse = "[[1],[2],[3]]";

	//@Retry(name = "lottery", fallbackMethod = "callLotteryServiceFallback")
	//@RateLimiter(name = "lottery", fallbackMethod = "callLotteryServiceFallback")
	@CircuitBreaker(name="lottery")
	public String callLotteryService() {
		var rt = new RestTemplate();
		cachedResponse = rt.getForObject("http://localhost:9100/api/v1/numbers?column=3", String.class);
		return cachedResponse;
	}

	@TimeLimiter(name = "lottery", fallbackMethod = "asyncCallLotteryServiceFallback")
	public CompletableFuture<String> asyncCallLotteryService() {
		return CompletableFuture.supplyAsync( () -> {
			var rt = new RestTemplate();
			cachedResponse = rt.getForObject("http://localhost:9100/api/v1/numbers?column=3", String.class);
			return cachedResponse;			
		});
	}
	
	
	public String callLotteryServiceFallback(Exception e) {
		return cachedResponse;
	}
	
	
	public CompletableFuture<String> asyncCallLotteryServiceFallback(Exception e) {
		return CompletableFuture.supplyAsync( () -> cachedResponse );
	}
}
