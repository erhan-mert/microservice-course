package com.example.hr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.hr.service.WebSocketService;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	private final WebSocketService webSocketService;
	
	public WebSocketConfiguration(WebSocketService webSocketService) {
		this.webSocketService = webSocketService;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketService, "/hr-events")
		        .setAllowedOrigins("*");
	}
	
}
