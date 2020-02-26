package br.com.ciandt.desafio.enums;

public enum StatusExecucao {
	
	AGENDADO("Agendado"), EM_EXECUCAO("Em execução"), FALHA("Falha");
	
	private final String descricao;
	
	private StatusExecucao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}

}
