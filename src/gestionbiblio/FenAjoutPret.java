/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionbiblio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;

/**
 *
 * @author G A S I M I A R O
 */
public class FenAjoutPret extends javax.swing.JFrame {

    /**
     * Creates new form FenAjoutPret
     */
     private static PanelPret panelPret; // Ajout de la référence au PanelLivre
     private static String lastId;
     private int nouveauId;
     private int nBlivreNonRendu;
//     private static int numPret;
     private static biblio bi;
     
                // Créer une liste pour stocker les numéros de lecteur
           java.util.List<String> numerosLecteur = new java.util.ArrayList<>();
           java.util.List<String> numerosLivre = new java.util.ArrayList<>();

           // Connexion à la base de données
           Connection connection = null;
           Statement statement = null;
           PreparedStatement pst=null;
//            ResultSet resultSetPret = null;
            PreparedStatement pstNb=null;
            ResultSet rsNb= null;
           ResultSet resultSetLecteur = null;
           ResultSet resultSetLivre = null;
     
     
    public FenAjoutPret(biblio bi,PanelPret panelPret,String lastId) {
       
        this.bi = bi;
        this.panelPret=panelPret;
       this.lastId = lastId;
        initComponents();
        
              //********************************************************************
        
        //mis en place du nouveau Id     
        this.lastId = lastId.substring(3);
        nouveauId = Integer.parseInt(this.lastId)+1;
        numAjoutPret.setText("PR0"+(nouveauId));
         
        //******************************************************************
//*************************************************************************************************************************************************************************/
//inititaliser les combobox par lecteur et livre
        
                                                    try {
                             // Charger le pilote JDBC
                             Class.forName("com.mysql.cj.jdbc.Driver");

                             // Établir la connexion à la base de données
                             connection = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");

                             // Créer une déclaration SQL
                             statement = connection.createStatement();

                                    String queryLecteur = "SELECT numLecteur FROM lecteur ORDER BY CAST(SUBSTRING(numLecteur,3) as UNSIGNED)";
                                    String queryLivre = "SELECT numLivre FROM livre WHERE disponible='OUI' ORDER BY CAST(SUBSTRING(numLivre,3) as UNSIGNED)";
//                                    String queryPret = "SELECT N_Pret FROM pret ORDER BY N_Pret DESC LIMIT 1";

                                    // Parcourir les résultats et ajouter les numéros de pret 
//                                     resultSetPret = statement.executeQuery(queryPret);
//                                    while (resultSetPret.next()) {
//                                        String numPret = resultSetPret.getString("N_Pret");
//                                        this.numPret =Integer.parseInt(numPret)+1;
//                                        numAjoutPret.setText(Integer.toString(this.numPret));
//                                    }
                                    // Parcourir les résultats et ajouter les numéros de lecteur à la liste
                                     resultSetLecteur = statement.executeQuery(queryLecteur);
                                    while (resultSetLecteur.next()) {
                                        String numLecteur = resultSetLecteur.getString("numLecteur");
                                        numerosLecteur.add(numLecteur);
                                    }
                                    
                                     // Parcourir les résultats et ajouter les numéros de livre à la liste
                                    resultSetLivre = statement.executeQuery(queryLivre);
                                    while (resultSetLivre.next()) {
                                        String numLivre = resultSetLivre.getString("numLivre");
                                        numerosLivre.add(numLivre);
                                    }

                             // Convertir les listes de numéros de lecteur et de numéros de livre en tableaux
                             String[] numerosLecteurArray = numerosLecteur.toArray(new String[0]);
                             String[] numerosLivreArray = numerosLivre.toArray(new String[0]);

                             // Ajouter les numéros de lecteur et les numéros de livre aux listes déroulantes
                             numAjoutLecteur.setModel(new javax.swing.DefaultComboBoxModel<>(numerosLecteurArray));
                             numAjoutLivre.setModel(new javax.swing.DefaultComboBoxModel<>(numerosLivreArray));

                         } catch (ClassNotFoundException | SQLException e) {
                             e.printStackTrace();
                         } finally {
                             // Fermer les ressources
                             try {
                                 if (resultSetLecteur != null) {
                                     resultSetLecteur.close();
                                 }
                                 if (statement != null) {
                                     statement.close();
                                 }
                                 if (connection != null) {
                                     connection.close();
                                 }
                             } catch (SQLException e) {
                                 e.printStackTrace();
                             }
                         }



            
                
//*************************************************************************************************************************************************************************/
//*************************************************************************************************************************************************************************/

       
        Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = dateFormat.format(date);
    dateAjoutPret.setText(formattedDate);
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser2 = new com.raven.datechooser.DateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numAjoutLivre = new javax.swing.JComboBox<>();
        numAjoutLecteur = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        numAjoutPret = new javax.swing.JTextField();
        dateAjoutPret = new javax.swing.JTextField();
        btnDatePret = new javax.swing.JButton();
        btnAjoutPret = new javax.swing.JButton();

        dateChooser2.setTextField(dateAjoutPret);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AJOUTER NOUVEAU PRET");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("AJOUT PRET");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel2.setText("Numero Lecteur :");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel3.setText("Numero Livre     :");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setText("Date Pret           :");

        jLabel5.setText("Numero Pret           :");

        numAjoutPret.setEditable(false);

        btnDatePret.setText("...");
        btnDatePret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatePretActionPerformed(evt);
            }
        });

        btnAjoutPret.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnAjoutPret.setText("Ajouter");
        btnAjoutPret.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAjoutPret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutPretActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateAjoutPret, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDatePret))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(numAjoutLivre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(numAjoutLecteur, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(numAjoutPret, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(btnAjoutPret, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numAjoutPret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numAjoutLecteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numAjoutLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dateAjoutPret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDatePret))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btnAjoutPret)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatePretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatePretActionPerformed
        // TODO add your handling code here:
        dateChooser2.showPopup();
    }//GEN-LAST:event_btnDatePretActionPerformed

    private void btnAjoutPretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutPretActionPerformed
        // TODO add your handling code here:
        // Récupérer les valeurs des champs
        String numPret = numAjoutPret.getText();
        String numLecteur = numAjoutLecteur.getSelectedItem().toString();
        String numLivre = numAjoutLivre.getSelectedItem().toString();
        String datePret = dateAjoutPret.getText();
        
        // Formater la date au format 'yyyy-MM-dd'
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
       

        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");

            // Créer une déclaration SQL
            statement = connection.createStatement();
            //converitir la date
             Date parsedDate = sdf.parse(datePret);
            SimpleDateFormat sdfFormatted = new SimpleDateFormat("yyyy-MM-dd");
            datePret = sdfFormatted.format(parsedDate);

            // Créer la requête SQL INSERT
            String query = "INSERT INTO pret (N_Pret, numLecteur, numLivre, datePret,dateretour) VALUES ( '" + numPret + "','" + numLecteur + "', '" + numLivre + "', '" + datePret + "','0000-00-00')";
            
            //compter le nombre de livre non rendu
            String queryNbLivre ="select count(numLecteur) as nb FROM pret where numLecteur='"+numLecteur+"' AND DateRetour='0000-00-00';";
           pstNb = connection.prepareStatement(queryNbLivre);
            rsNb = pstNb.executeQuery();
            while(rsNb.next()){
                nBlivreNonRendu = Integer.parseInt(rsNb.getString("nb"));
            }
            
            //un lecteur doit pas avoir possession plus de 3 livres
            if(nBlivreNonRendu >= 3 ){
                      this.setAlwaysOnTop(false);
                     JOptionPane.showMessageDialog(null, "Vous deviez d'abord rendre vos Livre s'il vous plait");
                    // JOptionPane.showMessageDialog(null, "Vous deviez d'abord rendre vos Livre, le maximum est 3", "nombre pret atteint",JOptionPane.ERROR_MESSAGE,  new ImageIcon("/com/cell/error.png"));

                     this.setAlwaysOnTop(true);
            }
            else{
                            //rendre non disponible le livre prêtés et augmenter le nombre de pret de 1
                       pst = connection.prepareStatement(" UPDATE livre set disponible='NON',NbFoisPret= NbFoisPret + 1,remarque='en Lecture' WHERE numLivre='"+numLivre+"'");
                       pst.executeUpdate();


                       // Exécuter la requête SQL
                       statement.executeUpdate(query);

                       // Afficher un message de succès
                       this.setAlwaysOnTop(false);
                       JOptionPane.showMessageDialog(null, "Ajout de Pret réussi");
                       this.setAlwaysOnTop(true);
                       // augmenter le num Pret de 1
                       numAjoutPret.setText("PR0"+(nouveauId = nouveauId+1));


                       //actualiser la liste des livres  qu'on peut preter
                       numerosLivre.remove(numLivre);
                      numAjoutLivre.setModel(new javax.swing.DefaultComboBoxModel<>(numerosLivre.toArray(new String[0])));

                       panelPret.updateDBPret();
            }
           
        }
         catch (ParseException ex) {
            ex.printStackTrace();
            // Gérer l'erreur de parsing de la date
            return;
        }catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnAjoutPretActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        bi.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenAjoutPret.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenAjoutPret.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenAjoutPret.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenAjoutPret.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenAjoutPret(bi,panelPret,lastId).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjoutPret;
    private javax.swing.JButton btnDatePret;
    private javax.swing.JTextField dateAjoutPret;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox<String> numAjoutLecteur;
    private javax.swing.JComboBox<String> numAjoutLivre;
    private javax.swing.JTextField numAjoutPret;
    // End of variables declaration//GEN-END:variables
}
