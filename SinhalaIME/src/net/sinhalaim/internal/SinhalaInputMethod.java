/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.sinhalaim.internal;

/**
 *
 * @author buddhika
 */

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.font.TextHitInfo;
import java.awt.im.InputMethodHighlight;
import java.awt.im.spi.InputMethod;
import java.awt.im.spi.InputMethodContext;
import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.AttributedString;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.text.MessageFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;



public class SinhalaInputMethod implements InputMethod {

    private static Locale[] SUPPORTED_LOCALES = {
        Locale.ENGLISH
    };
    private static Locale[] LOOKUP_LOCALES = {
        Locale.ENGLISH
    };

    // lookup tables - shared by all instances of this input method
    private static Properties cityNames;
    private static Properties cityAliases;
    private static Properties templates;

    // windows - shared by all instances of this input method
    private static Window statusWindow;
    // current or last statusWindow owner instance
    private static SinhalaInputMethod statusWindowOwner;
    // true if Solaris style; false if PC style
    private static boolean attachedStatusWindow = false;
    // remember the statusWindow location per instance
    private Rectangle clientWindowLocation;
    // status window location in PC style
    private static Point globalStatusWindowLocation;
    // keep live city input method instances (synchronized using statusWindow)
    private static HashSet sinhalaInputMethodInstances = new HashSet(5);
    // non-null if Swing input method JFrame is available
    static Method methodCreateInputMethodJFrame = null;
    
