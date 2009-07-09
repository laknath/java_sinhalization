/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import net.locale.LocaleInfo;
import net.locale.LocaleTimeZoneNameProvider;



/**
 *
 * @author Buddhika Laknath
 */
public class TimeZoneNameProvider_si_LK extends LocaleTimeZoneNameProvider{

    static final private LocaleInfo localeInfo = new Sinhala();    
    
    @Override
    public LocaleInfo getLocaleData() {
    	return localeInfo;
    }

}
