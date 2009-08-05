/*
 * MainFrame.java
 *
 * Created on June 30, 2009, 3:58 PM
 */

package ime;

/**
 *
 * @author  Zameer MFM (064111c)
 * Faculty of Information Technology
 * University of Moratuwa
 * Sri Lanka.
 * e-mail : zameer@bcs.org.uk
 */
import javax.swing.*;
import java.util.*;
import java.awt.Font;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MainFrame extends javax.swing.JFrame {

    private UIManager.LookAndFeelInfo looks[];
    private Location loc;
    
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

    
    /** Creates new form MainFrame */
    public MainFrame() {
        looks = UIManager.getInstalledLookAndFeels();
        try
        {
            UIManager.setLookAndFeel( looks[2].getClassName() );
            SwingUtilities.updateComponentTreeUI( this );

        }
        catch( Exception exception )
        {
            exception.printStackTrace();
        }
        initComponents();
        
        loc=new Location(this.getWidth(),this.getHeight());  
        this.setLocation(loc.getPoint());
        
        initLanguageSettings();
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
        //last because we need to ommit this in dealing with Rakaransha
        consonantsUni[45]="ර"; consonants[45]="r";

        specialCharUni[0]="ෲ"; specialChar[0]="ruu";
        specialCharUni[1]="ෘ"; specialChar[1]="ru";
    }
    
    
    private void translate(){
        String s,r,v;
        text = jTextArea1.getText();  
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

        jTextArea2.setText(text);
        //jTextArea2.setText("meyatuwana");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IME - Faculty of Information Technology - Uni Moratuwa");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Text", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 51, 51))); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("KandyUnicode", 0, 13)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output Text", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jScrollPane2.setViewportView(jTextArea2);

        jButton1.setText("Translate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Item");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Item");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenu3.setText("Menu");
        jMenu2.add(jMenu3);

        jMenu4.setText("Menu");
        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Menu");

        jMenuItem3.setText("About");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
JOptionPane.showMessageDialog(null,"This software is a property of,\n"+
         "\" Faculty of Information Technology\n University of Moratuwa\"");
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    translate();
}//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

}