    static {
        methodCreateInputMethodJFrame =
            (Method)AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    try {
                        Class[] params = new Class[2];
                        params[0] = String.class;
                        params[1] = Boolean.TYPE;
                        return InputMethodContext.class.getMethod("createInputMethodJFrame", params);
                    }  catch (NoSuchMethodException e) {
                        return null;
                    }
                }
            });
    }
    // input method window titles
    static final String statusWindowTitle = "Sinhala Phonetic Input Method";
    static final String lookupWindowTitle = "Lookup Window";

    // lookup information - per instance
    private String[] lookupCandidates;
    private Locale[] lookupLocales;
    private int lookupCandidateCount;
    private LookupList lookupList;
    private int lookupSelection;

    // per-instance state
    InputMethodContext inputMethodContext;
    private boolean active;
    private boolean disposed;
    private Locale locale;
    private boolean converted;
    private StringBuffer rawText;
    private String convertedText;

    private int insertionPoint;
    private String[] rawTextSegs = null;
    private String[] convertedSegs = null;
    private String fmt = null;
    private int fieldPos[][] = null;
    private int segPos[][] = null;
    private int selectedSeg = 0;
    private int numSegs = 0;
    private int committedSeg = -1;
    private int previouslyCommittedCharacterCount = 0;


    //sinhala vovel settings
    private String text;
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
    private final static int POINTS_PER_INCH = 72;  


    public SinhalaInputMethod() throws IOException {
        this.initLanguageSettings();
        rawText = new StringBuffer();
    }

    //Initiate Language Settings
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
        //last because we need to ommit this in dealing with Rakaransha
        consonantsUni[45]="ර"; consonants[45]="r";

        specialCharUni[0]="ෲ"; specialChar[0]="ruu";
        specialCharUni[1]="ෘ"; specialChar[1]="ru";
    }

    //Translate the words
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

        //jTextArea2.setText(text);
        //jTextArea2.setText("meyatuwana");
        return text;
    }

    public void dispatchEvent(AWTEvent event) {
        if (!active && (event instanceof KeyEvent)) {
            System.out.println("SinhalaInputMethod.dispatch called with KeyEvent while not active");
        }
        if (disposed) {
            System.out.println("SinhalaInputMethod.dispatch called after being disposed");
        }
        if (!(event instanceof InputEvent)) {
            System.out.println("SinhalaInputMethod.dispatch called with event that's not an InputEvent");
        }
        if (event.getID() == KeyEvent.KEY_RELEASED) {
             // if candidate window is active
            if (lookupList != null) {
                KeyEvent e = (KeyEvent) event;
                if (e.isControlDown()) {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        // Control + Arrow Down commits chunks
                        closeLookupWindow();
                        commit(selectedSeg);
                        e.consume();
                    }
                } else {
                    // select candidate by Arrow Up/Down
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (++lookupSelection == lookupCandidateCount) {
                            lookupSelection = 0;
                        }
                        selectCandidate(lookupSelection);
                        e.consume();
                        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (--lookupSelection < 0) {
                            lookupSelection = lookupCandidateCount;
                        }
                        selectCandidate(lookupSelection);
                        e.consume();
                    }
                }

            } else {
                if (event.getID() == KeyEvent.KEY_RELEASED) {
                    KeyEvent e = (KeyEvent) event;
                    if (e.isControlDown()) {
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            // Control + Arrow Down commits chunks
                            commit(selectedSeg);
                            e.consume();
                        }
                    } else {
                        // move selected segment by Arrow Right/Left
                        if ((e.getKeyCode() == KeyEvent.VK_RIGHT) && (converted == true)) {
                            if (selectedSeg < (numSegs - 1)) {
                                selectedSeg++;
                                sendText(false);
                                e.consume();
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        } else if ((e.getKeyCode() == KeyEvent.VK_LEFT) && (converted == true)) {
                            if (selectedSeg > (committedSeg + 1)) {
                                selectedSeg--;
                                sendText(false);
                                e.consume();
                            } else {
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                    }
                }
            }
        }

        if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            MouseEvent e = (MouseEvent) event;
            Component comp = e.getComponent();
            Point pnt = comp.getLocationOnScreen();
            int x = (int)pnt.getX() + e.getX();
            int y = (int)pnt.getY() + e.getY();
            TextHitInfo hit = inputMethodContext.getLocationOffset(x,y);
            if (hit != null) {
                // within composed text
                if (converted) {
                    selectedSeg = findSegment(hit.getInsertionIndex());
                    sendText(false);
                    e.consume();
                } else {
                    insertionPoint = hit.getInsertionIndex();
                }
            } else {
                // if hit outside composition, simply commit all.
                commitAll();
            }
        }

        if (event.getID() == KeyEvent.KEY_TYPED) {
            KeyEvent e = (KeyEvent) event;
            if (handleCharacter(e.getKeyChar())) {
                e.consume();
            }
        }
    }

    /**
     * Attempts to handle a typed character.
     * @return whether the character was handled
     */
    private boolean handleCharacter(char ch) {
        if (lookupList != null) {
            if (ch == ' ') {
                if (++lookupSelection == lookupCandidateCount) {
                    lookupSelection = 0;
                }
                selectCandidate(lookupSelection);
                return true;
            } else if (ch == '\n') {
                commitAll();
                closeLookupWindow();
                return true;
            } else if ('1' <= ch && ch <= '0' + lookupCandidateCount) {
                selectCandidate(ch - '1');
                closeLookupWindow();
                return true;
            } else {
                Toolkit.getDefaultToolkit().beep();
                return true;
            }
        } else if (converted) {
            if (ch == ' ') {
                convertAgain();
                return true;
            } else if (ch == '\n') {
                commitAll();
                return true;
            } else {
                Toolkit.getDefaultToolkit().beep();
                return true;
            }
        } else {
            if (ch == ' ') {
                int length = rawText.length();
                if (length == 3 || length == 6 || length == 9) {
                    convertFirstTime();
                    return true;
                }
            } else if (ch == '\n') {
                if (rawText.length() != 0) {
                    commitAll();
                    return true;
                }
            } else if (ch == '\b') {
                if (insertionPoint > 0) {
                    rawText.deleteCharAt(insertionPoint - 1);
                    --insertionPoint;
                    sendText(false);
                    return true;
                }
            } else if ('a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z') {
                rawText.insert(insertionPoint++, ch);

                System.out.println(rawText.toString() + rawText.length());
                //String sinhalaText = this.translate( rawText.toString() );
                //rawText.insert(0, sinhalaText);
                commitAll();

                sendText(false);
                return true;
            }
            if (rawText.length() != 0) {
                Toolkit.getDefaultToolkit().beep();
                return true;
            }
        }
        return false;
    }

    public void setInputMethodContext(InputMethodContext context) {
        inputMethodContext = context;

        if (inputMethodContext instanceof JTextComponent){
            JOptionPane.showMessageDialog(null, "testing");
        }

        if (statusWindow == null) {
            Window sw;

            if (methodCreateInputMethodJFrame != null) {
                try {
                    Object[] params = new Object[2];
                    params[0] = statusWindowTitle;
                    params[1] = Boolean.FALSE;
                    sw = (Window)methodCreateInputMethodJFrame.invoke(context, params);
                } catch (Exception e) {
                        sw = context.createInputMethodWindow(statusWindowTitle, false);
                }
            } else {
                sw = context.createInputMethodWindow(statusWindowTitle, false);
            }

            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setForeground(Color.black);
            label.setBackground(Color.white);

            synchronized (this.getClass()) {
                if (statusWindow == null) {
                    statusWindow = sw;
                    statusWindow.addComponentListener(new ComponentListener() {
                        public void componentResized(ComponentEvent e) {}
                        public void componentMoved(ComponentEvent e) {
                            synchronized (statusWindow) {
                                if (!attachedStatusWindow) {
                                    Component comp = e.getComponent();
                                    if (comp.isVisible()) {
                                        globalStatusWindowLocation = comp.getLocation();
                                    }
                                }
                            }
                        }

                        public void componentShown(ComponentEvent e) {}
                        public void componentHidden(ComponentEvent e) {}
                    });

                    label.addMouseListener(new MouseListener() {
                        public void mouseClicked(MouseEvent e) {
                                int count = e.getClickCount();
                                if (count >= 2) {
                                toggleStatusWindowStyle();
                            }
                        }
                        public void mousePressed(MouseEvent e) {}
                        public void mouseReleased(MouseEvent e) {}
                        public void mouseEntered(MouseEvent e) {}
                        public void mouseExited(MouseEvent e) {}
                    });
                    if (statusWindow instanceof JFrame) {
                        ((JFrame)statusWindow).getContentPane().add(label);
                    } else {
                        statusWindow.add(label);
                    }
                    statusWindowOwner = this;
                    updateStatusWindow(locale);
                    label.setSize(200, 50);
                    statusWindow.pack();
                }
            }
        }

        inputMethodContext.enableClientWindowNotification(this, attachedStatusWindow);
        synchronized (statusWindow) {
            sinhalaInputMethodInstances.add(this);
        }
    }

    public boolean setLocale(Locale locale) {
        for (int i = 0; i < SUPPORTED_LOCALES.length; i++) {
            if (locale.equals(SUPPORTED_LOCALES[i])) {
                this.locale = locale;
                if (statusWindow != null) {
                    updateStatusWindow(locale);
                }
                return true;
            }
        }
        return false;
    }

    public Locale getLocale() {
        return locale;
    }

    void updateStatusWindow(Locale locale) {
        synchronized (statusWindow) {
            JLabel label;
            if (statusWindow instanceof JFrame) {
                label = (JLabel) ((JFrame)statusWindow).getContentPane().getComponent(0);
            } else {
                label = (JLabel) statusWindow.getComponent(0);
            }
            String localeName = locale == null ? "null" : locale.getDisplayName();
            String text = "Current locale: " + localeName;
            if (!label.getText().equals(text)) {
                label.setText(text);
                statusWindow.pack();
            }
            if (attachedStatusWindow) {
                if (clientWindowLocation != null) {
                    statusWindow.setLocation(clientWindowLocation.x,
                                 clientWindowLocation.y + clientWindowLocation.height);
                }
            } else {
                setPCStyleStatusWindow();
            }
        }
    }

    private void setPCStyleStatusWindow() {
	synchronized (statusWindow) {
	    if (globalStatusWindowLocation == null) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            globalStatusWindowLocation = new Point(d.width - statusWindow.getWidth(),
                                   d.height - statusWindow.getHeight() - 25);
	    }
	    statusWindow.setLocation(globalStatusWindowLocation.x, globalStatusWindowLocation.y);
	}
    }

    private void setStatusWindowForeground(Color fg) {
	synchronized (statusWindow) {
	    if (statusWindowOwner != this) {
            return;
	    }
	    JLabel label;
	    if (statusWindow instanceof JFrame) {
            label = (JLabel) ((JFrame)statusWindow).getContentPane().getComponent(0);
	    } else {
            label = (JLabel) statusWindow.getComponent(0);
	    }
	    label.setForeground(fg);
	}
    }

    private void toggleStatusWindowStyle() {
        synchronized (statusWindow) {
            if (attachedStatusWindow) {
                attachedStatusWindow = false;
                setPCStyleStatusWindow();
            } else {
                attachedStatusWindow = true;
            }
            Iterator itr = sinhalaInputMethodInstances.iterator();
            while (itr.hasNext()) {
                SinhalaInputMethod im = (SinhalaInputMethod)itr.next();
                im.inputMethodContext.enableClientWindowNotification(im, attachedStatusWindow);
            }
        }
    }

    public void setCharacterSubsets(Character.Subset[] subsets) {
        // ignore
    }

    public void reconvert() {
        // not supported yet
        throw new UnsupportedOperationException();
    }

    /**
     * find segment at insertion point
     */
    int findSegment(int insertion) {
        for (int i = committedSeg + 1; i < numSegs; i++) {
            if ((segPos[i][0] < insertion) && (insertion < segPos[i][1])) {
            return i;
            }
        }
        return 0;
    }

    public void activate() {
        if (active) {
            System.out.println("SinhalaInputMethod.activate called while active");
        }
        active = true;
        synchronized (statusWindow) {
            statusWindowOwner = this;
            updateStatusWindow(locale);
            if (!statusWindow.isVisible()) {
                statusWindow.setVisible(true);
            }
            setStatusWindowForeground(Color.black);
        }
    }

    public void deactivate(boolean isTemporary) {
        closeLookupWindow();
        if (!active) {
            System.out.println("SinhalaInputMethod.deactivate called while not active");
        }
        setStatusWindowForeground(Color.lightGray);
        active = false;
    }

    public void hideWindows() {
        if (active) {
            System.out.println("SinhalaInputMethod.hideWindows called while active");
        }
        closeLookupWindow();

        synchronized (statusWindow) {
            if (statusWindowOwner == this) {
                statusWindow.setVisible(false);
            }
        }
    }

    public void removeNotify() {
    }

    public void endComposition() {
        if (rawText.length() != 0) {
            commitAll();
        }
        closeLookupWindow();
    }

    public void notifyClientWindowChange(Rectangle location) {

        clientWindowLocation = location;
        synchronized (statusWindow) {
            if (!attachedStatusWindow || statusWindowOwner != this) {
                return;
            }
            if (location != null) {
                statusWindow.setLocation(location.x, location.y+location.height);
                if (!statusWindow.isVisible()) {
                    if (active) {
                        setStatusWindowForeground(Color.black);
                    } else {
                        setStatusWindowForeground(Color.lightGray);
                    }
                    statusWindow.setVisible(true);
                }
            } else {
                statusWindow.setVisible(false);
            }
        }
    }

    public void dispose() {
        if (active) {
            System.out.println("SinhalaInputMethod.dispose called while active");
        }
        if (disposed) {
            System.out.println("SinhalaInputMethod.disposed called repeatedly");
        }
        closeLookupWindow();
        synchronized (statusWindow) {
            sinhalaInputMethodInstances.remove(this);
        }
        disposed = true;
    }

    public Object getControlObject() {
        return null;
    }

    public void setCompositionEnabled(boolean enable) {
        // not supported yet
        throw new UnsupportedOperationException();
    }

    public boolean isCompositionEnabled() {
        // always enabled
        return true;
    }


    /*
     * Looks up the entry for key in the given table, taken the
     * input method's locale into consideration.
     */
    String lookup(String lookupName, Properties table) {
        String result = null;
        String localeLookupName = lookupName + "_" + locale;
        while (true) {
            result = (String) table.get(localeLookupName);
            if (result != null) {
                break;
            }
            int index = localeLookupName.lastIndexOf("_");
            if (index == -1) {
                break;
            }
            localeLookupName = localeLookupName.substring(0, index);
        }
        return result;
    }

    String findAlias(String lookupName) {
        lookupName = lookupName.toUpperCase();
        return cityAliases.getProperty(lookupName, lookupName);
    }

    void convertFirstTime() {
        numSegs = rawText.length() / 3;
        rawTextSegs = new String[numSegs];
        convertedSegs = new String[numSegs];
        for (int i = 0; i < numSegs; ++i) {
            rawTextSegs[i] = rawText.substring(i * 3, (i + 1) *3);
            String alias = findAlias(rawTextSegs[i]);
            String result = lookup(alias, cityNames);
            if (result != null) {
            convertedSegs[i] = result;
            } else {
            convertedSegs[i] = rawText.substring(i * 3, (i + 1) * 3);
            }
        }
        converted = true;
        sendText(false);
    }

    void convertAgain() {
        String lookupName;
        lookupName = rawTextSegs[selectedSeg];
        // if converted string is same as original, it's not in word list. We skip
        // further conversion.
        if (!lookupName.equals(convertedSegs[selectedSeg])) {
            lookupName = findAlias(lookupName);
            lookupCandidates = new String[LOOKUP_LOCALES.length];
            lookupLocales = new Locale[LOOKUP_LOCALES.length];
            lookupCandidateCount = 0;
            lookupSelection = 0;
            for (int i = 0; i < LOOKUP_LOCALES.length; i++) {
            Locale iLocale = LOOKUP_LOCALES[i];
            String localeLookupName = lookupName + '_' + iLocale;
            String localeConvertedText = (String) cityNames.get(localeLookupName);
            if (localeConvertedText != null) {
                lookupCandidates[lookupCandidateCount] = localeConvertedText;
                lookupLocales[lookupCandidateCount] = iLocale;
                lookupCandidateCount++;
            } else if (iLocale.equals(Locale.ENGLISH)) {
                localeConvertedText = (String) cityNames.get(lookupName);
                if (localeConvertedText != null) {
                lookupCandidates[lookupCandidateCount] = localeConvertedText;
                lookupLocales[lookupCandidateCount] = iLocale;
                lookupCandidateCount++;
                }
            }
            if (convertedSegs[selectedSeg].equals(localeConvertedText)) {
                lookupSelection = lookupCandidateCount - 1;
            }
            }
            openLookupWindow();
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /* commits all chunks up to the specified index */
    private void commit(int index) {
        if (index >= (numSegs - 1)) {
            // if this is the last segment, commit all
            commitAll();
        } else {
            committedSeg = index;
            selectedSeg = committedSeg + 1;
            sendText(true);
        }
    }

    /* commits all chunks */
    void commitAll() {
        committedSeg = numSegs - 1;
        sendText(true);
        // once composed text is committed, reinitialize all variables
        //rawText.setLength(0);
        convertedText = null;
        converted = false;

        rawTextSegs = null;
        convertedSegs = null;
        fmt = null;
        fieldPos = null;
        segPos = null;
        selectedSeg = 0;
        insertionPoint = rawText.length();
        numSegs = 0;
        committedSeg = -1;
        previouslyCommittedCharacterCount = 0;
    }

    void parseFormat() {
        Vector vec = new Vector();
        int[] elem = null;

        for(int i = 0; i < fmt.length(); i++) {
            if (fmt.charAt(i) == '{') {
            elem = new int[2];
            elem[0] = i;
            } else if (fmt.charAt(i) == '}') {
            elem[1] = i;
            vec.add(elem);
            }
        }
        if (vec.size() != 0) {
            fieldPos = new int[vec.size()][];
            vec.toArray(fieldPos);
        }
    }

    void formatOutput() {
        if (fmt == null) {
            String key = "Template" + Integer.toString(numSegs);
            fmt = lookup(key, templates);
            parseFormat();
        }
        convertedText = MessageFormat.format(fmt, (Object [])convertedSegs);
        // Figure out converted segment position
        int errors = 0;
        segPos = new int[fieldPos.length][2];
        for (int i = 0; i < fieldPos.length; i++) {
            int optLen = (fieldPos[i][1] - fieldPos[i][0]) + 1;
            int diffs = convertedSegs[i].length() - optLen;
            segPos[i][0] = fieldPos[i][0] + errors;
            segPos[i][1] = segPos[i][0] + convertedSegs[i].length();
            errors += diffs;
        }
    }

    void sendText(boolean committed) {
        AttributedString as = null;
        TextHitInfo caret = null;
        int committedCharacterCount = 0;
        int newTotalCommittedCharacterCount = previouslyCommittedCharacterCount;

        if (converted) {
            formatOutput();
            if (committed) {
                if (committedSeg == (numSegs - 1)) {
                    newTotalCommittedCharacterCount = convertedText.length();
                } else {
                    newTotalCommittedCharacterCount = segPos[committedSeg + 1][0];
                }
                committedCharacterCount = newTotalCommittedCharacterCount - previouslyCommittedCharacterCount;
            }

            as = new AttributedString(convertedText.substring(previouslyCommittedCharacterCount));

            for(int i = committedSeg + 1; i < numSegs; i++) {
                InputMethodHighlight highlight;
                if (i == selectedSeg) {
                    highlight = InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
                } else {
                    highlight = InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
                }

                as.addAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT, highlight,
                                    segPos[i][0] - previouslyCommittedCharacterCount,
                                    segPos[i][1] - previouslyCommittedCharacterCount);
            }
            previouslyCommittedCharacterCount = newTotalCommittedCharacterCount;
            
        } else {
            as = new AttributedString(rawText.toString());
            if (committed) {
                committedCharacterCount = rawText.length();
            } else if (rawText.length() != 0) {
                as.addAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                    InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT);
                caret = TextHitInfo.leading(insertionPoint);
            }
        }
        inputMethodContext.dispatchInputMethodEvent(
                                InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                as.getIterator(),
                                committedCharacterCount,
                                caret,
                                null);
    }

    void selectCandidate(int candidate) {
        lookupSelection = candidate;
        lookupList.selectCandidate(lookupSelection);
        convertedSegs[selectedSeg] = lookupCandidates[lookupSelection];
        sendText(false);
    }

    int getSelectedSegmentOffset() {
        return segPos[selectedSeg][0] - previouslyCommittedCharacterCount;
    }

    void openLookupWindow() {
        lookupList = new LookupList(this, inputMethodContext,
				    lookupCandidates, lookupLocales, lookupCandidateCount);
        lookupList.selectCandidate(lookupSelection);
    }

    void closeLookupWindow() {
        if (lookupList != null) {
            lookupList.setVisible(false);
            lookupList = null;
        }
    }
}

