package net.locale;

import java.text.Collator;
import java.text.spi.CollatorProvider;
import java.util.Locale;

public abstract class LocaleCollatorProvider extends CollatorProvider {

	public abstract LocaleInfo getLocaleData();


	@Override
	public Collator getInstance(Locale locale) {
            getLocaleData().checkLocaleSupported(locale);

            return getLocaleData().getCollator();

	}


	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

}
