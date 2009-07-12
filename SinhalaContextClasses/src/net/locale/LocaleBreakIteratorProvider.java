/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.text.BreakIterator;
import java.text.spi.BreakIteratorProvider;
import java.util.Locale;


/**
 *
 * @author Buddhika Laknath
 */
public abstract class LocaleBreakIteratorProvider extends BreakIteratorProvider{

    public abstract LocaleInfo getLocaleData();

    @Override
    public BreakIterator getWordInstance(Locale arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BreakIterator getLineInstance(Locale arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BreakIterator getCharacterInstance(Locale arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BreakIterator getSentenceInstance(Locale arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Locale[] getAvailableLocales() {
        return getLocaleData().getSupportedLocales();
    }



}
