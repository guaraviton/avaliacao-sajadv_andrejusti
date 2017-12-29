package sajadv.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import sajadv.common.exception.ValidacaoException;
import sajadv.entity.BaseEntity;

public class ValidationUtil {

	public static void validate(BaseEntity entity) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BaseEntity>> violations = validator.validate(entity);
		if(!violations.isEmpty()){
			throw new ValidacaoException(violations);
		}
	}
	
}
