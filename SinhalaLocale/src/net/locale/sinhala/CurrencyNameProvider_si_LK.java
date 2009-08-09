/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import net.locale.LocaleCurrencyNameProvider;
import net.locale.LocaleInfo;

/**
 *
 * @author Buddhika Laknath
 */
public class CurrencyNameProvider_si_LK extends LocaleCurrencyNameProvider{

    static final private LocaleInfo localeData = new Sinhala();    
    
    @Override
    public LocaleInfo getLocaleData() {
        return localeData;
    }

}