class LookupList extends JPanel {

    SinhalaInputMethod inputMethod;
    InputMethodContext context;
    Window lookupWindow;
    String[] candidates;
    Locale[] locales;
    int candidateCount;
    int selected;

    final int INSIDE_INSET = 4;
    final int LINE_SPACING = 18;

    LookupList(SinhalaInputMethod inputMethod, InputMethodContext context,
	       String[] candidates, Locale[] locales, int candidateCount) {
        this.inputMethod = inputMethod;
        this.context = context;
        this.candidates = candidates;
        this.locales = locales;
        this.candidateCount = candidateCount;

	if (SinhalaInputMethod.methodCreateInputMethodJFrame != null) {
	    try {
		Object[] params = new Object[2];
		params[0] = SinhalaInputMethod.lookupWindowTitle;
		params[1] = Boolean.TRUE;
		lookupWindow =
		    (Window)SinhalaInputMethod.methodCreateInputMethodJFrame.invoke(context, params);
	    } catch (Exception e) {
        	lookupWindow =
		    context.createInputMethodWindow(SinhalaInputMethod.lookupWindowTitle, true);
	    }
	} else {
            lookupWindow = context.createInputMethodWindow(SinhalaInputMethod.lookupWindowTitle, true);
	}
        setFont(new Font("Dialog", Font.PLAIN, 12));
        setPreferredSize(new Dimension(200, candidateCount * LINE_SPACING + 2 * INSIDE_INSET));
        setOpaque(true);
        setForeground(Color.black);
        setBackground(Color.white);

        enableEvents(AWTEvent.KEY_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);

	if (lookupWindow instanceof JFrame) {
	    ((JFrame)lookupWindow).getContentPane().add(this);
	} else {
	    lookupWindow.add(this);
	}
	lookupWindow.pack();
        updateWindowLocation();
        lookupWindow.setVisible(true);
    }

