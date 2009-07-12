/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sinhalacontextexample;

import java.awt.event.ItemEvent;
import java.util.Locale;

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
    }


    public void onItemSelected(ItemEvent evt){
        String selectedItem = view.jComboBox1.getSelectedItem().toString();
        String[] localeParts = selectedItem.split("_");

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

        System.out.println(selectedItem);
    }
    

}
