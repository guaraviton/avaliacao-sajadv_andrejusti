package sajadv.spring.handler.exception.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO{
	
	private List<ErrorDTO> errors = new ArrayList<ErrorDTO>();
	
    public void addFieldError(String field, String message) {
        FieldErrorDTO error = new FieldErrorDTO(field, message);
        errors.add(error);
    }
    
    public void addError(String message) {
        ErrorDTO error = new ErrorDTO(message);
        errors.add(error);
    }

	public List<ErrorDTO> getErrors() {
		return errors;
	}
}