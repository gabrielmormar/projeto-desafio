package desafio.empresas.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException object, HttpServletRequest request) {		
		System.out.println("DENTRO DO OBJECT NOT FOUND");
		StandardError error = new StandardError(object.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(IllegalDeleteException.class)
	public ResponseEntity<StandardError> illegalDeletion(IllegalDeleteException object, HttpServletRequest request) {		
		System.out.println("DENTRO DO ILLEGAL DELETE");
		StandardError error = new StandardError(object.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	/**
	 * Valida argumentos invalidos definidos na DTO
	 * @param object
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> invalidArgument(MethodArgumentNotValidException object, HttpServletRequest request) {
		System.out.println("DENTRO DO INVALID ARGUMENT");
		ValidationError error = new ValidationError("Erros de validação", HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		for (FieldError err : object.getBindingResult().getFieldErrors()) {
			error.addError(err.getField(), err.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorizationDenied(AuthorizationException object, HttpServletRequest request) {		
		System.out.println("DENTRO DO DENIED");
		StandardError error = new StandardError(object.getMessage(), HttpStatus.FORBIDDEN.value(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
}
