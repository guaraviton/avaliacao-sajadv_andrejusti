package sajadv.common.exception;

/**
 * Excecao generica para representar os possiveis problemas nas operacoes executadas pelos DAOs.
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class DAOException extends GlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DAOException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

}
