package roberto.cursospringboot.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import roberto.cursospringboot.services.exceptions.DataIntegrityException;
import roberto.cursospringboot.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
			HttpServletRequest request) { // método padrão do ControllerAdvice

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> objectNotFoundException(DataIntegrityException e, HttpServletRequest request) { // método
																															// padrão
																															// do
																															// ControllerAdvice

		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) { // método
																														// padrão
																														// do
																														// ControllerAdvice

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação",System.currentTimeMillis());
		//percorrendo a lista de error que o Postman mostra:
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
