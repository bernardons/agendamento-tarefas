package br.com.ciandt.desafio.model;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ciandt.desafio.enums.StatusExecucao;


public class TarefaTest {
	
	private static final long ID_TAREFA = 1L;
	private static final String AGENDAMENTO = "00000";
	private static final String TB_DESTINO = "tb_destino";
	private static final String TB_ORIGEM = "tb_origem";
	protected Tarefa tarefa;
	
	@BeforeEach
	public void init() {
		tarefa = new Tarefa(ID_TAREFA, TB_ORIGEM, TB_DESTINO, AGENDAMENTO );
	}
	@Test
	public void testCriarTarefa() {
		Assertions.assertEquals(1L, tarefa.getId());
		Assertions.assertEquals(tarefa.getTabelaOrigem(), TB_ORIGEM);
		Assertions.assertEquals(tarefa.getTabelaDestino(), TB_DESTINO);
		Assertions.assertEquals(tarefa.getAgendamento(), AGENDAMENTO);
	}
	
	@Test
	public void testId() {
		tarefa.setId(2L);
		Assertions.assertEquals(2L, tarefa.getId());
		Assertions.assertTrue(tarefa.getId() == 2L);
	}
	@Test
	public void testTabelaOrigem() {
		Assertions.assertTrue(TB_ORIGEM.equals(tarefa.getTabelaOrigem()));
		tarefa.setTabelaOrigem("TB");
		Assertions.assertTrue("TB".equals(tarefa.getTabelaOrigem()));
	}
	@Test
	public void testTabelaDestino() {
		Assertions.assertTrue(TB_DESTINO.equals(tarefa.getTabelaDestino()));
		tarefa.setTabelaDestino("TB");
		Assertions.assertTrue("TB".equals(tarefa.getTabelaDestino()));
	}
	
	@Test
	public void testAgendamento() {
		Assertions.assertTrue(AGENDAMENTO.equals(tarefa.getAgendamento()));
		tarefa.setAgendamento("11111");
		Assertions.assertTrue("11111".equals(tarefa.getAgendamento()));
	}
	
	@Test
	public void testDataUltimaExecucao() {
		LocalDateTime agora = LocalDateTime.now();
		tarefa.setDataUltimaExecucao(agora);
		Assertions.assertTrue(agora.equals(tarefa.getDataUltimaExecucao()));
	}
	
	@Test
	public void testStatus() {
		StatusExecucao status = StatusExecucao.EM_EXECUCAO;
		tarefa.setStatus(status);
		Assertions.assertTrue(status.equals(tarefa.getStatus()));
	}
	
	@Test
	public void testToString() {
		Assertions.assertTrue((tarefa.getTabelaOrigem() + "|" + tarefa.getTabelaDestino() + "|" + tarefa.getAgendamento()).equals(tarefa.toString()));
	}

}
