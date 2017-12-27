package sajadv.common.exception;

/**
 * Excecao generica para representar os problemas gerais das aplicacoes retorna a mensagem do resource bundle.
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class BundleException extends GlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BundleException() {
		super();
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public BundleException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public BundleException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public BundleException(Throwable cause) {
		super(cause);
	}

}
