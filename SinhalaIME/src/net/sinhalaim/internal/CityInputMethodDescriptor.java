/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sinhalaim.internal;

/**
 *
 * @author buddhika
 */

import java.awt.Image;
import java.awt.im.spi.InputMethod;
import java.awt.im.spi.InputMethodDescriptor;
import java.util.Locale;

/**
 * Provides sufficient information about an input method
 * to enable selection and loading of that input method.
 * The input method itself is only loaded when it is actually used.
 */

public class CityInputMethodDescriptor implements InputMethodDescriptor {

    private static Locale YOMI = new Locale("ja", "JP", "YOMI");

    public CityInputMethodDescriptor() {
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#getAvailableLocales
     */
    public Locale[] getAvailableLocales() {
        Locale[] locales = {Locale.ENGLISH,
                            Locale.GERMAN,
                            Locale.JAPANESE,
                            YOMI,
                            Locale.SIMPLIFIED_CHINESE,
                            Locale.TRADITIONAL_CHINESE};
        return locales;
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#hasDynamicLocaleList
     */
    public boolean hasDynamicLocaleList() {
        return false;
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#getInputMethodDisplayName
     */
    public synchronized String getInputMethodDisplayName(Locale inputLocale, Locale displayLanguage) {
        String localeName = null;
        if (inputLocale == Locale.ENGLISH) {
            localeName = "English";
        } else if (inputLocale == Locale.GERMAN) {
            localeName = "German";
        } else if (inputLocale == Locale.JAPANESE) {
            localeName = "Japanese";
        } else if (inputLocale == YOMI) {
            localeName = "Japanese Reading";
        } else if (inputLocale == Locale.SIMPLIFIED_CHINESE) {
            localeName = "Simplified Chinese";
        } else if (inputLocale == Locale.TRADITIONAL_CHINESE) {
            localeName = "Traditional Chinese";
        }
        if (localeName != null) {
            return "City Input Method - " + localeName;
        } else {
            return "City Input Method";
        }
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#getInputMethodIcon
     */
    public Image getInputMethodIcon(Locale inputLocale) {
        return null;
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#createInputMethod
     */
    public InputMethod createInputMethod() throws Exception {
        return new CityInputMethod();
    }

    public String toString() {
	Locale loc[] = getAvailableLocales();
	String locnames = null;

	for (int i = 0; i < loc.length; i++) {
	    if (locnames == null) {
		locnames = loc[i].toString();
	    } else {
		locnames += "," + loc[i];
	    }
	}
	return getClass().getName() + "[" +
	    "locales=" + locnames +
	    ",localelist=" + (hasDynamicLocaleList() ? "dynamic" : "static") +
	    "]";
    }
}
