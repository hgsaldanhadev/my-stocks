package dev.hgsaldanha.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.dto.BrokerDTO;
import dev.hgsaldanha.exception.BrokerNotFoundException;
import dev.hgsaldanha.exception.MyStocksException;
import dev.hgsaldanha.model.Broker;
import dev.hgsaldanha.service.BrokerService;

@RestController
@RequestMapping("/api/brokers")
public class BrokerController {

	@Autowired
	private BrokerService brokerService;

	@Autowired
	private ModelMapper mm;

	@GetMapping
	public List<Broker> getBrokers() {
		return brokerService.getBrokers();
	}

	@GetMapping("/{id}")
	public Broker getBroker(@PathVariable Integer id) throws BrokerNotFoundException {
		return brokerService.getBroker(id);
	}

	@PostMapping
	public ResponseEntity<String> createBroker(@Valid @RequestBody BrokerDTO brokerDto) throws MyStocksException {
		Broker broker = mm.map(brokerDto, Broker.class);
		Integer id = brokerService.addBroker(broker);
		try {
			return ResponseEntity.created(new URI("/api/brokers/" + id)).build();
		} catch (URISyntaxException e) {
			throw new MyStocksException("Error creating a new Broker.", e);
		}
	}

	@PutMapping("/{id}")
	public void updateBroker(@PathVariable Integer id, @Valid @RequestBody BrokerDTO brokerDto) throws BrokerNotFoundException {
		Broker broker = mm.map(brokerDto, Broker.class);
		broker.setId(id);
		brokerService.updateBroker(broker);
	}

	@DeleteMapping("/{id}")
	public void deleteBroker(@PathVariable Integer id) throws BrokerNotFoundException {
		brokerService.deleteBroker(id);
	}
}
