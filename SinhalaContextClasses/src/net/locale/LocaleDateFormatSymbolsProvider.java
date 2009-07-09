package net.locale;

import java.text.DateFormatSymbols;
import java.text.spi.DateFormatSymbolsProvider;
import java.util.Locale;

public abstract class LocaleDateFormatSymbolsProvider extends DateFormatSymbolsProvider {

	public abstract LocaleInfo getLocaleData();

	@Override
	public DateFormatSymbols getInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		return new LocaleDateFormatSymbols(getLocaleData());
	}

	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

}
