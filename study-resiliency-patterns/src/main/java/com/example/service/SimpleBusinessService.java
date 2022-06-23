package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimpleBusinessService {
	private final LotteryClientService lotteryClientService;

	public SimpleBusinessService(LotteryClientService lotteryClientService) {
		this.lotteryClientService = lotteryClientService;
	}

	//@Scheduled(fixedRate = 3_000)
	public void callAnotherService() {
		var response = lotteryClientService.callLotteryService();
		System.out.println(response);
	}

	@Scheduled(fixedRate = 3_000)
	public void callAnotherAsyncService() {
		lotteryClientService.asyncCallLotteryService()
		                    .thenAcceptAsync(System.err::println);
	}
}
