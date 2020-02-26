package br.com.ciandt.desafio.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PessoaDTO implements Serializable{
	private static final long serialVersionUID = 2482263294331739361L;
	private Long id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String email;
	
	
	public PessoaDTO(Long id, String nome, String sobrenome, LocalDate dataNascimento, String email) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
	}
	
	public PessoaDTO() {
		super();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
