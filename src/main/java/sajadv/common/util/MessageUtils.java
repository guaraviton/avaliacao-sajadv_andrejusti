package sajadv.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

	private static MessageSource messageSource;
	
	public static String get(String key, Object... params){
		return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
	}
	
	public static String get(String key){
		return get(key, new Object[]{});
	}

	@Autowired
	private void setMessageSource(MessageSource messageSource) {
		MessageUtils.messageSource = messageSource;
	}
}
