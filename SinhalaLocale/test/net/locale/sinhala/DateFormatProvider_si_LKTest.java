/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class DateFormatProvider_si_LKTest {

    Locale locale;
    Date date;
    Calendar cal;

    public DateFormatProvider_si_LKTest() {
        this.locale = new Locale("si", "LK");
        this.cal = Calendar.getInstance();

        //2009 August 9, 10:25
        cal.set(2009, 7, 9, 10, 36, 6);
        date = cal.getTime();
    }


    /**
     * Test of getDateInstance method, of class CurrencyNameProvider_si_LK.
     */
    @Test
    public void testGetDateInstance() {
        System.out.println("getDateInstance");
        DateFormatProvider_si_LK instance = new DateFormatProvider_si_LK();

        String shortDateResult = instance.getDateInstance(DateFormat.SHORT, locale).format(date);
        String expResult = "09-08-09";
        assertEquals(expResult, shortDateResult);

        String mediumDateResult = instance.getDateInstance(DateFormat.MEDIUM, locale).format(date);
        expResult = "2009/08/09";
        assertEquals(expResult, mediumDateResult);

        String longDateResult = instance.getDateInstance(DateFormat.LONG, locale).format(date);
        expResult = "2009 අගෝස්තු 09";
        assertEquals(expResult, longDateResult);

        String fullDateResult = instance.getDateInstance(DateFormat.FULL, locale).format(date);
        expResult = "2009 අගෝස්තු මස 09 වැනිදා ඉරිදා";
        assertEquals(expResult, fullDateResult);

    }


    /**
     * Test of getTimeInstance method, of class CurrencyNameProvider_si_LK.
     */
    @Test
    public void testGetTimeInstance() {
        System.out.println("getTimeInstance");
        DateFormatProvider_si_LK instance = new DateFormatProvider_si_LK();

        String shortTimeResult = instance.getTimeInstance(DateFormat.SHORT, locale).format(date);
        String expResult = "10.36";
        assertEquals(expResult, shortTimeResult);

        String mediumTimeResult = instance.getTimeInstance(DateFormat.MEDIUM, locale).format(date);
        expResult = "10.36:06";
        assertEquals(expResult, mediumTimeResult);

        String longTimeResult = instance.getTimeInstance(DateFormat.LONG, locale).format(date);
        expResult = "පෙ.ව. 10.36:06";
        assertEquals(expResult, longTimeResult);

        String fullTimeResult = instance.getTimeInstance(DateFormat.FULL, locale).format(date);
        expResult = "පෙ.ව. 10.36:06 +0530";
        assertEquals(expResult, fullTimeResult);

    }
    
    /**
     * Test of getDateTimeInstance method, of class CurrencyNameProvider_si_LK.
     */
    @Test
    public void testGetDateTimeInstance() {
        System.out.println("getTimeInstance");
        DateFormatProvider_si_LK instance = new DateFormatProvider_si_LK();

        String shortDateTimeResult = instance.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale).format(date);
        String expResult = "09-08-09 10.36";
        assertEquals(expResult, shortDateTimeResult);

        String mediumDateTimeResult = instance.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale).format(date);
        expResult = "2009/08/09 10.36:06";
        assertEquals(expResult, mediumDateTimeResult);

        String longDateTimeResult = instance.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale).format(date);
        expResult = "2009 අගෝස්තු 09 පෙ.ව. 10.36:06";
        assertEquals(expResult, longDateTimeResult);

        String fullDateTimeResult = instance.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale).format(date);
        expResult = "2009 අගෝස්තු මස 09 වැනිදා ඉරිදා පෙ.ව. 10.36:06 +0530";
        assertEquals(expResult, fullDateTimeResult);

    }

}