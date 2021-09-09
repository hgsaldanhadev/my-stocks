package dev.hgsaldanha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.hgsaldanha.model.Broker;
import dev.hgsaldanha.repository.BrokerRepository;

@Service
public class BrokerService {
	
	@Autowired
	private BrokerRepository brokers;

	public List<Broker> getBrokers() {
		return brokers.findAll(Sort.by("name"));
	}

	public Broker getBroker(Integer id) {
		return brokers.getById(id);
	}

}
