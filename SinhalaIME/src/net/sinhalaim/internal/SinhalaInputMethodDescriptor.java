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

public class SinhalaInputMethodDescriptor implements InputMethodDescriptor {

    public SinhalaInputMethodDescriptor() {
    }

    /**
     * @see java.awt.im.spi.InputMethodDescriptor#getAvailableLocales
     */
    public Locale[] getAvailableLocales() {
        Locale[] locales = {Locale.ENGLISH};
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
        }
        
        if (localeName != null) {
            return "Sinhala Phonetic Input Method - in " + localeName;
        } else {
            return "Sinhala Phonetic Input Method";
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
        return new SinhalaInputMethod();
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
