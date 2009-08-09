/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.util.Currency;
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

        if (currencyCode.equals("LKR")){
            return getLocaleData().getCurrencySymbol();
        }else{
            return Currency.getInstance(locale).getSymbol(locale);
        }
        
    }

    @Override
    public Locale[] getAvailableLocales() {
        return getLocaleData().getSupportedLocales();
    }

}
