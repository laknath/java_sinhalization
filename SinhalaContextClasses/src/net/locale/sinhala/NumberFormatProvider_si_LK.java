/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import net.locale.LocaleInfo;
import net.locale.LocaleNumberFormatProvider;

/**
 *
 * @author Buddhika Laknath
 */
public class NumberFormatProvider_si_LK extends LocaleNumberFormatProvider {

    static final private LocaleInfo localeInfo = new Sinhala();

    @Override
    public LocaleInfo getLocaleData() {
    	return localeInfo;
    }
}
