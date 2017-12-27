package sajadv.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Normalizer;
import java.util.List;

import sajadv.common.exception.GlobalException;

public abstract class StringUtils {
	
	public static final String EMPTY_STRING = "";
	
	public static boolean isNotBlank(String str) {
		return org.apache.commons.lang.StringUtils.isNotBlank(str);
	}
	
	public static boolean isBlank(String str) {
		return org.apache.commons.lang.StringUtils.isBlank(str);
	}
	
	public static boolean isNotEmptyTrimOrNull(Object value) {
		return !isEmptyTrimOrNull(value);
	}
	
	public static boolean isEmptyTrimOrNull(Object value) {
		return value == null || toString(value).trim().equals(EMPTY_STRING);
	}
	
	public static String trimInsideWithOneSpace(String string) {
		int amountBlanks = 0;
		StringBuilder newString = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c != ' ') {
				amountBlanks = 0;
			} else {
				amountBlanks += 1;
			}
			if (amountBlanks < 2) {
				newString.append(c);
			}
		}
		return newString.toString().trim();
	}
	
	public static String removerAcentuacao(String string) {
		string = Normalizer.normalize(string, Normalizer.Form.NFD);
		string = string.replaceAll("[^\\p{ASCII}]", "");
		return string;
	}
	
	@SuppressWarnings({ "unchecked" })
	public static String toString(Object obj) {
		if (obj == null) {
			return StringUtils.EMPTY_STRING;
		}
		if (obj instanceof String) {
			return (String) obj;
		}
		if (obj instanceof List) {
			return toStringList((List<Object>) obj);
		}
		return obj.toString();
	}

	private static String toStringList(List<Object> lista) {
		StringBuilder sb = new StringBuilder("");
		for(Object objeto : lista){
			if(!"".equals(sb.toString())){
				sb.append(", ");
			}
			sb.append(objeto.toString());
		}
		return sb.toString();
	}

	public static String decode(String usuarioLike) {
		try {
			return URLDecoder.decode(usuarioLike, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new GlobalException("Erro decode string " + usuarioLike, e);
		}
	}
	
	public static String zerosAEsquerda(String str, int tamanho) {
		String formato = "%0"+tamanho+"d";
		return String.format(formato, Integer.parseInt(str));
	}
}
