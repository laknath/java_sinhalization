/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.util.Locale;
import java.util.spi.TimeZoneNameProvider;

/**
 *
 * @author Buddhika Laknath
 */
public abstract class LocaleTimeZoneNameProvider extends TimeZoneNameProvider{

    public abstract LocaleInfo getLocaleData();    
    
    @Override
    public String getDisplayName(String ID, boolean daylight, int style, Locale locale) {
	
        LocaleInfo localeInfo = getLocaleData();
        localeInfo.checkLocaleSupported(locale);       
        
        return localeInfo.getTimeZone();
    }

    @Override
    public Locale[] getAvailableLocales() {
        return getLocaleData().getSupportedLocales();
    }

}
