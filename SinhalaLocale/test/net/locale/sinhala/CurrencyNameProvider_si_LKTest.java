/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import net.locale.sinhala.*;
import java.util.Locale;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class CurrencyNameProvider_si_LKTest {

    Locale locale;

    public CurrencyNameProvider_si_LKTest() {

        this.locale = new Locale("si", "LK");

    }

    /**
     * Test of getSymbol method, of class CurrencyNameProvider_si_LK.
     */
    @Test
    public void testGetSymbol() {
        System.out.println("GetSymbol");
        CurrencyNameProvider_si_LK instance = new CurrencyNameProvider_si_LK();
        String expResult = "Rs";
        String result = instance.getSymbol("LKR", locale);
        assertEquals(expResult, result);

    }

}