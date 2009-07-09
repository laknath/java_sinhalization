/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;


import net.locale.LocaleInfo;
import net.locale.LocaleDateFormatProvider;


/**
 *
 * @author Buddhika Laknath
 */
public class DateFormatProvider_si_LK extends LocaleDateFormatProvider {

    static final private LocaleInfo localeData = new Sinhala();

    @Override
    public LocaleInfo getLocaleData() {
	return localeData;
    }
}
