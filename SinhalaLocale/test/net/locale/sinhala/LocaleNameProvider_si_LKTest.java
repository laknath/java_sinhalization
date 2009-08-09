/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class LocaleNameProvider_si_LKTest {

    Locale locale;

    public LocaleNameProvider_si_LKTest() {
        this.locale = new Locale("si", "LK");
    }

    /**
     * Test of getDisplayCountry method, of class LocaleNameProvider_si_LK.
     */
    @Test
    public void testGetDisplayCountry() {
        System.out.println("getDisplayCountry");
        LocaleNameProvider_si_LK localeNameProvider = new LocaleNameProvider_si_LK();

        String result = localeNameProvider.getDisplayCountry("LK", locale);
        String expResult = "ශ්‍රි ලංකාව";

        assertEquals(expResult, result);
    }

    /**
     * Test of getDisplayLanguage method, of class LocaleNameProvider_si_LK.
     */
    @Test
    public void testGetDisplayLanguage() {
        System.out.println("getDisplayLanguage");
        LocaleNameProvider_si_LK localeNameProvider = new LocaleNameProvider_si_LK();

        String result = localeNameProvider.getDisplayLanguage("si", locale);
        String expResult = "සිංහල";

        assertEquals(expResult, result);
    }


}