package net.locale;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.spi.NumberFormatProvider;
import java.util.Locale;

public abstract class LocaleNumberFormatProvider extends NumberFormatProvider {


	public abstract LocaleInfo getLocaleData();


	@Override
	public NumberFormat getCurrencyInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		DecimalFormat currencyInstance = new DecimalFormat(getLocaleData().getCurrencyFormat(),
				new LocaleDecimalFormatSymbols(getLocaleData()));
		currencyInstance.setRoundingMode(RoundingMode.HALF_UP);

		return currencyInstance;
	}


	@Override
	public NumberFormat getIntegerInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		DecimalFormat integerInstance = new DecimalFormat(getLocaleData().getIntegerFormat(),
				new LocaleDecimalFormatSymbols(getLocaleData()));

		// following settings are required by contract for this method
		integerInstance.setParseIntegerOnly(true);
		integerInstance.setRoundingMode(RoundingMode.HALF_EVEN);

		return integerInstance;
	}


	@Override
	public NumberFormat getNumberInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		DecimalFormat instance = new DecimalFormat(getLocaleData().getNumberFormat(), new LocaleDecimalFormatSymbols(
				getLocaleData()));
		instance.setRoundingMode(RoundingMode.HALF_UP);

		return instance;
	}


	@Override
	public NumberFormat getPercentInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		DecimalFormat percentInstance = new DecimalFormat(getLocaleData().getPercentFormat(),
				new LocaleDecimalFormatSymbols(getLocaleData()));
		percentInstance.setRoundingMode(RoundingMode.HALF_UP);
        
		return percentInstance;
	}


	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

}
