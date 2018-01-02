package sajadv.common.exception;

import java.util.List;

/**
 * Excecao generica para representar as validacoes feitas fora do binding result
 * 
 * @version 1.0
 * @author y2jm - FABIO PAIVA
 * 
 */
public class ValidacaoException extends GlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> mensagens;

	public ValidacaoException(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public List<String> getMensagens() {
		return mensagens;
	}
}
