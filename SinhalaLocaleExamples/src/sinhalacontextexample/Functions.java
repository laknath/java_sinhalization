/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sinhalacontextexample;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JComboBox;

/**
 *
 * @author buddhika
 */
public class Functions {

    private SinhalaContextExampleView view;
    private static Locale locale;

    public Functions(SinhalaContextExampleView view){
        this.view = view;

        Locale list[] = Locale.getAvailableLocales();
        for (Locale aLocale : list) {
            this.view.jComboBox1.addItem(aLocale.toString()) ;
        }

        String[] ids = TimeZone.getAvailableIDs();

        for (String id : ids){
            System.out.println(id);
        }

        this.onItemSelected();

    }


    @SuppressWarnings("static-access")
    public void onItemSelected(){
        String selectedItem = view.jComboBox1.getSelectedItem().toString();
        String[] localeParts = selectedItem.split("_");

        //change the font to sinhala
        if (selectedItem.equals("si_LK")){
            System.out.println("testing");
            setSinhalaFont();
        }else{
            setDefaultFont();
        }


        if (localeParts.length == 1){

            this.locale = new Locale(localeParts[0]);

        }
        else if (localeParts.length == 2){

            this.locale = new Locale(localeParts[0], localeParts[1]);

        }
        else if (localeParts.length == 3){

            this.locale = new Locale(localeParts[0], localeParts[1], localeParts[2]);
            
        }


        /* Demo Locale.getDisplayLanguage(java.util.Locale) */
        this.view.jTextField2.setText(this.locale.getDisplayLanguage(this.locale));

        /* Demo Locale.getDisplayCountry(java.util.Locale) */
        this.view.jTextField3.setText(this.locale.getDisplayCountry(this.locale));

        /* Demo Locale.getDisplayVariant(java.util.Locale) */
        this.view.jTextField4.setText(this.locale.getDisplayVariant(this.locale));


        TimeZone zone = TimeZone.getTimeZone("Asia/Colombo");


        /* Demo TimeZone.getDisplayName(true, TimeZone.SHORT, locale) */
        this.view.jTextField5.setText(zone.getDisplayName(true, TimeZone.SHORT, this.locale));

        /* Demo TimeZone.getDisplayName(true, TimeZone.LONG, locale) */
        this.view.jTextField6.setText(zone.getDisplayName(false, TimeZone.LONG, this.locale));

        System.out.println(selectedItem);

        /*Demo for DateFormatProvider */

        /* DateFormat.getTimeInstance(int style, Locale locale) */
        this.view.jTextField7.setText(DateFormat.getTimeInstance(this.getDateStyle((String)this.view.jComboBox2.getSelectedItem()),
                                        this.locale).format(new Date()));

        /* DateFormatProvider.getDateInstance(int style, Locale locale) */
        this.view.jTextField15.setText(DateFormat.getDateInstance(this.getDateStyle((String)this.view.jComboBox6.getSelectedItem()),
                                        this.locale).format(new Date()));

        /* DateFormat.getDateTimeInstance(int dateStyle, int timeStyle, Locale locale) */
        this.view.jTextField9.setText(DateFormat.getDateTimeInstance(this.getDateStyle((String)this.view.jComboBox4.getSelectedItem()),
                                        this.getDateStyle((String)this.view.jComboBox5.getSelectedItem()),
                                        this.locale).format(new Date()));

        /* Demo for DateFormatSymbolProvider */
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);

        addItems(dfs.getEras(), this.view.jComboBox10);
        addItems(dfs.getAmPmStrings(), this.view.jComboBox11);
        addItems(dfs.getMonths(), this.view.jComboBox3);
        addItems(dfs.getShortMonths(), this.view.jComboBox7);
        addItems(dfs.getWeekdays(), this.view.jComboBox8);
        addItems(dfs.getShortWeekdays(), this.view.jComboBox9);
        this.view.jTextField28.setText(dfs.getLocalPatternChars());

        /* Demo for CurrencyNameProvider */
        Currency c = Currency.getInstance(locale);
        this.view.jTextField10.setText(c.getSymbol(locale));

        /* Demo for NumberFormatProvider */
        this.view.jTextField16.setText("0.00");

        this.view.jTextField11.setText(NumberFormat.getCurrencyInstance(locale).format(0.00));
        this.view.jTextField12.setText(NumberFormat.getIntegerInstance(locale).format(0.00));
        this.view.jTextField13.setText(NumberFormat.getNumberInstance(locale).format(0.00));
        this.view.jTextField14.setText(NumberFormat.getPercentInstance(locale).format(0.00));

        /* Demo for DecimalFormatProvider */
        DecimalFormatSymbols df = new DecimalFormatSymbols(locale);

        this.view.jTextField21.setText(String.valueOf(df.getDecimalSeparator()));
        this.view.jTextField22.setText(String.valueOf(df.getDigit()));
        this.view.jTextField23.setText(String.valueOf(df.getExponentSeparator()));
        this.view.jTextField24.setText(String.valueOf(df.getGroupingSeparator()));
        this.view.jTextField29.setText(String.valueOf(df.getInfinity()));
        this.view.jTextField30.setText(String.valueOf(df.getInternationalCurrencySymbol()));
        this.view.jTextField31.setText(String.valueOf(df.getMinusSign()));
        this.view.jTextField33.setText(String.valueOf(df.getNaN()));
        this.view.jTextField34.setText(String.valueOf(df.getPatternSeparator()));
        this.view.jTextField35.setText(String.valueOf(df.getPercent()));
        this.view.jTextField36.setText(String.valueOf(df.getPerMill()));
        this.view.jTextField37.setText(String.valueOf(df.getZeroDigit()));


    }

    private void setSinhalaFont(){
        this.view.jTextField2.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField3.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField4.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField7.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField15.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField9.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox10.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox11.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox3.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox7.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox8.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jComboBox9.setFont(new java.awt.Font("KaputaUnicode", 0, 13));

    }

    private void setDefaultFont(){
        this.view.jTextField2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField4.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField7.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField15.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField9.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox10.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox11.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox7.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox8.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jComboBox9.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
    }

    /* return date style for a given string */
    public static int getDateStyle(String text){

        int style = 0;

        if (text.equals("SHORT")){
            style = DateFormat.SHORT;
        }else if (text.equals("MEDIUM")){
            style = DateFormat.MEDIUM;
        }else if (text.equals("LONG")){
            style = DateFormat.LONG;
        }else if (text.equals("FULL")){
            style = DateFormat.FULL;
        }

        return style;
    }

    public static Locale getLocale(){
        return locale;
    }

    private static void addItems(String[] array, JComboBox box){

        box.removeAllItems();

        for (int i=0;i < array.length; i++){
            box.addItem(array[i]);
        }

    }
}
