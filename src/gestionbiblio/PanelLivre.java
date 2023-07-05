/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gestionbiblio;

import com.cell.CentredTableCellRender;
import com.cell.PlaceholderTextField;
import com.cell.TableActionCellEditor;
import com.cell.TableActionCellEditorLivre;
import com.cell.TableActionCellRender;
import com.cell.TableActionCellRenderLivre;
import com.cell.TableActionEvent;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Container;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.table.*;

/**
 *
 * @author Mbolatiana
 */
public class PanelLivre extends javax.swing.JPanel {

    
     private static final String usernamme="root";
   private static final String password="";
   private static final String dataConn="jdbc:mysql://localhost/baseprojet";
   private String optionRecherchePrecedente = "Titre";

   
   private static biblio bi;
   private String lastId;
   Connection sqlConn=null;
   PreparedStatement pst=null;
   ResultSet rs= null;
   PreparedStatement pstdel=null;
   ResultSet rsdel= null;
    /**
     * Creates new form PanelLivre
     * @param bi
     */
    public PanelLivre(biblio bi) {
        this.bi = bi;
        Connection con1;
        initComponents();
 
         updateDB1();
         

         
          //centrer les elements du tableau
         /***************************************/
        for (int i = 0; i < tableLivre.getColumnCount(); i++) {
            tableLivre.getColumnModel().getColumn(i).setCellRenderer(new CentredTableCellRender());
        }
        /**************************************/
    
        
         TableActionEvent event = new TableActionEvent() {
            
            
               @Override
               public void onEdit(int row,Object o) {
                   System.out.println("regarder ligne " + o);
                    //recuperer d abord le nom de l utilisateur
                   try {
                       pst = sqlConn.prepareStatement("Select titre,remarque from livre WHERE numLivre='"+o+"'");
                       rs = pst.executeQuery();
                     String remarque = null;
                     String titre = null;
                     while(rs.next()){
                         titre= rs.getString("Titre");
                         remarque= rs.getString("remarque");
                     }
//                  System.out.println(remarque);
                        PanelLivre panLivre = PanelLivre.this;
                     FenRemarqueLivre fen = new FenRemarqueLivre(bi,panLivre,o,titre,remarque);
                     fen.setAlwaysOnTop(true);
                     bi.setEnabled(false);
                     fen.setVisible(true);
//                     onViewButtonClicked(o,nom);   
                      } catch (SQLException ex) {
                       ex.printStackTrace();
                   }
               }
               
                    @Override
               public void onView(int row,Object o) {
                
               }
                @Override
               public void onReturn(int row,Object o) {
                
               }
               @Override
               public void onDelete(int row,Object o) {
                  

               }
        };
    tableLivre.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRenderLivre());
        tableLivre.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditorLivre(event));
        
        //.....................................................................................
        // Ajoutez un écouteur d'événements pour détecter les changements de sélection dans le JComboBox
                    choixRechercheLivre.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Lorsque l'option est modifiée, appelez la fonction de recherche correspondante
                            rechercherNumeroLivre(rechercheLivre.getText());
                        }
                    });

// ...............................................................


                        ////recherche lecteur////////////////////////////////////////////
           //         RechercheLecteur = new PlaceholderTextField("numLecteur");

         
  rechercheLivre.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        rechercherNumeroLivre(rechercheLivre.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        rechercherNumeroLivre(rechercheLivre.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Ne sera pas appelé pour les champs de texte simples
    }
});

         
           ///////////////////////////////////////////////////////////////////////////
//  System.out.println( choixRechercheLivre.getSelectedItem());
    }
    
 
private void rechercherNumeroLivre(String numeroLivre) {
    String choixRecherche = (String) choixRechercheLivre.getSelectedItem();

    if (choixRecherche.equals("Titre")) {
        rechercherParTitre(numeroLivre);
    } else if (choixRecherche.equals("Auteur")) {
        rechercherParAuteur(numeroLivre);
    }
}

private void rechercherParTitre(String titre) {
    DefaultTableModel model = (DefaultTableModel) tableLivre.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tableLivre.setRowSorter(sorter);

    if (titre.trim().isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + titre, 1)); // Recherche par titre, ignore la casse
    }
}

private void rechercherParAuteur(String auteur) {
    DefaultTableModel model = (DefaultTableModel) tableLivre.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tableLivre.setRowSorter(sorter);

    if (auteur.trim().isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + auteur, 2)); // Recherche par auteur, ignore la casse
    }
}




