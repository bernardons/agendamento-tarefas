package br.com.ciandt.desafioexceptions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ciandt.desafio.exception.ErrorResponse;

public class ErrorResponseTest {
	
	private static final String DETALHE_ERRO = "detalheErro";
	private static final String MENSAGEM_DE_ERRO = "mensagemDeErro";
	private ErrorResponse erro;
	
	@BeforeEach
	public void init() {
		String detalhes = DETALHE_ERRO;
		List<String> listDetalhes = new ArrayList<>();
		listDetalhes.add(detalhes);
		erro = new ErrorResponse(MENSAGEM_DE_ERRO, listDetalhes);
	}
	
	@Test
	public void testErrorResponse() {
		Assertions.assertTrue(MENSAGEM_DE_ERRO.equals(erro.getMessage()));
		Assertions.assertTrue(erro.getDetails().contains(DETALHE_ERRO));
	}
	
	@Test
	public void testSetErrorResponse(){
		List<String> det = erro.getDetails();
		det.add("maisUmDEtalhe");
		erro.setDetails(det);
		Assertions.assertTrue(erro.getDetails().size() == 2);
		erro.setMessage(DETALHE_ERRO);
		Assertions.assertTrue(DETALHE_ERRO.equals(erro.getMessage()));
	}

}
