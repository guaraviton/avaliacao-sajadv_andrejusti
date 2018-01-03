package sajadv.common.exception;

/**
 * Excecao generica para identificar erros de delecao dos registros referenciados em outras tabelas
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class RegistroReferenciadoException extends GlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensagem;

	public RegistroReferenciadoException(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
