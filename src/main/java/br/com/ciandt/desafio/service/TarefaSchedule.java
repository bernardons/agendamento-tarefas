package br.com.ciandt.desafio.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import br.com.ciandt.desafio.batch.TarefaBatchConfig;
import br.com.ciandt.desafio.model.Tarefa;

@Component
public class TarefaSchedule {

	TaskScheduler scheduler;

	TarefaBatchConfig tarefaBatchConfig;

	Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

	public TarefaSchedule(TaskScheduler scheduler, TarefaBatchConfig tarefaBatchConfig) {
		this.scheduler = scheduler;
		this.tarefaBatchConfig = tarefaBatchConfig;
	}

	public void addTaskToScheduler(Tarefa tarefa){
		if (!jobsMap.containsKey(tarefa.toString())) {
			Runnable tarefaRunnable = tarefaBatchConfig.build(tarefa);
			ScheduledFuture<?> scheduledTask = scheduler.schedule(tarefaRunnable, new CronTrigger(tarefa.getAgendamento(), TimeZone.getTimeZone(TimeZone.getDefault().getID())));
			jobsMap.put(tarefa.toString(), scheduledTask);
		}
	}

	public void executeImediate(Tarefa tarefa) {
		Runnable tarefaRunnable = tarefaBatchConfig.build(tarefa);
		ScheduledFuture<?> scheduledTask = scheduler.schedule(tarefaRunnable, Instant.now());
		jobsMap.put(tarefa.toString(), scheduledTask);
	}

}
