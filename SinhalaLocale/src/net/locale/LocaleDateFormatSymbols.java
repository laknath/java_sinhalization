package net.locale;

import java.text.DateFormatSymbols;

public class LocaleDateFormatSymbols extends DateFormatSymbols {
	private static final long serialVersionUID = 1L;
	private final LocaleInfo localeInfo;

	public LocaleDateFormatSymbols(LocaleInfo localeInfo) {
		this.localeInfo = localeInfo;
		initializeFields();
	}

	protected void initializeFields() {
		setEras(localeInfo.getEras());
		setLocalPatternChars(localeInfo.getPatternChars());
		setMonths(localeInfo.getMonths());
		setShortMonths(localeInfo.getShortMonths());
		setWeekdays(localeInfo.getWeekdays());
		setShortWeekdays(localeInfo.getShortWeekdays());
        setAmPmStrings(localeInfo.getAmPmStrings());
	}

}
