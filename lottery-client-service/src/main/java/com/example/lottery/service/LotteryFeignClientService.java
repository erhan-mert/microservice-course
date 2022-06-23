package com.example.lottery.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LotteryFeignClientService {
	private final LotteryService lotteryService;
	
	public LotteryFeignClientService(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var response = lotteryService.getir(5);
		System.out.println(response);
	}
}
