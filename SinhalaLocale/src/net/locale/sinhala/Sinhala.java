/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale.sinhala;

import java.text.Collator;
import java.util.Locale;
import java.util.TimeZone;
import net.locale.LocaleInfo;


/**
 *
 * @author Buddhika Laknath
 */
class Sinhala implements LocaleInfo{

    private static final Locale si_LK_LOCALE = new Locale("si","LK");

    private static final Locale[] SUPPORTED_LOCALES = { si_LK_LOCALE };

    private static final String[] ERAS = { "ක්‍රි.පූ." , "ක්‍රි.ව." };

    private static final String PATTERN_CHARS = "GyMdkHmsSEDFwWahKzZ";

    private static final String[] MONTHS = { "ජනවාරි", 
                                            "පෙබරවාරි", 
                                            "මාර්තු", 
                                            "අප්‍රේල්", 
                                            "මැයි", 
                                            "ජූනි", 
                                            "ජූලි", 
                                            "අගෝස්තු",
                                            "සැප්තැම්බර්", 
                                            "ඔක්තෝබර්", 
                                            "නොවැම්බර්", 
                                            "දෙසැම්බර්" 
                                        };

    private static final String[] SHORT_MONTHS = {  "ජන",
                                                    "පෙබ",
                                                    "මාර්තු",
                                                    "අප්‍රේල්",
                                                    "මැයි",
                                                    "ජූනි",
                                                    "ජූලි",
                                                    "අගෝ",
                                                    "සැප්",
                                                    "ඔක්",
                                                    "නොවැ",
                                                    "දෙසැ"
                                                  };

    private static final String[] WEEKDAYS = { "",
                                               "\u0d89\u0dbb\u0dd2\u0daf\u0dcf",
                                               "\u0dc3\u0daf\u0dd4\u0daf",
                                               "\u0d85\u0d9c\u0dc4\u0dbb\u0dd4\u0dc0\u0daf\u0dcf", 
                                               "\u0db6\u0daf\u0dcf\u0daf\u0dcf", 
                                               "\u0db6\u0dca\u200d\u0dbb\u0dc4\u0dc3\u0dca\u0db4\u0dad\u0dd2\u0db1\u0dca\u0daf\u0dcf",
                                               "\u0dc3\u0dd2\u0d9a\u0dd4\u0dbb\u0dcf\u0daf\u0dcf", 
                                               "\u0dc3\u0dd9\u0db1\u0dc3\u0dd4\u0dbb\u0dcf\u0daf\u0dcf",
                                            };
            
    private static final String[] SHORT_WEEKDAYS = {"", 
                                                    "ඉ",
                                                    "ස",
                                                    "අ",
                                                    "බ",
                                                    "බ්‍ර",
                                                    "සි",
                                                    "සෙ"
                                                   };

    private static final String LONG_TIMEZONE_NAME = "Sri Lanka Standard Time";

    private static final String SHORT_TIMEZONE_NAME = "LKT";
    
    private static final String DATE_SHORT = "yy-MM-dd";

    private static final String DATE_MEDIUM = "yyyy/MM/dd";
    
    private static final String DATE_LONG = "yyyy MMMM dd";

    private static final String DATE_FULL = "yyyy MMMM මස dd වැනිදා EEEE";

    private static final String TIME_SHORT = "hh.mm";

    private static final String TIME_MEDIUM = "hh.mm:ss";

    private static final String TIME_LONG = "aa hh.mm:ss";

    private static final String TIME_FULL = "aa hh.mm:ss Z";

    private static final char DECIMAL_SEPARATOR = '.';

    private static final char GROUPING_SEPARATOR = ',';

    private static final char PATTERN_SEPARATOR = ';';

    private static final char PERCENT = '%';

    private static final char ZERO_DIGIT = '0';

    private static final char DIGIT = '#';

    private static final char MINUS_SIGN = '-';

    private static final String EXPONENT_SEPARATOR = "E";

    private static final char PER_MILLE = '‰' ;

    private static final String INFINITY = "∞";

    private static final String NAN = "NaN"; 

    private static final String CURRENCY_SYMBOL = "Rs";

    private static final String PERCENT_FORMAT = "#,##0%";

