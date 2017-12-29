package sajadv.common.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

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

	private Set<ConstraintViolation<? extends Object>> violations;

	public ValidacaoException(Object violations) {
		this.violations = (Set<ConstraintViolation<? extends Object>>) violations;
	}

	public Set<ConstraintViolation<? extends Object>> getViolations() {
		return violations;
	}

}
