/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.text.BreakIterator;
import java.text.CharacterIterator;


/**
 *
 * @author Buddhika Laknath
 */
public abstract class LocaleBreakIteratorProvider extends BreakIterator{

    public abstract LocaleInfo getLocaleData();    
    
    @Override
    public int first() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int last() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int next(int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int previous() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int following(int offset) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int current() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CharacterIterator getText() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setText(CharacterIterator newText) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
