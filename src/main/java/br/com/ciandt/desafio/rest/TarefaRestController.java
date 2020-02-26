package br.com.ciandt.desafio.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ciandt.desafio.exception.AgendamentoTarefaException;
import br.com.ciandt.desafio.model.Tarefa;
import br.com.ciandt.desafio.service.TarefaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Tarefa")
public class TarefaRestController {
		
	@Autowired
	TarefaService service;
	
	@ApiOperation(value = "Retorna a lista de tarefas")
	@GetMapping(value="/tarefa")
	List<Tarefa> listar() {
		return service.findAll();
	}
	
	@ApiOperation(value = "Realiza o agendamento de uma tarefa")
	@PostMapping(value = "/tarefa")
	ResponseEntity<Tarefa> salvar(@Valid @RequestBody Tarefa tarefa) throws AgendamentoTarefaException  {
		return new ResponseEntity<Tarefa>(service.save(tarefa), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Executa imediatamente uma tarefa")
	@GetMapping(value="/tarefa/{id}")
	void executar(@PathVariable(required = true) Long id) throws AgendamentoTarefaException {
		service.execute(id);
	}
	

}
