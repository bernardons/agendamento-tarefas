package br.com.ciandt.desafio.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import br.com.ciandt.desafio.enums.StatusExecucao;

@Entity
@Table(name = "tb_tarefa", uniqueConstraints = { @UniqueConstraint(columnNames = { "tabela_origem", "tabela_destino", "agendamento" }, name="unique_tarefa") })
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 4495426895411749604L;

	private Long id;

	private String tabelaOrigem;

	private String tabelaDestino;

	private String agendamento;

	private LocalDateTime dataUltimaExecucao;

	@Enumerated(EnumType.ORDINAL)
	private StatusExecucao status = StatusExecucao.AGENDADO;
	
	public Tarefa() {
		super();
	}

	public Tarefa(Long id, String tabelaOrigem, String tabelaDestino, String agendamento) {
		this.id = id;
		this.tabelaOrigem = tabelaOrigem;
		this.tabelaDestino = tabelaDestino;
		this.agendamento = agendamento;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="sq_tb_tarefa")
	@SequenceGenerator(allocationSize=1,  name="sq_tb_tarefa", sequenceName = "sq_tb_tarefa")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank(message = "Tabela Origem deve ser informada!")
	@Column(updatable = false, name = "tabela_origem", nullable = false, length = 50)
	public String getTabelaOrigem() {
		return tabelaOrigem;
	}

	public void setTabelaOrigem(String tabelaOrigem) {
		this.tabelaOrigem = tabelaOrigem;
	}
	
	@NotBlank(message = "Tabela Destino deve ser informada!")
	@Column(updatable = false, name = "tabela_destino", nullable = false, length = 50)
	public String getTabelaDestino() {
		return tabelaDestino;
	}

	public void setTabelaDestino(String tabelaDestino) {
		this.tabelaDestino = tabelaDestino;
	}
	
	@NotBlank(message = "Agendamento (cron) deve ser informado.")
	@Column(updatable = false, name = "agendamento", nullable = false, length = 20)
	@Pattern(regexp = "^\\s*($|#|\\w+\\s*=|(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|/|,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|/|,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[0-5]?\\d)(?:(?:-|/|,)(?:[0-5]?\\d))?(?:,(?:[0-5]?\\d)(?:(?:-|/|,)(?:[0-5]?\\d))?)*)\\s+(\\?|\\*|(?:[01]?\\d|2[0-3])(?:(?:-|/|,)(?:[01]?\\d|2[0-3]))?(?:,(?:[01]?\\d|2[0-3])(?:(?:-|/|,)(?:[01]?\\d|2[0-3]))?)*)\\s+(\\?|\\*|(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|/|,)(?:0?[1-9]|[12]\\d|3[01]))?(?:,(?:0?[1-9]|[12]\\d|3[01])(?:(?:-|/|,)(?:0?[1-9]|[12]\\d|3[01]))?)*)\\s+(\\?|\\*|(?:[1-9]|1[012])(?:(?:-|/|,)(?:[1-9]|1[012]))?(?:L|W)?(?:,(?:[1-9]|1[012])(?:(?:-|/|,)(?:[1-9]|1[012]))?(?:L|W)?)*|\\?|\\*|(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?(?:,(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)(?:(?:-)(?:JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC))?)*)\\s+(\\?|\\*|(?:[0-6])(?:(?:-|/|,|#)(?:[0-6]))?(?:L)?(?:,(?:[0-6])(?:(?:-|/|,|#)(?:[0-6]))?(?:L)?)*|\\?|\\*|(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?(?:,(?:MON|TUE|WED|THU|FRI|SAT|SUN)(?:(?:-)(?:MON|TUE|WED|THU|FRI|SAT|SUN))?)*)(|\\s)+(\\?|\\*|(?:|\\d{4})(?:(?:-|/|,)(?:|\\d{4}))?(?:,(?:|\\d{4})(?:(?:-|/|,)(?:|\\d{4}))?)*))$", flags = Flag.UNICODE_CASE)
	public String getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(String agendamento) {
		this.agendamento = agendamento;
	}
	@Column(updatable = true, name = "dt_ultima_execucao", nullable=true)
	public LocalDateTime getDataUltimaExecucao() {
		return dataUltimaExecucao;
	}

	public void setDataUltimaExecucao(LocalDateTime dataUltimaExecucao) {
		this.dataUltimaExecucao = dataUltimaExecucao;
	}

	public StatusExecucao getStatus() {
		return status;
	}

	public void setStatus(StatusExecucao status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return (this.tabelaOrigem != null ? this.tabelaOrigem + "|" : "") + ( this.tabelaDestino != null ? this.tabelaDestino+ "|" : "") +  (this.agendamento != null ? this.agendamento : "");
	}

}
