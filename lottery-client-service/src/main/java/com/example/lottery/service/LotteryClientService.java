package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LotteryClientService {
	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private AtomicInteger count = new AtomicInteger(0);
	
	public LotteryClientService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void init() {
		instances = discoveryClient.getInstances("lottery");
		instances.forEach( instance -> System.out.println("%s:%d".formatted(instance.getHost(),instance.getPort())));
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryService() {
		var rt = new RestTemplate();
		// round-robin
		int index = count.getAndIncrement() % instances.size();
		var instance = instances.get(index);
		var response = rt.getForObject("http://%s:%d/api/v1/numbers?column=3".formatted(instance.getHost(),instance.getPort()), String.class);
		System.out.println(response);
	}
}
