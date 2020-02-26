package br.com.ciandt.desafio.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.ciandt.desafio.exception.AgendamentoTarefaException;
import br.com.ciandt.desafio.model.Tarefa;
import br.com.ciandt.desafio.repository.TarefaRepository;

@Service
public class TarefaService {
	
	@Autowired
	TarefaRepository repository;
	
	@Autowired
	TarefaSchedule schedule;
	
	public List<Tarefa> findAll() {
		return repository.findAll();
	}

	public Tarefa save(@Valid Tarefa tarefa) throws AgendamentoTarefaException {

		try {
			return repository.save(tarefa);
		} finally {
			if (tarefa.getId() != null)
				schedule.addTaskToScheduler(tarefa);
		}

	}

	public void execute(Long id) throws AgendamentoTarefaException {
		try {
			Tarefa tarefa = repository.findById(id).get();
			schedule.executeImediate(tarefa);
		}catch (Exception e) {
			throw new AgendamentoTarefaException("Erro ao executar a tarefa id: " + id, e.getMessage());
		}
       

	}
	
	@EventListener({ ContextRefreshedEvent.class })
	void initializePoolSchedule() {
		findAll().stream().forEach(tarefa -> {
			schedule.addTaskToScheduler(tarefa);
		});
	}

}
