/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;


import net.locale.LocaleInfo;
import net.locale.LocaleDateFormatSymbolsProvider;

/**
 *
 * @author Buddhika Laknath
 */
public class DateFormatSymbolsProvider_si_LK extends LocaleDateFormatSymbolsProvider {

    static final private LocaleInfo localeData = new Sinhala();

    @Override
    public LocaleInfo getLocaleData() {
	return localeData;
    }
}
