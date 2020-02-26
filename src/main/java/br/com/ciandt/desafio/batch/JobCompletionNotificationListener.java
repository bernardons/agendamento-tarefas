package br.com.ciandt.desafio.batch;

import java.time.ZoneId;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ciandt.desafio.enums.StatusExecucao;
import br.com.ciandt.desafio.model.Tarefa;
import br.com.ciandt.desafio.repository.TarefaRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	@Autowired
	TarefaRepository repository;
	
	@Autowired
	DataSource dataSource;

	@Override
	public void afterJob(JobExecution jobExecution) {

		Long idTarefa = jobExecution.getJobParameters().getLong("idTarefa");
		Optional<Tarefa> opt = repository.findById(idTarefa);
		if (opt.isPresent()) {
			Tarefa tarefa = opt.get();
			if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
				tarefa.setStatus(StatusExecucao.EM_EXECUCAO);
				tarefa.setDataUltimaExecucao(jobExecution.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
			} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
				tarefa.setStatus(StatusExecucao.FALHA);
			}
			repository.save(tarefa);
		}

	}
	
	

}
