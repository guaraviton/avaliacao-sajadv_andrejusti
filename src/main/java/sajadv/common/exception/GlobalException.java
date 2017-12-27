package sajadv.common.exception;

/**
 * Excecao generica para representar os problemas gerais das aplicacoes.
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GlobalException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public GlobalException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public GlobalException(Throwable cause) {
		super(cause);
	}

}
