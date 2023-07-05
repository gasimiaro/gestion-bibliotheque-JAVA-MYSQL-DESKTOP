/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionbiblio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

 
public class FenAjoutLecteur extends javax.swing.JFrame {
   private static PanelLecteur panLec;
   private static biblio bi;
   private static String lastId;
   private int nouveauId;
   
    public FenAjoutLecteur(biblio bi,PanelLecteur panLec,String lastId) {
        this.bi = bi;
        this.panLec=panLec;
        this.lastId = lastId;
        initComponents();
       btn_ajout_lecteur.setEnabled(false);
         //********************************************************************
        
        //mis en place du nouveau Id     
        this.lastId = lastId.substring(3);
        nouveauId = Integer.parseInt(this.lastId)+1;
        txtNumLecteur.setText("LE0"+(nouveauId));
        //******************************************************************
   
              txtnomLecteur.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        desactiveBtn(); 
       // Pattern.matches("a-zA-Z",txtnomLecteur.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
desactiveBtn();     }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Ne sera pas appelé pour les champs de texte simples
desactiveBtn();    }
});
    }
public  void desactiveBtn(){
         if ( !txtnomLecteur.getText().isEmpty() && Pattern.matches("[a-zA-Z]+",txtnomLecteur.getText()) ){
             btn_ajout_lecteur.setEnabled(true);
         }else{
             btn_ajout_lecteur.setEnabled(false);
         }
         
         
//         if(!Pattern.matches("[a-zA-Z]",txtnomLecteur.getText())){
// btn_ajout_lecteur.setEnabled(false);             
//         }
//         else{
//                          System.out.println("tsy diso");
//                           btn_ajout_lecteur.setEnabled(true);             
//
//
//         }
                 
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtnomLecteur = new javax.swing.JTextField();
        btn_ajout_lecteur = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumLecteur = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(204, 204, 255));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel3.setText("Nom                     :");

        txtnomLecteur.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtnomLecteurInputMethodTextChanged(evt);
            }
        });

        btn_ajout_lecteur.setText("Ajouter");
        btn_ajout_lecteur.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ajout_lecteur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ajout_lecteurMouseClicked(evt);
            }
        });
        btn_ajout_lecteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_lecteurActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("AJOUT DE LECTEUR");

        jLabel2.setText("N umero Lecteur :");

        txtNumLecteur.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnomLecteur, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                            .addComponent(txtNumLecteur)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btn_ajout_lecteur, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumLecteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnomLecteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btn_ajout_lecteur)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ajout_lecteurMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_lecteurMouseClicked

//        Connection con1;
//        PreparedStatement insert;
//        
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            con1 = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
//            String sql="Insert into lecteur(nom) values (?)";
//            insert = con1.prepareStatement(sql);
//            insert.setString(1, txtnomLecteur.getText());
////            insert.setString(2, txtnomLecteur.getText());
//            insert.executeUpdate()  ;
//      
//            JOptionPane.showMessageDialog(null,"ajout avec succes");
//            con1.close();
//            txtnomLecteur.setText("");
//            
//            panLec.updateDB();
//            bi.setEnabled(true);
//            this.dispose();
//
//        }
//
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(null,e);
//        }        
    }//GEN-LAST:event_btn_ajout_lecteurMouseClicked

    private void btn_ajout_lecteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_lecteurActionPerformed
        // TODO add your handling code here:
           Connection con1;
        PreparedStatement insert;
        if(txtnomLecteur.getText() == ""){
            this.setEnabled(false);
        }
        else{
             try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con1 = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
            String sql="Insert into lecteur(numLecteur,nom) values (?,?)";
            insert = con1.prepareStatement(sql);
            insert.setString(1, txtNumLecteur.getText());
            insert.setString(2, txtnomLecteur.getText());
//            insert.setString(2, txtnomLecteur.getText());
            insert.executeUpdate();
             con1.close();
            this.setAlwaysOnTop(false);
            JOptionPane.showMessageDialog(null,"ajout avec succes");
            this.setAlwaysOnTop(true);
            txtNumLecteur.setText("LE0"+(nouveauId = nouveauId +1));
            txtnomLecteur.setText("");
            
            panLec.updateDB();

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }  
        }

       
    }//GEN-LAST:event_btn_ajout_lecteurActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        bi.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void txtnomLecteurInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtnomLecteurInputMethodTextChanged
    if(txtnomLecteur.getText()==""){
        btn_ajout_lecteur.setEnabled(false);
        System.out.println("vide");
    }
    else{
        btn_ajout_lecteur.setEnabled(true);
                System.out.println("changer");

    }

    }//GEN-LAST:event_txtnomLecteurInputMethodTextChanged


    public static void main(String args[]) {
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenAjoutLecteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenAjoutLecteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenAjoutLecteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenAjoutLecteur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenAjoutLecteur(bi,panLec,lastId).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ajout_lecteur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtNumLecteur;
    private javax.swing.JTextField txtnomLecteur;
    // End of variables declaration//GEN-END:variables
}
