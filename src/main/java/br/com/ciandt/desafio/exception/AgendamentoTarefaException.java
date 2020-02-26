package br.com.ciandt.desafio.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgendamentoTarefaException extends Exception {

	private static final long serialVersionUID = -6531236306898927699L;
	private String errorCode;
    private String message;
 
    public AgendamentoTarefaException(String errorCode, String message) {
    	super();
        this.errorCode = errorCode;
        this.message = message;
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
