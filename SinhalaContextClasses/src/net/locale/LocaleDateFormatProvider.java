package net.locale;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Locale;

public abstract class LocaleDateFormatProvider extends DateFormatProvider {

	public abstract LocaleInfo getLocaleData();


	@Override
	public DateFormat getDateInstance(int style, Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		String format = selectDateFormatForStyle(style);
		return new SimpleDateFormat(format, new LocaleDateFormatSymbols(getLocaleData()));
	}

	@Override
	public DateFormat getDateTimeInstance(int dateStyle, int timeStyle, Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		String format = selectDateFormatForStyle(dateStyle) + " " + selectTimeFormatForStyle(timeStyle);
		return new SimpleDateFormat(format, new LocaleDateFormatSymbols(getLocaleData()));
	}

	@Override
	public DateFormat getTimeInstance(int style, Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		String format = selectTimeFormatForStyle(style);
		return new SimpleDateFormat(format, new LocaleDateFormatSymbols(getLocaleData()));
	}

	private String selectDateFormatForStyle(int style) {
		String format = null;
		switch (style) {
		case DateFormat.FULL:
			format = getLocaleData().getDateFull();
			break;
		case DateFormat.LONG:
			format = getLocaleData().getDateLong();
			break;
		case DateFormat.MEDIUM:
			format = getLocaleData().getDateMedium();
			break;
		case DateFormat.SHORT:
			format = getLocaleData().getDateShort();
			break;
		default:
			throw new IllegalArgumentException("unsupported style " + style);
		}
		return format;
	}

	private String selectTimeFormatForStyle(int style) {
		String format = null;
		switch (style) {
		case DateFormat.FULL:
			format = getLocaleData().getTimeFull();
			break;
		case DateFormat.LONG:
			format = getLocaleData().getTimeLong();
			break;
		case DateFormat.MEDIUM:
			format = getLocaleData().getTimeMedium();
			break;
		case DateFormat.SHORT:
			format = getLocaleData().getTimeShort();
			break;
		default:
			throw new IllegalArgumentException("unsupported style " + style);
		}
		return format;
	}


	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

}