    private static final String NUMBER_FORMAT = "#,##0.##";

    private static final String INTEGER_FORMAT = "#,##0";

    private static final String CURRENCY_FORMAT = "Rs #,##0.00";

    private static final String[][] COUNTRY_TUPLES = { { "LK", "ශ්‍රි ලංකාව" }};

    
    private static final String[][] LANGUAGE_TUPLES =   {{ "si", "සිංහල" }};

    private static final String[] AM_PM_STRING = { "පෙ.ව.", "ප.ව." };

   
    public void checkLocaleSupported(Locale locale) {
        if (locale == null) {
            throw new NullPointerException("locale must not be null");
        }

        // exact search
        for (Locale supported : getSupportedLocales()) {
        	if (locale.equals(supported))
			return;
		}

        //relaxed search
        for (Locale supported : getSupportedLocales()) {
            if (locale.getLanguage().equals(supported.getLanguage()))
                return;
        }

        throw new IllegalArgumentException("unsupported locale " + locale);
    }

    public String[][] getCountryTuples() {
        return COUNTRY_TUPLES;
    }

    public String getCurrencyFormat() {
        return CURRENCY_FORMAT;
    }

    public String getCurrencySymbol() {
        return CURRENCY_SYMBOL;
    }

    public String getDateFull() {
    	return DATE_FULL;
    }

    public String getDateLong() {
    	return DATE_LONG;
    }

    public String getDateMedium() {
    	return DATE_MEDIUM;
    }

    public String getDateShort() {
        return DATE_SHORT;
    }

    public char getDecimalSeparator() {
    	return DECIMAL_SEPARATOR;
    }

    public char getDigit() {
    	return DIGIT;
    }

    public String[] getEras() {
        return ERAS;
    }

    public String getExponentSeparator() {
    	return EXPONENT_SEPARATOR;
    }

    public char getGroupingSeparator() {
    	return GROUPING_SEPARATOR;
    }

    public String getInfinity() {
    	return INFINITY;
    }

    public String getIntegerFormat() {
    	return INTEGER_FORMAT;
    }

    public String[][] getLanguageTuples() {
    	return LANGUAGE_TUPLES;
    }

    public char getMinusSign() {
        return MINUS_SIGN;
    }

    public String[] getMonths() {
    	return MONTHS;
    }

    public String getNaN() {
    	return NAN;
    }

    public String getNumberFormat() {
    	return NUMBER_FORMAT;
    }

    public String getPatternChars() {
        return PATTERN_CHARS;
    }

    public char getPatternSeparator() {
    	return PATTERN_SEPARATOR;
    }

    public char getPercent() {
        return PERCENT;
    }

    public String getPercentFormat() {
        return PERCENT_FORMAT;
    }

    public char getPerMille() {
    	return PER_MILLE;
    }

    public String[] getShortMonths() {
    	return SHORT_MONTHS;
    }

    public String[] getShortWeekdays() {
    	return SHORT_WEEKDAYS;
    }

    public Locale[] getSupportedLocales() {
    	return SUPPORTED_LOCALES;
    }

    public String getTimeFull() {
    	return TIME_FULL;
    }

    public String getTimeLong() {
        return TIME_LONG;
    }

    public String getTimeMedium() {
    	return TIME_MEDIUM;
    }

    public String getTimeShort() {
    	return TIME_SHORT;
    }

    public String[] getWeekdays() {
        return WEEKDAYS;
    }

    public char getZeroDigit() {
    	return ZERO_DIGIT;
    }

    public Collator getCollator() {
        return Collator.getInstance(si_LK_LOCALE); 												// locale
    }    
    
    public String getTimeZone(String ID, boolean daylight, int style){

        if (ID.equalsIgnoreCase("Asia/Colombo") || TimeZone.getTimeZone(ID).getID().equals("GMT+05:30")){
            
            if (style == TimeZone.SHORT){
                return SHORT_TIMEZONE_NAME;
            }else{
                return LONG_TIMEZONE_NAME;
            }

        }else{

            return TimeZone.getTimeZone(ID).getDisplayName(daylight, style);
            
        }
    }

    public String[] getAmPmStrings(){
        return AM_PM_STRING;
    }
}
