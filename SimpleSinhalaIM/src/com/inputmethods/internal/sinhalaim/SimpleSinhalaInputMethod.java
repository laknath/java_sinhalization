/*
 * SimpleSihalaInputMethod.java
 */

package com.inputmethods.internal.sinhalaim;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.awt.font.TextHitInfo;
import java.awt.im.InputMethodHighlight;
import java.awt.im.spi.InputMethod;
import java.awt.im.spi.InputMethodContext;
import java.text.AttributedString;
import java.util.Locale;
import java.util.Vector;

/*
 * SimpleSihalaInputMethod is a phonetic English to Sinhala IM
 * that allows to enter Sinhala fonts easiy in English
 */
public class SimpleSinhalaInputMethod implements InputMethod {

    private static final int UNSET           = 0;
    private static final int ESCAPE          = 1; 
    private static final int SPECIAL_ESCAPE  = 2; 
    private static final int SURROGATE_PAIR  = 3; 

    private InputMethodContext context;
    private Locale locale;
    private StringBuffer buffer;
    private int insertionPoint;
    private int format = UNSET;

    //sinhala vovel settings
    private Vector<String> stack = new Vector<String>();
    private int nVowels=26;
    private String[] consonants= new String[46];
    private String[] consonantsUni= new String[46];
    private String[] vowels= new String[26];
    private String[] vowelsUni= new String[26];
    private String[] vowelModifiersUni= new String[26];
    private String[] specialConsonants= new String[6];
    private String[] specialConsonantsUni= new String[6];
    private String[] specialCharUni= new String[3];
    private String[] specialChar= new String[3];

    /**
     * This is the input method's main routine.  The composed text is stored
     * in buffer.
     */
    public void dispatchEvent(AWTEvent event) {
        // This input method handles KeyEvent only.
        if (!(event instanceof KeyEvent)) {
            return;
        }

        KeyEvent e = (KeyEvent) event;
        int eventID = event.getID();
        boolean notInCompositionMode = buffer.length() == 0;

        if (eventID == KeyEvent.KEY_PRESSED) {
            
            if (notInCompositionMode)  {
                return;
            }

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    moveCaretLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveCaretRight();
                    break;
            }
        } else if (eventID == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();

            // If not in composition mode
            if (notInCompositionMode)  {

                //Start composition mode
                startComposition(c);
                
            } else {
                switch (c) { 
                    case ' ':
                        finishComposition();
                        break;
                    case '\u007f':	// Delete
                        deleteCharacter();
                        break;
                    case '\b':	// BackSpace
                        deletePreviousCharacter();
                        break;
                    case '\u001b':	// Escape
                        cancelComposition();
                        break;
                    case '\n':	// Return
                    case '\t':	// Tab
                        sendCommittedText(buffer);
                        break;
                    default:
                        translateToSinhala(c);
                        break;
                    }
            }
        } else {
            // If we are not in composition mode
            if (notInCompositionMode)  {
                return;
            }
        }

