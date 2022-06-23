package com.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

// Reactive Filter
@Component
public class AuditingFilter implements GlobalFilter{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		var request = exchange.getRequest();
		System.out.println("URL			: %s".formatted(request.getPath().toString()));
		System.out.println("Query params: %s".formatted(request.getQueryParams()));
		request.getBody().subscribe(System.out::println);
		request.getHeaders()
		       .forEach( (name,value) -> System.out.println("%s: %s".formatted(name,value)));
		return chain.filter(exchange);
	}

}
