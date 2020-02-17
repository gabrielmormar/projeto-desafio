package desafio.empresas.exceptions;

public class IllegalDeleteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public IllegalDeleteException(String msg) {
		super(msg);
	}
	
	public IllegalDeleteException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
