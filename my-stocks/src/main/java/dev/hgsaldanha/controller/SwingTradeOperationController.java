package dev.hgsaldanha.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.dto.SwingTradeOperationDTO;
import dev.hgsaldanha.model.SwingTradeOperation;
import dev.hgsaldanha.model.User;
import dev.hgsaldanha.service.SwingTradeOperationService;

@RestController
@RequestMapping("/api/swing")
public class SwingTradeOperationController {
	
	@Autowired
	private SwingTradeOperationService stService; //TODO rever nomes das variáveis
	
	@Autowired
	private ModelMapper mm;

	//TODO carregar o id do usuário da sessão
	private User getUser() {
		return new User();
	}
	
	@GetMapping
	public List<SwingTradeOperation> getSwingTradeOperations(Principal user) {
		return stService.getSwingTradeOperations(getUser().getId());
	}
	
	//TODO testar retorno em caso de entity not found exception
	@GetMapping("/{id}")
	public SwingTradeOperation getSwingTradeOperation(Long id) {
		return stService.getSwingTradeOperation(id, getUser().getId());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void addSwingTradeOperation(@Valid SwingTradeOperationDTO swingTradeDto) {
		SwingTradeOperation sto = mm.map(swingTradeDto, SwingTradeOperation.class);
		sto.getUser().setId(getUser().getId());
		stService.addSwingTradeOperation(sto);
	}
}
