
/*
 * SimpleSihalaInputMethodDescriptor.java
 */

package com.sun.inputmethods.internal.sinhalaim;

import java.awt.Image;
import java.awt.im.spi.InputMethodDescriptor;
import java.awt.im.spi.InputMethod;
import java.util.Locale;

/*
 * SimpleSihalaInputMethodDescriptor is the discriptor used by the InputMethod SPF
 */
public class SimpleSinhalaInputMethodDescriptor implements InputMethodDescriptor {

    /**
     * Creates a new instance of the Simple Sihala Input Method.
     *
     * @return a new instance of the Simple Sihala Input Method
     * @exception Exception any exception that may occur while creating the
     * input method instance
     */
    public InputMethod createInputMethod() throws Exception {
        return new SimpleSinhalaInputMethod();
    }

    /**
     * This input method can be used by any locale.
     */
    public Locale[] getAvailableLocales() {
        Locale[] locales = {
            new Locale("si","LK"),
        };
        return locales;
    }

    public synchronized String getInputMethodDisplayName(Locale inputLocale, Locale displayLanguage) {
        return "Simple Sinhala Input Method";
    }

    public Image getInputMethodIcon(Locale inputLocale) {
        return null;
    }

    public boolean hasDynamicLocaleList() {
        return false;
    }
}
