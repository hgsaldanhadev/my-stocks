package dev.hgsaldanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.exception.BrokerNotFoundException;
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

	@GetMapping("/{id}")
	public Broker getBroker(@PathVariable Integer id) throws BrokerNotFoundException {
		return brokerService.getBroker(id);
	}
}
