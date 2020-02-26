package br.com.ciandt.desafio.batch;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import br.com.ciandt.desafio.dto.PessoaDTO;
import br.com.ciandt.desafio.model.Tarefa;

@Configuration
@EnableBatchProcessing
public class TarefaBatchConfig implements Runnable{
	
	private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;
    
    private final DataSource dataSource;
    
    @Autowired
    JobLauncher jobLauncher;
    
    private JobCompletionNotificationListener jobCompletionNotificationListener;
    
    private Tarefa tarefa = new Tarefa();
    
    private static final String QUERY_FIND_PESSOAS = "SELECT * FROM ";
    
    
	public TarefaBatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource, JobCompletionNotificationListener jobCompletionNotificationListener) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
		this.jobCompletionNotificationListener = jobCompletionNotificationListener;
	}
	
	@Bean
	@StepScope
    ItemReader<PessoaDTO> reader(@Value("#{jobParameters[tabelaOrigem]}") String tabelaOrigem) {
        JdbcCursorItemReader<PessoaDTO> databaseReader = new JdbcCursorItemReader<>();
        
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_PESSOAS + tabelaOrigem.toUpperCase());
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(PessoaDTO.class));
        databaseReader.open(new ExecutionContext());
        return databaseReader;
    }
	
	@Bean
	@StepScope
	public JdbcBatchItemWriter<PessoaDTO> writer(@Value("#{jobParameters[tabelaDestino]}") String tabelaDestino) {
		JdbcBatchItemWriter<PessoaDTO> writer = new JdbcBatchItemWriter<>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		writer.setSql("INSERT INTO " + tabelaDestino.toUpperCase() + " (id, nome, sobrenome, data_nascimento, email) VALUES (SQ_" + tabelaDestino.toUpperCase() + ".nextVal, :nome, :sobrenome, :dataNascimento, :email)");
		writer.setDataSource(this.dataSource);
		return writer;
	}
	
	@Bean
    public Job importPessoatJob() {
        return jobBuilderFactory.get("importPessoaJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionNotificationListener)
                .flow(step1())
                .end()
                .build();
    }
	
	
	@Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<PessoaDTO, PessoaDTO>chunk(10)
                .reader(reader(this.tarefa.getTabelaOrigem()))
                .writer(writer(this.tarefa.getTabelaDestino()))
                .build();
    }

	public TarefaBatchConfig build(Tarefa tarefa) {
		this.tarefa = tarefa;
		return this;
	}


	@Override
	public void run() {
		JobParameters jobParameters = new JobParametersBuilder().addString("data", LocalDateTime.now().toString())
				.addString("tabelaOrigem", tarefa.getTabelaOrigem())
				.addString("tabelaDestino", tarefa.getTabelaDestino()).addLong("idTarefa", tarefa.getId())
				.toJobParameters();

			try {
				jobLauncher.run(importPessoatJob(), jobParameters);
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
			}
		

	}
	

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
