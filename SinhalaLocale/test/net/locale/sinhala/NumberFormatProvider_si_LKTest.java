/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.text.NumberFormat;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author buddhika
 */
public class NumberFormatProvider_si_LKTest {

    Locale locale;

    public NumberFormatProvider_si_LKTest() {
        this.locale = new Locale("si", "LK");
    }


    /**
     * Test of getCurrencyInstance method, of class NumberFormatProvider_si_LK.
     */
    @Test
    public void testGetCurrencyInstance() {
        System.out.println("gsetCurrencyInstance");
        NumberFormatProvider_si_LK numberFormatProvider = new NumberFormatProvider_si_LK();

        NumberFormat nf = numberFormatProvider.getCurrencyInstance(locale);
        String result = nf.format(100);
        String expResult = "Rs 100.00";

        assertEquals(expResult, result);
    }

    /**
     * Test of getIntegerInstance method, of class NumberFormatProvider_si_LK.
     */
    @Test
    public void testGetIntegerInstance() {
        System.out.println("getIntegerInstance");
        NumberFormatProvider_si_LK numberFormatProvider = new NumberFormatProvider_si_LK();

        NumberFormat nf = numberFormatProvider.getIntegerInstance(locale);
        String result = nf.format(100000);
        String expResult = "100,000";

        assertEquals(expResult, result);

    }

    /**
     * Test of getNumberInstance method, of class NumberFormatProvider_si_LK.
     */
    @Test
    public void testGetNumberInstance() {
        System.out.println("getNumberInstance");
        NumberFormatProvider_si_LK numberFormatProvider = new NumberFormatProvider_si_LK();

        NumberFormat nf =  numberFormatProvider.getNumberInstance(locale);
        String result = nf.format(100000);
        String expResult = "100,000";

        assertEquals(expResult, result);
    }

    /**
     * Test of getPercentInstance method, of class NumberFormatProvider_si_LK.
     */
    @Test
    public void testGetPercentInstance() {
        System.out.println("getPercentInstance");
        NumberFormatProvider_si_LK numberFormatProvider = new NumberFormatProvider_si_LK();

        NumberFormat nf = numberFormatProvider.getPercentInstance(locale);
        String result = nf.format(0.8);
        String expResult = "80%";

        assertEquals(expResult, result);
    }

}