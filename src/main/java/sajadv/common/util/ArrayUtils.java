package sajadv.common.util;

import org.apache.commons.lang.StringUtils;

public class ArrayUtils {
	
	public static String toString(Object[] array) {
		return StringUtils.join(array, ", ");
	}
	
}
