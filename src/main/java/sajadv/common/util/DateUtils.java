package sajadv.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DateUtils {

	private static final SimpleDateFormat DEFAULTDATEFORMAT = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat DEFAULTDATEFORMAT2 = new SimpleDateFormat("dd/MM/yy");
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	private static final SimpleDateFormat DEFAULTDATETIMEFORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public static final String PATTERN_YYYY_MM_DD_HH_MM = "dd/MM/yyyy HH:mm";

	public static final Locale LOCALE_BR = new Locale("pt", "BR");

	private static final Pattern DATE_PATTERN_FINDER = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");

	public static String formatDateTime(Date date) {
		if (date != null) {
			return DEFAULTDATETIMEFORMAT.format(date);
		}
		return StringUtils.EMPTY_STRING;
	}

	public static String format(Date date) {
		if (date != null) {
			return DEFAULTDATEFORMAT.format(date);
		}
		return StringUtils.EMPTY_STRING;
	}

	public static String format(Date date, String pattern) {
		return format(date, pattern, LOCALE_BR);
	}

	public static String format(Date date, String pattern, Locale locale) {
		if (date != null) {
			return new SimpleDateFormat(pattern, locale).format(date);
		}
		return StringUtils.EMPTY_STRING;
	}

	public static Date newDateMoreDays(int days) {
		return dateMoreDays(new Date(), days);
	}

	public static Date dateMoreDays(Date date, int days) {
		if (days != 0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, days);
			return cal.getTime();
		}
		return date;
	}

	/**
	 * Adiciona mes(es) a data informada.
	 * 
	 * @param date
	 *            Data que ter� a o m�s alterado.
	 * @param months
	 *            Quantidade de meses que ser� adicionada a data informada.
	 * @return
	 */
	public static Date dateMoreMonths(Date date, int months) {
		if (date != null && months != 0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, months);
			return cal.getTime();
		}
		return date;
	}

	public static Date newDateMoreYears(int year) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}

	public static boolean isWeekend(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDate(String date) {
		try {
			DEFAULTDATEFORMAT.setLenient(false);
			return DEFAULTDATEFORMAT.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseDates(String date) {
		Date result = null;
		try {
			String str = date.replaceAll("\\/", "");
			if (str.length() == 8) {
				DEFAULTDATEFORMAT.setLenient(false);
				result = DEFAULTDATEFORMAT.parse(date);
			} else if (str.length() == 6) {
				DEFAULTDATEFORMAT2.setLenient(false);
				result = DEFAULTDATEFORMAT2.parse(date);
			}

			return result;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @param date
	 * @return
	 */
	public static Date parseDateTime(String date) {
		try {
			return DEFAULTDATETIMEFORMAT.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parse(String date, String... pattern) {
		for (String string : pattern) {
			Date data = parse(date, string);
			if (data != null) {
				return data;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar parseStringToCalendar(String date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar parseDateToCalendar(Date date) {

		Calendar cal = null;
		if (!isNullable(date)) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}

		return cal;
	}

	/**
	 * 
	 * @param date
	 * @param day
	 * @param month
	 * @param year
	 * @param hour
	 * @param min
	 * @param sec
	 * @param millisec
	 * @return
	 */
	public static Date setDate(Date date, int day, int month, int year, int hour, int min, int sec, int millisec) {
		if (date != null) {
			Calendar dateCal = Calendar.getInstance();
			dateCal.setTime(date);
			dateCal.set(Calendar.DAY_OF_MONTH, day);
			dateCal.set(Calendar.MONTH, month);
			dateCal.set(Calendar.YEAR, year);
			dateCal.set(Calendar.HOUR_OF_DAY, hour);
			dateCal.set(Calendar.MINUTE, min);
			dateCal.set(Calendar.SECOND, sec);
			dateCal.set(Calendar.MILLISECOND, millisec);
			return dateCal.getTime();
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @param hour
	 * @param min
	 * @param sec
	 * @param millisec
	 * @return
	 */
	public static Date setHour(Date date, int hour, int min, int sec, int millisec) {
		if (date != null) {
			Calendar dateCal = Calendar.getInstance();
			dateCal.setTime(date);
			dateCal.set(Calendar.HOUR_OF_DAY, hour);
			dateCal.set(Calendar.MINUTE, min);
			dateCal.set(Calendar.SECOND, sec);
			dateCal.set(Calendar.MILLISECOND, millisec);
			return dateCal.getTime();
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date setMinHour(Date date) {
		return setHour(date, 0, 0, 0, 0);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date setMaxHour(Date date) {
		return setHour(date, 23, 59, 59, 999);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date parseNullableDate(Date date) {
		if (isNullable(date)) {
			return null;
		}
		return date;
	}

	public static boolean isNullable(Date date) {
		boolean isNull = true;
		if (date != null) {
			isNull = false;
			Calendar newDate = Calendar.getInstance();
			newDate.setTime(date);
			if (newDate.get(Calendar.YEAR) == 2382) {
				isNull = true;
			}
		}
		return isNull;
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isGreaterOrEqualThan(Date date, Date then, boolean onlyDate) {
		if (date != null && then != null) {
			Date newDate = toDate(date, onlyDate);
			Date newThen = toDate(then, onlyDate);
			return newDate.compareTo(newThen) >= 0;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isGreaterThan(Date date, Date then, boolean onlyDate) {
		if (date != null && then != null) {
			Date newDate = toDate(date, onlyDate);
			Date newThen = toDate(then, onlyDate);
			return newDate.compareTo(newThen) > 0;
		}
		return false;
	}

	public static boolean isGreaterThan(Calendar date, Calendar then, boolean onlyDate) {
		return isGreaterThan(toDate(date), toDate(then), onlyDate);
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isEqualThan(Date date, Date then, boolean onlyDate) {
		if (date != null && then != null) {
			Date newDate = toDate(date, onlyDate);
			Date newThen = toDate(then, onlyDate);
			return newDate.compareTo(newThen) == 0;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isNotEqualThan(Date date, Date then, boolean onlyDate) {
		return !isEqualThan(date, then, onlyDate);
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isSmallerOrEqualThan(Date date, Date then, boolean onlyDate) {
		if (date != null && then != null) {
			Date newDate = toDate(date, onlyDate);
			Date newThen = toDate(then, onlyDate);
			return newDate.compareTo(newThen) <= 0;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @param then
	 * @return
	 */
	public static boolean isSmallerThan(Date date, Date then, boolean onlyDate) {
		if (date != null && then != null) {
			Date newDate = toDate(date, onlyDate);
			Date newThen = toDate(then, onlyDate);
			return newDate.compareTo(newThen) < 0;
		}
		return false;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isGreaterOrEqualThanToday(Date date) {
		return isGreaterOrEqualThan(date, new Date(), true);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSmallerOrEqualThanToday(Date date) {
		return isSmallerOrEqualThan(date, new Date(), true);
	}

	/**
	 * Diferen�a em milisegundos de uma data em rela��o a outra.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Diferen�a em milisegundos.
	 */
	public static long diff(Date dataFinal, Date dataInicial) {
		Calendar cInicial = Calendar.getInstance();
		Calendar cFinal = Calendar.getInstance();

		cInicial.setTime(dataInicial);
		cFinal.setTime(dataFinal);

		int timeZoneDiff = cFinal.get(Calendar.DST_OFFSET) - cInicial.get(Calendar.DST_OFFSET);

		long lInicial = cInicial.getTimeInMillis();
		long lFinal = cFinal.getTimeInMillis() + timeZoneDiff;

		long diff = lFinal - lInicial;

		return diff;

	}

	/**
	 * Diferen�a em dias de uma data em rela��o a outra.
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return Diferen�a em dias.
	 */
	public static long diffDays(Date dataFinal, Date dataInicial) {
		long diff = diff(setMinHour(dataFinal), setMinHour(dataInicial)) / 86400000;
		return diff;
	}

	/**
	 * 
	 * @param time
	 * @param expire
	 *            quantidade de milisegundos.
	 * @return
	 */
	public static boolean isTimeToLive(Date time, long expire) {
		return time == null || DateUtils.diff(new Date(), time) > expire;
	}

	/**
	 * Preenche a hora (hour) na data (date) informada.
	 * 
	 * @param date
	 *            Data que tera a hora setada.
	 * @param hour
	 *            Hora que ser� setada na data.
	 * @param hourPattern
	 *            Formato da hora. Ex: hh:mm:ss
	 * @return Objeto date preenchido com data e hora. Caso o m�todo n�o consiga
	 *         entender a hora informada, este retornar� nulo.
	 */
	public static Date setHour(Date date, String hour, String hourPattern) {
		Date dateTime = null;
		if (date != null) {
			String dateAsString = format(date, "dd/MM/yyyy");
			dateTime = parse(dateAsString + " " + hour, "dd/MM/yyyy " + hourPattern);
		}
		return dateTime;
	}

	/**
	 * 
	 * @param date
	 * @param justDate
	 * @return
	 */
	private static Date toDate(Date date, boolean justDate) {
		Date d = date;
		if (justDate) {
			d = setHour(date, 0, 0, 0, 0);
		}
		return d;
	}

	/**
	 * 
	 * @return
	 */
	public static SimpleDateFormat getDefaultDateFormat() {
		return DEFAULTDATEFORMAT;
	}

	/**
	 * 
	 * @return
	 */
	public static SimpleDateFormat getDefaultDateTimeFormat() {
		return DEFAULTDATETIMEFORMAT;
	}

	/**
	 * 
	 */
	public static Date firstDayOfMonth(int month) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, month);
		date.set(Calendar.DAY_OF_MONTH, 1);
		return date.getTime();
	}

	/**
	 * 
	 */
	public static Date firstDayOfCurrentMonth() {
		Calendar date = Calendar.getInstance();
		return firstDayOfMonth(date.get(Calendar.MONTH));
	}

	/**
	 * 
	 */
	public static Date firstDayOfCurrentYear() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_YEAR, 1);
		return date.getTime();
	}

	/**
	 * Obt�m o �ltimo dia do m�s anterior ao corrente.
	 * 
	 * @return
	 */
	public static Date lastDayOfLastMonth() {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.add(Calendar.DAY_OF_MONTH, -1);
		return date.getTime();
	}

	public static Date firstDayOfLast12Months() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, -12);
		return calendar.getTime();
	}

	public static Date firstDayOfMonth(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date firstDayOfYear(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		return calendar.getTime();
	}

    public static Date firstDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

	public static Date lastDayOfMonth(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static int daysOfMonth(Date date) {
		Calendar calendar = toCalendar(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date lastDayOfYear(Date date) {
		Calendar calendar = toCalendar(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
		return calendar.getTime();
	}

	public static Date getNextWednesday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

		if (diaSemana >= Calendar.MONDAY && diaSemana <= Calendar.THURSDAY) {
			cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		} else if (diaSemana == Calendar.FRIDAY) {
			cal.add(Calendar.DAY_OF_WEEK, 5);
		} else if (diaSemana == Calendar.SATURDAY) {
			cal.add(Calendar.DAY_OF_WEEK, 4);
		} else {
			cal.add(Calendar.DAY_OF_WEEK, 3);
		}

		return cal.getTime();
	}

	public static int countDaysBetweenDates(Date dataInicial, Date dataFinal) {
		long differenceMilliSeconds = diff(dataFinal, dataInicial);
		return new Long(differenceMilliSeconds / 1000 / 60 / 60 / 24).intValue();
	}

	public static boolean validateFormat(String date) {
		Pattern padrao = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}");
		Matcher pesquisa = padrao.matcher(date);
		return pesquisa.matches();
	}

	public static boolean validateFormats(String date) {
		boolean result1 = false;
		boolean result2 = false;
		Pattern padrao1 = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}");
		Pattern padrao2 = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{2}");
		Matcher pesquisa1 = padrao1.matcher(date);
		if (pesquisa1.matches()) {
			result1 = true;
		}

		Matcher pesquisa2 = padrao2.matcher(date);
		if (pesquisa2.matches()) {
			result2 = true;
		}
		return result1 || result2;
	}

	public static Date toDate(Calendar calendar) {
		if (calendar != null) {
			return calendar.getTime();
		}
		return null;
	}

	public static Calendar toCalendar(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @param startThen
	 * @param endThen
	 * @param onlyDate
	 * @return
	 */
	public static boolean isBetweenThan(Date date, Date startThen, Date endThen, boolean onlyDate) {
		return isBetweenThan(date, startThen, endThen, onlyDate, true);
	}

	/**
	 * 
	 * @param date
	 * @param startThen
	 * @param endThen
	 * @param onlyDate
	 * @return
	 */
	public static boolean isBetweenThan(Date date, Date startThen, Date endThen, boolean onlyDate, boolean inclusive) {
		if (inclusive) {
			return isGreaterOrEqualThan(date, startThen, onlyDate) && isSmallerOrEqualThan(date, endThen, onlyDate);
		}
		return isGreaterThan(date, startThen, onlyDate) && isSmallerThan(date, endThen, onlyDate);
	}

	public static int compareTo(Date data1, Date date2) {

		int compareTo = 0;

		if (data1 != null && date2 != null) {

			compareTo = data1.compareTo(date2);

		} else if (data1 != null && date2 == null) {

			compareTo = -1;

		} else if (data1 == null && date2 != null) {

			compareTo = 1;
		}

		return compareTo;

	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static boolean isSameMonthAndYear(Calendar date1, Calendar date2) {
		return date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) && date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR);
	}

	public static boolean isSameMonthAndYear(Date date1, Date date2) {
		return isSameMonthAndYear(toCalendar(date1), toCalendar(date2));
	}

	public static String replateDateWith4YearsFor2YearsDigits(String text) {
		String newText = StringUtils.toString(text).trim();
		Matcher m = DATE_PATTERN_FINDER.matcher(newText);
		if (m != null) {
			while (m.find()) {
				String oldDate = StringUtils.toString(m.group());
				String newDate = oldDate.replace("/19", "/");
				newDate = newDate.replace("/20", "/");
				newText = newText.replaceAll(oldDate, newDate);
			}
		}
		return newText;
	}

	/**
	 * Adiciona mes(es) a data informada.
	 * 
	 * @param date
	 *            Data que ter� a o m�s alterado.
	 * @param months
	 *            Quantidade de meses que ser� adicionada a data informada.
	 * @return
	 */
	public static Date dateMoreYears(Date date, int years) {
		if (date != null && years != 0) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, years);
			return cal.getTime();
		}
		return date;
	}

}
