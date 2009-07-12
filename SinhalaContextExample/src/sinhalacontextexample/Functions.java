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

    public Functions(SinhalaContextExampleView view){
        this.view = view;

        Locale list[] = Locale.getAvailableLocales();
        for (Locale aLocale : list) {
            this.view.jComboBox1.addItem(aLocale.toString()) ;
        }
    }


    public void onItemSelected(ItemEvent evt){
        String selectedItem = view.jComboBox1.getSelectedItem().toString();

        //testing Locale.getDisplayLanguage(java.util.Locale)
        
        

        System.out.println(selectedItem);
    }
    

}
