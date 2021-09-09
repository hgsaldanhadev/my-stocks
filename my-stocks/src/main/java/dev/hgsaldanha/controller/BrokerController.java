package dev.hgsaldanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.model.Broker;
import dev.hgsaldanha.service.BrokerService;

@RestController
@RequestMapping("/api/brokers")
public class BrokerController {
	
	@Autowired
	private BrokerService brokerService;

	@GetMapping
	public List<Broker> getBrokers() {
		return brokerService.getBrokers();
	}
	
	//TODO testar retorno em caso de entity not found exception
	@GetMapping("/{id}")
	public Broker getBroker(Integer id) {
		return brokerService.getBroker(id);
	}
}
