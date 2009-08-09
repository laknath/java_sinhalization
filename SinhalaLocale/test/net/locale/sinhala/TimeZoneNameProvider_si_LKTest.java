/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.util.Locale;
import java.util.TimeZone;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author buddhika
 */
public class TimeZoneNameProvider_si_LKTest {

    Locale locale;

    public TimeZoneNameProvider_si_LKTest() {
        this.locale = new Locale("si", "LK");
    }

    /**
     * Test of TimeZone.getLocaleData method, of class TimeZoneNameProvider_si_LK.
     */
    @Test
    public void testGetDisplayName() {
        System.out.println("getDisplayName");
        TimeZoneNameProvider_si_LK timeZoneProvider = new TimeZoneNameProvider_si_LK();

        String result = timeZoneProvider.getDisplayName("Asia/Colombo",true,TimeZone.SHORT,locale);
        String expResult = "LKT";

        assertEquals(expResult, result);

        result = timeZoneProvider.getDisplayName("Asia/Colombo",true,TimeZone.LONG,locale);
        expResult = "Sri Lanka Standard Time";

        assertEquals(expResult, result);

    }

}