/****************************************************/
    //////////////////////////////////////////////////////////////////////////////
    //Mis a jour table Livre
     
 @SuppressWarnings("unchecked")
 
     public void updateDB1(){

        int c;
          Connection con1;
        PreparedStatement insert;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
            
            pst = sqlConn.prepareStatement("Select * from livre ORDER BY CAST(SUBSTRING(numLivre,3) as UNSIGNED) DESC");
            rs = pst.executeQuery();
           ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();
           
            c=stData.getColumnCount() +1;
            
            DefaultTableModel RecordTable= (DefaultTableModel)tableLivre.getModel();
            RecordTable.setRowCount(0);
           
            while(rs.next()){
            
            Vector v2 = new Vector();
           
            for(int a=1; a<=c;a++)     
            {
                this.tableLivre.setDefaultRenderer(JButton.class, new TableComponent());
                v2.add(lastId = rs.getString("numLivre"));   //prendre le dernier valeur de numLivre afin de ;l' incrementer         
                v2.add(rs.getString("Titre"));
                v2.add(rs.getString("Auteur"));
                v2.add(rs.getString("date_edition"));
                v2.add(rs.getString("Disponible"));
                v2.add(rs.getString("NbFoisPret"));
                v2.add(rs.getString("remarque"));
                
          }
            
          RecordTable.addRow(v2);
            }
            

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }   

    }  
     ////////////////////////////////////////////////////////////////////////////////////////

   
     

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableLect = new javax.swing.JTable();
        btn_ajout_lect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableLivre = new javax.swing.JTable();
        btn_ajout_livre = new javax.swing.JButton();
        rechercheLivre = new javax.swing.JTextField();
        choixRechercheLivre = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("LIVREEE");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Recherche Lecteur :");

        TableLect.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NUMERO LECTEUR", "NOM LECTEUR", "ACTION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableLect.setAlignmentX(1.0F);
        TableLect.setRowHeight(50);
        TableLect.setSelectionBackground(new java.awt.Color(143, 91, 71));
        TableLect.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(TableLect);

        btn_ajout_lect.setText("Ajouter");
        btn_ajout_lect.setFocusPainted(false);
        btn_ajout_lect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ajout_lectMouseClicked(evt);
            }
        });

        setBackground(new java.awt.Color(0, 51, 51));
        setMaximumSize(new java.awt.Dimension(827, 614));
        setMinimumSize(new java.awt.Dimension(827, 614));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Recherche Ouvrage:");

        tableLivre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N°Livre", "Design", "Auteur", "Date_Edit", "Dispo", "NbFoisPret", "Remarque", "Edition_Remarque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLivre.setAlignmentX(1.0F);
        tableLivre.setRowHeight(50);
        tableLivre.setSelectionBackground(new java.awt.Color(143, 91, 71));
        tableLivre.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tableLivre);

        btn_ajout_livre.setText("Ajouter");
        btn_ajout_livre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ajout_livre.setFocusPainted(false);
        btn_ajout_livre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ajout_livreMouseClicked(evt);
            }
        });
        btn_ajout_livre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_livreActionPerformed(evt);
            }
        });

        choixRechercheLivre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titre", "Auteur" }));

        jLabel2.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LISTE DES LIVRES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_ajout_livre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(269, 269, 269))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rechercheLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(choixRechercheLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(149, 149, 149))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rechercheLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choixRechercheLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btn_ajout_livre, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ajout_lectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_lectMouseClicked
//
//        FenAjoutLecteur fenetre= new FenAjoutLecteur();
//        fenetre.setVisible(true);

    }//GEN-LAST:event_btn_ajout_lectMouseClicked

    private void btn_ajout_livreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_livreMouseClicked

        PanelLivre panelLivre = this;
FenAjoutLivre fenAjoutLivre = new FenAjoutLivre(bi,panelLivre,lastId);
fenAjoutLivre.setAlwaysOnTop(true);
bi.setEnabled(false);
fenAjoutLivre.setVisible(true);

    }//GEN-LAST:event_btn_ajout_livreMouseClicked

    private void btn_ajout_livreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_livreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ajout_livreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableLect;
    private javax.swing.JButton btn_ajout_lect;
    private javax.swing.JButton btn_ajout_livre;
    private javax.swing.JComboBox<String> choixRechercheLivre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField rechercheLivre;
    private javax.swing.JTable tableLivre;
    // End of variables declaration//GEN-END:variables
}
