package sajadv.common.exception;

/**
 * Excecao generica para representar os possiveis problemas nas operacoes de consultas executadas pelos DAOs
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class QueryException extends DAOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public QueryException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public QueryException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public QueryException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public QueryException(Throwable cause) {
		super(cause);
	}

}
