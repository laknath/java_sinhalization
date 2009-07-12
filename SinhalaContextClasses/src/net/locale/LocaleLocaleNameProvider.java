package net.locale;
import java.util.Locale;
import java.util.spi.LocaleNameProvider;

public abstract class LocaleLocaleNameProvider extends LocaleNameProvider {

	public abstract LocaleInfo getLocaleData();


	@Override
	public String getDisplayCountry(String countryCode, Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		if (badCountryCodeFormat(countryCode)) {
			throw new IllegalArgumentException("country code is not in the required format");
		}

		for (String[] tuple : getLocaleData().getCountryTuples()) {
			String key = tuple[0];
			String value = tuple[1];

			if (key.equals(countryCode)) {
				return value;
			}
		}

		return null;
	}

	@Override
	public String getDisplayLanguage(String languageCode, Locale locale) {
		getLocaleData().checkLocaleSupported(locale);

		if (badLanguageCodeFormat(languageCode)) {
			throw new IllegalArgumentException("language code is not in the required format");
		}

		for (String[] tuple : getLocaleData().getLanguageTuples()) {
			String key = tuple[0];
			String value = tuple[1];

			if (key.equals(languageCode)) {
				return value;
			}
		}

		return null;
	}

	@Override
	public String getDisplayVariant(String variant, Locale locale) {
		return null;
	}

	@Override
	public Locale[] getAvailableLocales() {
		return getLocaleData().getSupportedLocales();
	}

	private boolean badCountryCodeFormat(String countryCode) {
		return countryCode.length() != 2 || Character.isLowerCase(countryCode.charAt(0))
				|| Character.isLowerCase(countryCode.charAt(1));
	}
    
	private boolean badLanguageCodeFormat(String languageCode) {
		return languageCode.length() != 2 || Character.isUpperCase(languageCode.charAt(0))
				|| Character.isUpperCase(languageCode.charAt(1));
	}

}
