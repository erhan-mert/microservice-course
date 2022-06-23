package com.example.lottery.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class LotteryFallbackService implements LotteryService {

	@Override
	public List<List<Integer>> getir(int column) {
		return IntStream.range(0, column)
				        .mapToObj(i -> List.of(4,8,15,16,23,42))
				        .toList();
	}

}
