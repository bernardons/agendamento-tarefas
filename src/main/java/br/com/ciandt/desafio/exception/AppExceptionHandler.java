package br.com.ciandt.desafio.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(DataIntegrityViolationException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add("Já existe uma tarefa com as configurações informadas!");
		ErrorResponse error = new ErrorResponse("Bad Request", details);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(AgendamentoTarefaException.class)
    public final ResponseEntity<ErrorResponse> handleExceptions(AgendamentoTarefaException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		if ("agendamento".equals(ex.getBindingResult().getFieldError().getField())) {
			details.add("Padrão inválido para o Agendamento(Cron)");
		} else {
			for (ObjectError error : ex.getBindingResult().getAllErrors()) {
				details.add(error.getDefaultMessage());
			}
		}

		ErrorResponse error = new ErrorResponse("Validation Failed", details);
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
}
