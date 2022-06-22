package com.example.lottery.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.service.LotteryService;

@RestController
@RequestScope
@RequestMapping("/numbers")
@Validated
@CrossOrigin
public class LotteryRestController {

	private final LotteryService lotteryService;
	@Value("${server.port}")
	private int port;
	
	public LotteryRestController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}
	
	@GetMapping(params="column")
	public List<List<Integer>> getLotteryNumbers(@RequestParam @Min(3) @Max(20) int column){
		System.out.println("New request has arrived at ".formatted(port));
		return lotteryService.getLotteryNumbers(column);
	}
	
}
