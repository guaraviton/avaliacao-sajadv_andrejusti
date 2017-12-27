package sajadv.spring.handler.exception;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import sajadv.common.exception.BundleException;
import sajadv.common.exception.DAOException;
import sajadv.common.exception.GlobalException;
import sajadv.common.util.MessageUtils;
import sajadv.spring.handler.exception.dto.ValidationErrorDTO;

@ControllerAdvice
public class SpringExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringExceptionHandler.class);

	@ExceptionHandler({HibernateOptimisticLockingFailureException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO handleHibernateOptimisticLockingFailureException(HibernateOptimisticLockingFailureException exception) {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		dto.addError(MessageUtils.get("erro.registro.desatualizado"));
		LOGGER.error("Erro HibernateOptimisticLockingFailureException capturado", exception);
		return dto;
	}
	
	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO handleFKException(DataIntegrityViolationException exception) {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		if(exception.getCause() instanceof ConstraintViolationException){
			ConstraintViolationException cve = (ConstraintViolationException) exception.getCause();
			dto.addError(MessageUtils.get(cve.getConstraintName()));
		}else{
			dto.addError(MessageUtils.get("erro.mensagem.sistema", exception.getMostSpecificCause().getMessage()));
		}
		LOGGER.error("Erro DataIntegrityViolationException capturado", exception);
		return dto;
	}
	
	@ExceptionHandler({DAOException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO handleDAOException(DAOException exception) {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		dto.addError(resolveMessage(exception));
		LOGGER.error("Erro capturado", exception);
		return dto;
	}

	@ExceptionHandler({GlobalException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO handleBusinessException(GlobalException exception) {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		dto.addError(resolveMessage(exception));
		LOGGER.error("Erro capturado", exception);
		return dto;
	}

	private String resolveMessage(GlobalException exception) {
		if (exception instanceof BundleException) {
			return MessageUtils.get(exception.getMessage());
		}
		return exception.getMessage();
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO handleValidationException(MethodArgumentNotValidException mav) {
		try {
			BindingResult result = mav.getBindingResult();
			List<ObjectError> allErrors = result.getAllErrors();
			return processarObjectErrors(allErrors, result.getTarget());
		} catch (Exception e) {
			throw new GlobalException("Erro ao montar mensagens de validacao", e);
		}
	}

	private ValidationErrorDTO processarObjectErrors(List<ObjectError> objectErrors, Object target) throws Exception {
		ValidationErrorDTO dto = new ValidationErrorDTO();
		String localizedErrorMessage = "";
		String field;
		for (ObjectError objectError : objectErrors) {
			localizedErrorMessage = resolveLocalizedErrorMessage(objectError);
			if (objectError instanceof FieldError) {
				field = ((FieldError) objectError).getField();
			} else {
				field = null;
			}
			dto.addFieldError(field, localizedErrorMessage);
		}
		return dto;
	}

	private String resolveLocalizedErrorMessage(ObjectError objectError) {

		String localizedErrorMessage = "";
		String fieldErrorMessage = "";
		for (String key : objectError.getCodes()) {
			try {

				if (objectError instanceof FieldError) {
					FieldError field = (FieldError) objectError;
					localizedErrorMessage = MessageUtils.get(key, field.getField());
					break;
				} else {
					localizedErrorMessage = MessageUtils.get(key);
					break;
				}

			} catch (NoSuchMessageException ex) {
			}
		}
		if ("".equals(localizedErrorMessage)) {
			try {
				localizedErrorMessage = MessageUtils.get(objectError.getDefaultMessage());
			} catch (NoSuchMessageException e) {
				try {

					if (objectError instanceof FieldError) {
						FieldError field = (FieldError) objectError;
						fieldErrorMessage = MessageUtils.get(field.getField());
					}

				} catch (NoSuchMessageException e2) {
					fieldErrorMessage = ((FieldError) objectError).getField();
				}
				localizedErrorMessage = fieldErrorMessage + " " + objectError.getDefaultMessage().toLowerCase();
			}
		}
		return localizedErrorMessage;
	}
}
