/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sinhalacontextexample;

import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author buddhika
 */
public class Functions {

    private SinhalaContextExampleView view;
    private Locale locale;

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

    }


    public void onItemSelected(ItemEvent evt){
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
    }

    private void setSinhalaFont(){
        this.view.jTextField2.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField3.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
        this.view.jTextField4.setFont(new java.awt.Font("KaputaUnicode", 0, 13));
    }

    private void setDefaultFont(){
        this.view.jTextField2.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField3.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        this.view.jTextField4.setFont(new java.awt.Font("DejaVu Sans", 1, 13));

    }

}
