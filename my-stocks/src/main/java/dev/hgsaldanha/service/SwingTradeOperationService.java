package dev.hgsaldanha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.hgsaldanha.model.SwingTradeOperation;
import dev.hgsaldanha.model.User;
import dev.hgsaldanha.repository.SwingTradeOperationRepository;

@Service
public class SwingTradeOperationService {
	
	@Autowired
	private SwingTradeOperationRepository swingTradeOperations;

	public List<SwingTradeOperation> getSwingTradeOperations(Long userId) {
		SwingTradeOperation sto = new SwingTradeOperation();
		User u = new User();
		sto.setUser(u);
		return swingTradeOperations.findAll(Example.of(sto), Sort.by("startDate").descending());
	}

	public SwingTradeOperation getSwingTradeOperation(Long id, Long userId) {
		//TODO como considerar o usuário?
		return swingTradeOperations.getById(id);
	}

	public void addSwingTradeOperation(SwingTradeOperation sto) {
		sto.setId(null);
		swingTradeOperations.save(sto);
		
	}

}
