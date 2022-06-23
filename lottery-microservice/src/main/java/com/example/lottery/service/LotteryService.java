package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class LotteryService {
	@Value("${lottery.max}")
	private int lotteryMax;
	@Value("${lottery.size}")
	private int lotterySize;
	
	public List<Integer> draw(){
		return ThreadLocalRandom.current()
				                .ints(1, lotteryMax)
				                .distinct()
				                .limit(lotterySize)
				                .sorted()
				                .boxed()
				                .toList();
	}
	
	public List<List<Integer>> getLotteryNumbers(int column){
		try { TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500, 5000));}catch(Exception e) {}
		return IntStream.range(0, column)
				        .mapToObj( i -> draw() )
				        .toList();
	}
}