    /**
     * Positions the lookup window near (usually below) the
     * insertion point in the component where composition occurs.
     */
    private void updateWindowLocation() {
        Point windowLocation = new Point();
        int textOffset = inputMethod.getSelectedSegmentOffset();
        Rectangle caretRect = context.getTextLocation(TextHitInfo.leading(textOffset));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = lookupWindow.getSize();
        final int SPACING = 2;

	if (caretRect.x + windowSize.width > screenSize.width) {
            windowLocation.x = screenSize.width - windowSize.width;
        } else {
            windowLocation.x = caretRect.x;
        }

	if (caretRect.y + caretRect.height + SPACING + windowSize.height > screenSize.height) {
            windowLocation.y = caretRect.y - SPACING - windowSize.height;
        } else {
            windowLocation.y = caretRect.y + caretRect.height + SPACING;
        }

        lookupWindow.setLocation(windowLocation);
    }

    void selectCandidate(int candidate) {
        selected = candidate;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
        FontMetrics metrics = g.getFontMetrics();
        int descent = metrics.getDescent();
        int ascent = metrics.getAscent();
        for (int i = 0; i < candidateCount; i++) {
            g.drawString((i + 1) + "   " + candidates[i] +
                    "  (" + locales[i].getDisplayName() + ")",
                    INSIDE_INSET, LINE_SPACING * (i + 1) + INSIDE_INSET - descent);
        }
        Dimension size = getSize();
        g.drawRect(INSIDE_INSET / 2,
                LINE_SPACING * ( selected + 1) + INSIDE_INSET - (descent + ascent + 1),
                size.width - INSIDE_INSET,
                descent + ascent + 2);
        g.drawRect(0, 0, size.width - 1, size.height - 1);
    }

    public void setVisible(boolean visible) {
        if (!visible) {
            lookupWindow.setVisible(false);
            lookupWindow.dispose();
            lookupWindow = null;
        }
        super.setVisible(visible);
    }

    protected void processKeyEvent(KeyEvent event) {
        inputMethod.dispatchEvent(event);
    }

    protected void processMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            int y = event.getY();
            if (y >= INSIDE_INSET && y < INSIDE_INSET + candidateCount * LINE_SPACING) {
                inputMethod.selectCandidate((y - INSIDE_INSET) / LINE_SPACING);
                inputMethod.closeLookupWindow();
            }
        }
    }
}
