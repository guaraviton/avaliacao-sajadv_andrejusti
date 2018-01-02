package sajadv.common.util;

import java.util.ArrayList;
import java.util.List;
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
			List<String> mensagens = new ArrayList<String>();
			for(ConstraintViolation<BaseEntity> constraintViolation : violations){
				mensagens.add(constraintViolation.getMessage());
			}
			throw new ValidacaoException(mensagens);
		}
	}
}