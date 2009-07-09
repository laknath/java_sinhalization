package net.locale;

import java.text.DecimalFormatSymbols;

public class LocaleDecimalFormatSymbols extends DecimalFormatSymbols {
	private static final long serialVersionUID = 1L;
	private final LocaleInfo localeInfo;

	public LocaleDecimalFormatSymbols(LocaleInfo localeInfo) {
		this.localeInfo = localeInfo;
		initializeFields();
	}

	protected void initializeFields() {
		setDecimalSeparator(localeInfo.getDecimalSeparator());
		setGroupingSeparator(localeInfo.getGroupingSeparator());
		setPatternSeparator(localeInfo.getPatternSeparator());
		setPercent(localeInfo.getPercent());
		setZeroDigit(localeInfo.getZeroDigit());
		setDigit(localeInfo.getDigit());
		setMinusSign(localeInfo.getMinusSign());
		setExponentSeparator(localeInfo.getExponentSeparator());
		setPerMill(localeInfo.getPerMille());
		setInfinity(localeInfo.getInfinity());
		setNaN(localeInfo.getNaN());
		setCurrencySymbol(localeInfo.getCurrencySymbol());
	}

}
