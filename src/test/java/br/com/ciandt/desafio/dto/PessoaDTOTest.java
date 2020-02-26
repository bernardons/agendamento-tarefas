package br.com.ciandt.desafio.dto;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PessoaDTOTest {
	
	private static final long ID = 1L;
	private static final String EMAIL = "pessoa@email.com";
	private static final LocalDate DATA_NASCIMENTO = LocalDate.of(1979, 6, 12);
	private static final String SOBRENOME_DA_PESSOA = "Sobrenome da Pessoa";
	private static final String NOME_DA_PESSOA = "Nome da Pessoa";
	PessoaDTO pessoa;
	
	@BeforeEach
	public void init() {
		pessoa = new PessoaDTO(ID, NOME_DA_PESSOA, SOBRENOME_DA_PESSOA, DATA_NASCIMENTO, EMAIL);
	}
	
	@Test
	public void testId() {
		Assertions.assertTrue(pessoa.getId() == ID);
		pessoa.setId(2L);
		Assertions.assertTrue(pessoa.getId() == 2L);
	}
	
	@Test
	public void testNome() {
		Assertions.assertTrue(NOME_DA_PESSOA.equals(pessoa.getNome()));
		pessoa.setNome("fulano");
		Assertions.assertTrue("fulano".equals(pessoa.getNome()));
		
	}
	
	@Test
	public void testSobreNome() {
		Assertions.assertTrue(SOBRENOME_DA_PESSOA.equals(pessoa.getSobrenome()));
		pessoa.setSobrenome("beltrano");
		Assertions.assertTrue("beltrano".equals(pessoa.getSobrenome()));
		
	}
	
	@Test
	public void testDataNascimento() {
		Assertions.assertTrue(DATA_NASCIMENTO.equals(pessoa.getDataNascimento()));
		LocalDate date = LocalDate.now();
		pessoa.setDataNascimento(date);
		Assertions.assertTrue(date.equals(pessoa.getDataNascimento()));
		
	}
	
	@Test
	public void testEmail() {
		Assertions.assertTrue(EMAIL.equals(pessoa.getEmail()));
		pessoa.setEmail("email@email.com");
		Assertions.assertTrue("email@email.com".equals(pessoa.getEmail()));
		
	}
	
	

}
