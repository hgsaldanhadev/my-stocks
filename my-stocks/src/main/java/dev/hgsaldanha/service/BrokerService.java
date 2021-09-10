package dev.hgsaldanha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.hgsaldanha.exception.BrokerNotFoundException;
import dev.hgsaldanha.model.Broker;
import dev.hgsaldanha.repository.BrokerRepository;

@Service
public class BrokerService {

	@Autowired
	private BrokerRepository brokers;

	public List<Broker> getBrokers() {
		return brokers.findAll(Sort.by("name"));
	}

	public Broker getBroker(Integer id) throws BrokerNotFoundException {
		return brokers.findById(id).orElseThrow(() -> new BrokerNotFoundException());
	}

	public Integer addBroker(Broker broker) {
		broker.setId(null);
		return brokers.save(broker).getId();
	}

	public void updateBroker(Broker broker) throws BrokerNotFoundException {
		brokers.findById(broker.getId()).orElseThrow(() -> new BrokerNotFoundException());
		brokers.save(broker);
	}

	public void deleteBroker(Integer id) throws BrokerNotFoundException {
		brokers.findById(id).orElseThrow(() -> new BrokerNotFoundException());
		brokers.deleteById(id);
	}

}
