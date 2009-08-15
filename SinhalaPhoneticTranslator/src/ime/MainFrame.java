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
import java.awt.print.*;
import java.awt.print.PrinterJob;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;


public class MainFrame extends javax.swing.JFrame implements Printable {

    private UIManager.LookAndFeelInfo looks[];
    private Location loc;
    private JFileChooser fileChooser;
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
        String s,v;
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
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IME - Zameer- Faculty of Information Technology - Uni Moratuwa");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input Text", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 13), new java.awt.Color(0, 51, 51))); // NOI18N
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("KandyUnicode", 0, 18));
        jTextArea2.setRows(5);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Output Text"));
        jScrollPane2.setViewportView(jTextArea2);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Load");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Print");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Copy");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Paste");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
JOptionPane.showMessageDialog(null,"This software is a property of,\n"+
         "\" Faculty of Information Technology\n University of Moratuwa\"");
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    System.exit(0);
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

   fileChooser=new JFileChooser();
   fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
   
   int x=fileChooser.showOpenDialog(this);
   fileChooser.setVisible(true);

   if(x!=JFileChooser.CANCEL_OPTION){
       // Option allows you to choose directories only
       jLabel1.setText(fileChooser.getSelectedFile().getPath());
       
       try{
       
           Scanner input=new Scanner(new File(fileChooser.getSelectedFile().getPath()));
           
       while (input.hasNext())
        {
            jTextArea1.append(input.nextLine()+"\n");
            //JOptionPane.showMessageDialog(this,"Hi");
            
        }
       
       translate();
       }
       catch(Exception e){
        jLabel1.setText("File Couldn't be loaded");
        
       }
          
   }

}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jTextArea1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyReleased
    translate();
}//GEN-LAST:event_jTextArea1KeyReleased

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

    if (this.jTextArea1.isFocusOwner()){
        this.jTextArea1.copy();
    }else{
        this.jTextArea2.copy();
    }
    
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
//--- Create a new PrinterJob object
        PrinterJob printJob = PrinterJob.getPrinterJob();
        
        //--- Set the printable class to this one since we
        //--- are implementing the Printable interface
        printJob.setPrintable(this); 
        
        //--- Show a print dialog to the user. If the user
        //--- clicks the print button, then print, otherwise
        //--- cancel the print job
        
        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (Exception PrintException) {
                PrintException.printStackTrace();
            }
        }
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

    this.jTextArea1.paste();

}//GEN-LAST:event_jMenuItem4ActionPerformed

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
       
        
        int i;
        Graphics2D g2d;
        Line2D.Double line = new Line2D.Double();
        
        //--- Validate the page number, we only print the first page
        if (pageIndex == 0) {
            
            //--- Create a graphic2D object and set the default parameters
            g2d = (Graphics2D) g;
            g2d.setColor(Color.black);
            //--- Translate the origin to be (0,0)
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            
            //--- Draw a border around the page using a 12 point border
            g2d.setStroke(new BasicStroke(2));
            Rectangle2D.Double border = new Rectangle2D.Double(0, 0,pageFormat.getImageableWidth(),pageFormat.getImageableHeight());
            g2d.draw(border);
            
            Font font = g2d.getFont();
            //--- Print the horizontal lines
            g2d.drawString("--------------------------------------------------------------------------------------------", POINTS_PER_INCH-20, POINTS_PER_INCH);
            
            g2d.setFont(new Font("KaputaUnicode", 0, 13));
            g2d.drawString(jTextArea2.getText(), POINTS_PER_INCH + 20, POINTS_PER_INCH + 120);
            
            g2d.drawString("--------------------------------------------------------------------------------------------", POINTS_PER_INCH-20, POINTS_PER_INCH + 140);

            return (PAGE_EXISTS);
        } else
            return (NO_SUCH_PAGE);
        
    
    
    }

}
