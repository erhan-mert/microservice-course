package com.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

// Reactive Filter
@Component
public class ProfilingFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		long start = System.currentTimeMillis();
		var response = chain.filter(exchange);
		return response.doOnSuccess((Void) -> {
			long stop = System.currentTimeMillis();
			System.out.println("Response time for (%s) is %d".formatted(request.getPath().toString(),stop-start));
		});
	}

}
