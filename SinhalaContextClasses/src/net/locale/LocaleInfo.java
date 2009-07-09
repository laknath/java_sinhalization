/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.locale;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

/**
 *
 * @author Buddhika Laknath
 */
public interface LocaleInfo extends Serializable{
	void checkLocaleSupported(Locale locale);

	String[][] getCountryTuples();

	String getCurrencyFormat();

	String getCurrencySymbol();

	String getDateFull();

	String getDateLong();

	String getDateMedium();

	String getDateShort();

	char getDecimalSeparator();

	char getDigit();

	String[] getEras();

	String getExponentSeparator();

	char getGroupingSeparator();

	String getInfinity();

	String getIntegerFormat();

	String[][] getLanguageTuples();

	char getMinusSign();

	String[] getMonths();

	String getNaN();

	String getNumberFormat();

	String getPatternChars();

	char getPatternSeparator();

	char getPercent();

	String getPercentFormat();

	char getPerMille();

	String[] getShortMonths();

	String[] getShortWeekdays();

	Locale[] getSupportedLocales();

	String getTimeFull();

	String getTimeLong();

	String getTimeMedium();

	String getTimeShort();

	String[] getWeekdays();

	char getZeroDigit();

	Collator getCollator();

}
