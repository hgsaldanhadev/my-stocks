package dev.hgsaldanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hgsaldanha.model.Broker;

public interface BrokerRepository extends JpaRepository<Broker, Integer> {

}
