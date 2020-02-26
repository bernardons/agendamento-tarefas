package br.com.ciandt.desafio.rest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ciandt.desafio.exception.AppExceptionHandler;
import br.com.ciandt.desafio.model.Tarefa;
import br.com.ciandt.desafio.repository.TarefaRepository;
import br.com.ciandt.desafio.service.TarefaSchedule;
import br.com.ciandt.desafio.service.TarefaService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TarefaRestControllerTest {
	
    private MockMvc mockMvc;

    @MockBean
    private TarefaService tarefaService;
    
    @MockBean
    private TarefaSchedule tarefaaSchedule;
    
    @MockBean
    private TarefaRepository tarefaRepository;
    
    @InjectMocks
    TarefaRestController controller;
    
    Tarefa tarefa;
    
    @BeforeEach
    public void init() {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new AppExceptionHandler())
                
                .build();
    	tarefa = new Tarefa(1L, "tabela1", "tabela2", "* * * * * *");
    }
    
    @Test
    public void findAll() throws Exception {
       
        List<Tarefa> tarefas = Arrays.asList(tarefa);
        given(tarefaService.findAll()).willReturn(tarefas);

        this.mockMvc.perform(get("/tarefa"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1,'tabelaOrigem': 'tabela1';'tabelaDestino': 'tabela2', 'agendamento' : '* * * * * *'}]"));
    }
    
	@Test
	public void save() throws Exception {		
		
		String inputJson = new ObjectMapper().writeValueAsString(tarefa);
		MvcResult mvcResult = this.mockMvc.perform(
				post("/tarefa").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		Assertions.assertEquals(200, status);
	}
	
	@Test
	public void execute() throws Exception {		
		
        this.mockMvc.perform(get("/tarefa/"+tarefa.getId()))
                .andExpect(status().isOk());
	}
	
	
	
	

}
