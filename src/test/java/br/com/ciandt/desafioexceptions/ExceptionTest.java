package br.com.ciandt.desafioexceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ciandt.desafio.exception.AgendamentoTarefaException;

public class ExceptionTest {
	
	private static final String MENSAGEM_DE_ERRO = "mensagemDeErro";
	private static final String ERROR_CODE = "errorCode";
	AgendamentoTarefaException ex;
	
	@BeforeEach
	public void init() {
		 ex = new AgendamentoTarefaException(ERROR_CODE, MENSAGEM_DE_ERRO);
	}
	
	@Test
	public void testAgendamentoTarefaException() {
		Assertions.assertTrue(ERROR_CODE.equals(ex.getErrorCode()));
		Assertions.assertTrue(MENSAGEM_DE_ERRO.equals(ex.getMessage()));
	}
	
	@Test
	public void testSetters() {
		ex.setErrorCode(MENSAGEM_DE_ERRO);
		Assertions.assertTrue(MENSAGEM_DE_ERRO.equals(ex.getErrorCode()));
		ex.setMessage(ERROR_CODE);
		Assertions.assertTrue(ERROR_CODE.equals(ex.getMessage()));
	}

}
