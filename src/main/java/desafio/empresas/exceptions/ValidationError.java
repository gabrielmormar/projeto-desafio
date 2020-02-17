package desafio.empresas.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> messages = new ArrayList<FieldMessage>();

	public ValidationError(String msg, Integer status, Long timestamp) {
		super(msg, status, timestamp);
	}
	public List<FieldMessage> getErrors() {
		return messages;
	}
	
	public void addError(String field, String message) {
		messages.add(new FieldMessage(field, message));
	}

}
