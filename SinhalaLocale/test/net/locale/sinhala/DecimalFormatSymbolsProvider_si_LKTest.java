/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class DecimalFormatSymbolsProvider_si_LKTest {

    Locale locale;
    DecimalFormatSymbols decimalFormatSymbols;

    public DecimalFormatSymbolsProvider_si_LKTest() {
        this.locale = new Locale("si", "LK");

        DecimalFormatSymbolsProvider_si_LK decimalFormatSymbolsProvider = new DecimalFormatSymbolsProvider_si_LK();
        decimalFormatSymbols = decimalFormatSymbolsProvider.getInstance(locale);
    }

    /**
     * Test of getLocaleData method, of class DecimalFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetLocaleData() {

        System.out.println("getLocaleData");

        //assertEquals("LKR", decimalFormatSymbols.getInternationalCurrencySymbol());
        assertEquals("Rs", decimalFormatSymbols.getCurrencySymbol());
        assertEquals('.', decimalFormatSymbols.getDecimalSeparator());
        assertEquals('#', decimalFormatSymbols.getDigit());
        assertEquals("E", decimalFormatSymbols.getExponentSeparator());
        assertEquals(',', decimalFormatSymbols.getGroupingSeparator());
        assertEquals("∞", decimalFormatSymbols.getInfinity());
        assertEquals("NaN", decimalFormatSymbols.getNaN());
        assertEquals(';', decimalFormatSymbols.getPatternSeparator());
        assertEquals('‰', decimalFormatSymbols.getPerMill());
        assertEquals('%', decimalFormatSymbols.getPercent());
        assertEquals('0', decimalFormatSymbols.getZeroDigit());
    }

}