        e.consume();
    }

    private void translateToSinhala(char c) {

        addCharacter(c);
        StringBuffer sinhalaBuf = new StringBuffer(translate(buffer.toString()));
        sendComposedText(sinhalaBuf);

    }

    private void addCharacter(char c) {
        buffer.insert(insertionPoint++, c);
    }

    /**
     * Send the composed text to the client.
     */
    private void sendComposedText(StringBuffer tempBuf) {

        AttributedString as = new AttributedString(tempBuf.toString());
        as.addAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                        InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT);
        
        context.dispatchInputMethodEvent(
                                  InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                  as.getIterator(), 0,
                                  TextHitInfo.leading(insertionPoint), null);
    }

    /**
     * Send the committed text to the client.
     */
    private void sendCommittedText(StringBuffer tempBuf) {
        AttributedString as = new AttributedString(tempBuf.toString());
        context.dispatchInputMethodEvent(
                                  InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                  as.getIterator(), tempBuf.length(),
                                  TextHitInfo.leading(insertionPoint), null);

        buffer.setLength(0);
        insertionPoint = 0;
        format = UNSET;
    }

    /**
     * Move the insertion point one position to the left in the composed text.
     * 
     */
    private void moveCaretLeft() {
        int len = buffer.length();
        if (--insertionPoint < 2) {
            insertionPoint++;
            beep();
        } else if (format == SURROGATE_PAIR && insertionPoint == 7) {
            insertionPoint = 8;
            beep();
        }

        context.dispatchInputMethodEvent(
                                  InputMethodEvent.CARET_POSITION_CHANGED,
                                  null, 0,
                                  TextHitInfo.leading(insertionPoint), null);
    }

    /**
     * Move the insertion point one position to the right in the composed text.
     */
    private void moveCaretRight() {
        int len = buffer.length();
        if (++insertionPoint > len) {
            insertionPoint = len;
            beep();
        }

        context.dispatchInputMethodEvent(
                                  InputMethodEvent.CARET_POSITION_CHANGED,
                                  null, 0,
                                  TextHitInfo.leading(insertionPoint), null);
    }

    /**
     * Delete the character preceding the insertion point in the composed text.
     */
    private void deletePreviousCharacter() {

        buffer.deleteCharAt(--insertionPoint);
        if (buffer.length() == 0) {
            sendCommittedText(buffer);
        } else {
            sendComposedText(buffer);
        }

    }

    /**
     * Delete the character following the insertion point in the composed text.
     * If the insertion point is at the end of the composed text, ring the bell.
     */
    private void deleteCharacter() {
        if (insertionPoint < buffer.length()) {
            buffer.deleteCharAt(insertionPoint);
            sendComposedText(buffer);
        } else {
            beep();
        }
    }


    private void startComposition(char c) {
        buffer.append(c);
        insertionPoint = 1;
        sendComposedText(buffer);
    }

    private void cancelComposition() {
        buffer.setLength(0);
        insertionPoint = 0;
        sendCommittedText(buffer);
    }

    private void finishComposition() {
        int len = buffer.length();

        StringBuffer sinhalaBuf = new StringBuffer(translate(buffer.toString()));

        sendCommittedText(sinhalaBuf);
        buffer.setLength(0);

        beep();
    }



    private static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }


    public void activate() {

        System.out.println("testing");

        if (buffer == null) {
            buffer = new StringBuffer();
            initLanguageSettings();
            insertionPoint = 0;
        }

    }

    public void deactivate(boolean isTemporary) {
        if (!isTemporary) {
            buffer = null;
        }
    }

    public void dispose() {
    }

    public Object getControlObject() {
        return null;
    }

    public void endComposition() {
        sendCommittedText(buffer);
    }

    public Locale getLocale() {
        return locale;
    }

    public void hideWindows() {
    }

    public boolean isCompositionEnabled() {
        return true;
    }

    public void notifyClientWindowChange(Rectangle location) {
    }

    public void reconvert() {
        throw new UnsupportedOperationException();
    }

    public void removeNotify() {
    }

    public void setCharacterSubsets(Character.Subset[] subsets) {
    }

    public void setCompositionEnabled(boolean enable) {
        throw new UnsupportedOperationException();
    }

    public void setInputMethodContext(InputMethodContext context) {

        this.context = context;
        
    }

    public boolean setLocale(Locale locale) {
        this.locale = locale;
        return true;
    }

    private void initLanguageSettings(){

        vowelsUni[0]="ඌ";    vowels[0]="oo";    vowelModifiersUni[0]="ූ";
        vowelsUni[1]="ඕ";    vowels[1]="o\\)";    vowelModifiersUni[1]="ෝ";
        vowelsUni[2]="ඕ";    vowels[2]="oe";    vowelModifiersUni[2]="ෝ";
        vowelsUni[3]="ආ";    vowels[3]="aa";    vowelModifiersUni[3]="ා";
        vowelsUni[4]="ආ";    vowels[4]="a\\)";    vowelModifiersUni[4]="ා";
        vowelsUni[5]="ඈ";    vowels[5]="Aa";    vowelModifiersUni[5]="ෑ";
        vowelsUni[6]="ඈ";    vowels[6]="A\\)";    vowelModifiersUni[6]="ෑ";
        vowelsUni[7]="ඈ";    vowels[7]="ae";    vowelModifiersUni[7]="ෑ";
        vowelsUni[8]="ඊ";    vowels[8]="ii";    vowelModifiersUni[8]="ී";
        vowelsUni[9]="ඊ";    vowels[9]="i\\)";    vowelModifiersUni[9]="ී";
        vowelsUni[10]="ඊ";    vowels[10]="ie";    vowelModifiersUni[10]="ී";
        vowelsUni[11]="ඊ";    vowels[11]="ee";    vowelModifiersUni[11]="ී";
        vowelsUni[12]="ඒ";    vowels[12]="ea";    vowelModifiersUni[12]="ේ";
        vowelsUni[13]="ඒ";    vowels[13]="e\\)";    vowelModifiersUni[13]="ේ";
        vowelsUni[14]="ඒ";    vowels[14]="ei";    vowelModifiersUni[14]="ේ";
        vowelsUni[15]="ඌ";    vowels[15]="uu";    vowelModifiersUni[15]="ූ";
        vowelsUni[16]="ඌ";    vowels[16]="u\\)";    vowelModifiersUni[16]="ූ";
        vowelsUni[17]="ඖ";    vowels[17]="au";    vowelModifiersUni[17]="ෞ";
        vowelsUni[18]="ඇ";    vowels[18]="/\\a";    vowelModifiersUni[18]="ැ";

        vowelsUni[19]="අ";    vowels[19]="a";    vowelModifiersUni[19]="";
        vowelsUni[20]="ඇ";    vowels[20]="A";    vowelModifiersUni[20]="ැ";
        vowelsUni[21]="ඉ";    vowels[21]="i";    vowelModifiersUni[21]="ි";
        vowelsUni[22]="එ";    vowels[22]="e";    vowelModifiersUni[22]="ෙ";
        vowelsUni[23]="උ";    vowels[23]="u";    vowelModifiersUni[23]="ු";
        vowelsUni[24]="ඔ";    vowels[24]="o";    vowelModifiersUni[24]="ො";
        vowelsUni[25]="ඓ";    vowels[25]="I";    vowelModifiersUni[25]="ෛ";

        specialConsonantsUni[0]="ං"; specialConsonants[0]="\\n";
        specialConsonantsUni[1]="ඃ"; specialConsonants[1]="\\h";
        specialConsonantsUni[2]="ඞ";  specialConsonants[2]="\\N";
        specialConsonantsUni[3]="ඍ"; specialConsonants[3]="\\R";

        //special characher Repaya
        specialConsonantsUni[4]="ර්"+"\u200D"; specialConsonants[4]="R";
        specialConsonantsUni[5]="ර්"+"\u200D"; specialConsonants[5]="\\r";

        consonantsUni[0]="ඬ"; consonants[0]="nnd";
        consonantsUni[1]="ඳ"; consonants[1]="nndh";
        consonantsUni[2]="ඟ"; consonants[2]="nng";
        consonantsUni[3]="ථ"; consonants[3]="Th";
        consonantsUni[4]="ධ"; consonants[4]="Dh";
        consonantsUni[5]="ඝ"; consonants[5]="gh";
        consonantsUni[6]="ඡ"; consonants[6]="Ch";
        consonantsUni[7]="ඵ"; consonants[7]="ph";
        consonantsUni[8]="භ"; consonants[8]="bh";
        consonantsUni[9]="ශ"; consonants[9]="sh";
        consonantsUni[10]="ෂ"; consonants[10]="Sh";
        consonantsUni[11]="ඥ"; consonants[11]="GN";
        consonantsUni[12]="ඤ"; consonants[12]="KN";
        consonantsUni[13]="ළු"; consonants[13]="Lu";
        consonantsUni[14]="ද"; consonants[14]="dh";
        consonantsUni[15]="ච"; consonants[15]="ch";
        consonantsUni[16]="ඛ"; consonants[16]="kh";
        consonantsUni[17]="ත"; consonants[17]="th";

        consonantsUni[18]="ට"; consonants[18]="t";
        consonantsUni[19]="ක"; consonants[19]="k";
        consonantsUni[20]="ඩ"; consonants[20]="d";
        consonantsUni[21]="න"; consonants[21]="n";
        consonantsUni[22]="ප"; consonants[22]="p";
        consonantsUni[23]="බ"; consonants[23]="b";
        consonantsUni[24]="ම"; consonants[24]="m";
        consonantsUni[25]="‍ය"; consonants[25]="\\u005C" + "y";
        consonantsUni[26]="‍ය"; consonants[26]="Y";
        consonantsUni[27]="ය"; consonants[27]="y";
        consonantsUni[28]="ජ"; consonants[28]="j";
        consonantsUni[29]="ල"; consonants[29]="l";
        consonantsUni[30]="ව"; consonants[30]="v";
        consonantsUni[31]="ව"; consonants[31]="w";
        consonantsUni[32]="ස"; consonants[32]="s";
        consonantsUni[33]="හ"; consonants[33]="h";
        consonantsUni[34]="ණ"; consonants[34]="N";
        consonantsUni[35]="ළ"; consonants[35]="L";
        consonantsUni[36]="ඛ"; consonants[36]="K";
        consonantsUni[37]="ඝ"; consonants[37]="G";
        consonantsUni[38]="ඨ"; consonants[38]="T";
        consonantsUni[39]="ඪ"; consonants[39]="D";
        consonantsUni[40]="ඵ"; consonants[40]="P";
        consonantsUni[41]="ඹ"; consonants[41]="B";
        consonantsUni[42]="ෆ"; consonants[42]="f";
        consonantsUni[43]="ඣ"; consonants[43]="q";
        consonantsUni[44]="ග"; consonants[44]="g";
        //need to ommit in dealing with Rakaransha, thus coming at the end
        consonantsUni[45]="ර"; consonants[45]="r";

        specialCharUni[0]="ෲ"; specialChar[0]="ruu";
        specialCharUni[1]="ෘ"; specialChar[1]="ru";
    }


    private String translate(String text){
        String s,v;

        //special consonents
        for (int i=0; i<specialConsonants.length; i++){
            text = text.replace(specialConsonants[i], specialConsonantsUni[i]);
        }

        //consonents + special Chars
        for (int i=0; i<specialCharUni.length; i++){
            for (int j=0;j<consonants.length;j++){
                s = consonants[j] + specialChar[i];
                v = consonantsUni[j] + specialCharUni[i];
                //r = new RegExp(s, "g");
                text = text.replace(s, v);
            }
        }
        //consonants + Rakaransha + vowel modifiers
        for (int j=0;j<consonants.length;j++){
            for (int i=0;i<vowels.length;i++){
                s = consonants[j] + "r" + vowels[i];
                v = consonantsUni[j] + "්‍ර" + vowelModifiersUni[i];
                //r = new RegExp(s, "g");
                text = text.replace(s, v);
            }
            s = consonants[j] + "r";
            v = consonantsUni[j] + "්‍ර";
            //r = new RegExp(s, "g");
            text = text.replace(s, v);

        }
        
        //consonents + vowel modifiers
        for (int i=0;i<consonants.length;i++){
            for (int j=0;j<nVowels;j++){
                s = consonants[i]+vowels[j];
                v = consonantsUni[i] + vowelModifiersUni[j];
                //r = new RegExp(s, "g");
                text = text.replace(s, v);
            }
        }

        //consonents + HAL
        for (int i=0; i<consonants.length; i++){
            //r = new RegExp(consonants[i], "g");
            text = text.replace(consonants[i], consonantsUni[i]+"්");
        }

        //vowels
        for (int i=0; i<vowels.length; i++){
            //r = new RegExp(vowels[i], "g");
            text = text.replace(vowels[i], vowelsUni[i]);
        }

        return text;
    }

}
