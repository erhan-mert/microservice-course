package com.example.hr.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class WebSocketService implements WebSocketHandler, MessageClient {

	private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("New message has arrived: %s".formatted(message.toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error has occurred at session (%s): %s.".formatted(session.getId(),e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		String sessionId = session.getId();
		System.err.println("Session is closed: %s".formatted(sessionId));
		sessions.remove(sessionId);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@Override
	public void sendMessage(String message) {
		sessions.forEach( (sessionId, session) ->{
			try {
				session.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				System.err.println("Error while sending websocket message: %s".formatted(e.getMessage()));
			}
		});
		
	}

}
