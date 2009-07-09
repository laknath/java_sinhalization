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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Locale[] getAvailableLocales() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
