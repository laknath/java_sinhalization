/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class DateFormatSymbolsProvider_si_LKTest {

    Locale locale;
    Date date;
    Calendar cal;
    
    public DateFormatSymbolsProvider_si_LKTest() {

        this.locale = new Locale("si", "LK");
        this.cal = Calendar.getInstance();

        //2009 August 9, 10:25
        cal.set(2009, 7, 9, 10, 36, 6);
        date = cal.getTime();

    }

    /**
     * Test of getEras method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetEras() {
        System.out.println("getEras");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String[] erasResult = dfs.getEras();
        String[] expResult = { "ක්‍රි.පූ." , "ක්‍රි.ව." };
        assertArrayEquals(expResult, erasResult);

    }

    /**
     * Test of getAmPmString method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetAmPmString() {
        System.out.println("getAmPmString");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String[] amPmResult = dfs.getAmPmStrings();
        String[] expResult = { "පෙ.ව.", "ප.ව." };
        assertArrayEquals(expResult, amPmResult);

    }

    /**
     * Test of getMonths method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetMonths() {
        System.out.println("getMonths");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String[] monthsResult = dfs.getMonths();
        String[] monthsExpResult = { "ජනවාරි",
                                     "පෙබරවාරි",
                                     "මාර්තු",
                                     "අප්‍රේල්",
                                     "මැයි",
                                     "ජූනි",
                                     "ජූලි",
                                     "අගෝස්තු",
                                     "සැප්තැම්බර්",
                                     "ඔක්තෝබර්",
                                     "නොවැම්බර්",
                                     "දෙසැම්බර්"
                                   };
        assertArrayEquals(monthsExpResult, monthsResult);
    }

    /**
     * Test of getShortMonths method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetShortMonths() {
        System.out.println("getShortMonths");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String[] shortMonthsResult = dfs.getShortMonths();
        String[] shortMonthsExpResult = {
                                            "ජන",
                                            "පෙබ",
                                            "මාර්තු",
                                            "අප්‍රේල්",
                                            "මැයි",
                                            "ජූනි",
                                            "ජූලි",
                                            "අගෝ",
                                            "සැප්",
                                            "ඔක්",
                                            "නොවැ",
                                            "දෙසැ"
                                         };
        
        assertArrayEquals(shortMonthsExpResult, shortMonthsResult);
    }

    /**
     * Test of getWeeks method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetWeeks() {
        System.out.println("getWeeks");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);


        String[] weekResult = dfs.getWeekdays();
        String[] weekExpResult = { "",
                                    "\u0d89\u0dbb\u0dd2\u0daf\u0dcf",
                                    "\u0dc3\u0daf\u0dd4\u0daf",
                                    "\u0d85\u0d9c\u0dc4\u0dbb\u0dd4\u0dc0\u0daf\u0dcf",
                                    "\u0db6\u0daf\u0dcf\u0daf\u0dcf",
                                    "\u0db6\u0dca\u200d\u0dbb\u0dc4\u0dc3\u0dca\u0db4\u0dad\u0dd2\u0db1\u0dca\u0daf\u0dcf",
                                    "\u0dc3\u0dd2\u0d9a\u0dd4\u0dbb\u0dcf\u0daf\u0dcf",
                                    "\u0dc3\u0dd9\u0db1\u0dc3\u0dd4\u0dbb\u0dcf\u0daf\u0dcf",
                                };
        assertArrayEquals(weekResult, weekExpResult);
    }

    /**
     * Test of getShortWeeks method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetShortWeeks() {
        System.out.println("getShortWeeks");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String[] shortWeekResult = dfs.getShortWeekdays();
        String[] shortweekExpResult =   {
                                        "",
                                        "ඉ",
                                        "ස",
                                        "අ",
                                        "බ",
                                        "බ්‍ර",
                                        "සි",
                                        "සෙ"
                                        };
        assertArrayEquals(shortweekExpResult, shortWeekResult);
    }

    /**
     * Test of getLocalPatternChars method, of class DateFormatSymbolsProvider_si_LK.
     */
    @Test
    public void testGetLocalPatternChars() {
        System.out.println("getLocalPatternChars");
        DateFormatSymbolsProvider_si_LK instance = new DateFormatSymbolsProvider_si_LK();
        DateFormatSymbols dfs = instance.getInstance(locale);

        String localPatternChars = dfs.getLocalPatternChars();
        String localPatternCharsExpResult = "GyMdkHmsSEDFwWahKzZ";
        assertEquals(localPatternCharsExpResult, localPatternChars);
    }

}