/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.util.Locale;
import java.util.spi.CurrencyNameProvider;

/**
 *
 * @author Buddhika Laknath
 */
public abstract class LocaleCurrencyNameProvider extends CurrencyNameProvider{

    public abstract LocaleInfo getLocaleData();    
    
    @Override
    public String getSymbol(String currencyCode, Locale locale) {
	getLocaleData().checkLocaleSupported(locale);        
        
        return getLocaleData().getCurrencySymbol();
    }

    @Override
    public Locale[] getAvailableLocales() {
	return getLocaleData().getSupportedLocales();
    }

}
