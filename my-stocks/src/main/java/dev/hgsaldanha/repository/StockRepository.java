package dev.hgsaldanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hgsaldanha.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
