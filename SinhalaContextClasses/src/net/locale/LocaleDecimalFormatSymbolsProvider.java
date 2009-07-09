package net.locale;

import java.text.DecimalFormatSymbols;
import java.text.spi.DecimalFormatSymbolsProvider;
import java.util.Locale;

public abstract class LocaleDecimalFormatSymbolsProvider extends DecimalFormatSymbolsProvider {


	public abstract LocaleInfo getLocaleData();


	@Override
	public DecimalFormatSymbols getInstance(Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		return new LocaleDecimalFormatSymbols(getLocaleData());
	}


	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

}
