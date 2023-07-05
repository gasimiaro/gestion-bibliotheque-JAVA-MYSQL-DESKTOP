/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gestionbiblio;

/**
 *
 * @author Mbolatiana
 */
import com.cell.ActionPanelPret;
import com.cell.ButtonCellEditor;
import com.cell.ButtonRenderer;
import com.cell.DateRenderer;
import com.cell.ButtonEditor;
import com.cell.CentredTableCellRender;
import com.cell.PlaceholderTextField;
import com.cell.TableActionCellEditor;
import com.cell.TableActionCellRender;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import com.cell.CentredTableCellRender;
import com.cell.TableActionCellEditorPret;
import com.cell.TableActionCellEditorPret;
import com.cell.TableActionCellRenderLivre;
import com.cell.TableActionCellRenderPret;
import com.cell.TableComponent;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTextField;



public class PanelPret extends javax.swing.JPanel {
   public DefaultTableModel RecordTablePret;
   
   private static biblio bi;
   Connection sqlConnPret=null;
   PreparedStatement pstPret=null;
   ResultSet rsPret= null;
   private TableActionEvent event;
   private String retour;
   private String lastId;
     JButton button = new JButton();

   

    /**
     * Creates new form PanelPret
     */
    public PanelPret(biblio bi) {
        this.bi = bi;
        initComponents();
//        TableColumn dateRetourColumn = tablePret.getColumnModel().getColumn(4); // Récupérer la colonne "DateRetour"
//dateRetourColumn.setCellEditor(new DefaultCellEditor(new JTextField())); // Utiliser un éditeur par défaut qui ne permet pas l'édition

         updateDBPret();
        
        
        //centrer les elements du tableau
         /***************************************/
        for (int i = 0; i < tablePret.getColumnCount(); i++) {
            tablePret.getColumnModel().getColumn(i).setCellRenderer(new CentredTableCellRender());        }

        /**************************************/
    
//***************************************************************************************************************************************************
    //ajout des boutons dans la table
    event = new TableActionEvent() {
            
             @Override
               public void onReturn(int row,Object o) {
                System.out.println("tafiditra");
               }
               @Override
               public void onEdit(int row,Object o) {
                  System.out.println("tafiditra2");
               }
               
                    @Override
               public void onView(int row,Object o) {
                
               }
               @Override
               public void onDelete(int row,Object o) {
                  

               }
        };
      // Définir le rendu personnalisé pour la colonne "Dateretour"
       PanelPret panPre = PanelPret.this;
//        TableCellRenderer dateRenderer = new DateRenderer(panPre);
//        tablePret.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderPret());
//        tablePret.setDefaultRenderer(Object.class, new DateRenderer());

//        tablePret.getColumnModel().getColumn(4).setCellRenderer(dateRenderer);
//         tablePret.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditorPret(event));
        
         
         tablePret.getColumnModel().getColumn(4).setCellRenderer(new DateRenderer(bi,panPre));
tablePret.getColumnModel().getColumn(4).setCellEditor(new ButtonCellEditor(bi,panPre));

            //.....................................................................................
                    // Ajoutez un écouteur d'événements pour détecter les changements de sélection dans le JComboBox
                                cbchoixRecherche.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        // Lorsque l'option est modifiée, appelez la fonction de recherche correspondante
                                        rechercherNumeroPret(recherchePret.getText());
                                    }
                                });

            // ...............................................................


                                    ////recherche lecteur////////////////////////////////////////////
                       //         RechercheLecteur = new PlaceholderTextField("numLecteur");


              recherchePret.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    rechercherNumeroPret(recherchePret.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    rechercherNumeroPret(recherchePret.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // Ne sera pas appelé pour les champs de texte simples
                }
            });

 
    }
//*****************************************************************************************************************************************************
//****************************************************************************************************************************************************    
//    public class ButtonEditor extends DefaultCellEditor 
//  {
//    private String label;
//   
//    
//    public ButtonEditor(JCheckBox checkBox)
//    {
//        super(checkBox);
//     
//      
//    }
//
//    public Component getTableCellEditorComponent(JTable table, Object value,
//    boolean isSelected, int row, int column) 
//    {
//      label = (value == null) ? value.toString() :"RENDRE" ;
//      button.setText(label);
//      return button;
//    }
//
//    public Object getCellEditorValue() 
//    {
//      return new String(label);
//    }
//  }
//    Numero Pret, Numero Lecteur, Numero Livre
    
            private void rechercherNumeroPret(String numeroPret) {
                String choixRecherche = (String) cbchoixRecherche.getSelectedItem();

                if (choixRecherche.equals("Numero Pret")) {
                    rechercherParNumPret(numeroPret);
                } else if (choixRecherche.equals("Numero Lecteur")) {
                    rechercherParNumLecteur(numeroPret);
                }
                else if (choixRecherche.equals("Numero Livre")) {
                    rechercherParNumLivre(numeroPret);
                }
            }

            private void rechercherParNumPret(String NumPret) {
                DefaultTableModel model = (DefaultTableModel) tablePret.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tablePret.setRowSorter(sorter);

                if (NumPret.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + NumPret, 0)); // Recherche par titre, ignore la casse
                }
            }

            private void rechercherParNumLecteur(String numLec) {
                DefaultTableModel model = (DefaultTableModel) tablePret.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tablePret.setRowSorter(sorter);

                if (numLec.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + numLec, 1)); // Recherche par auteur, ignore la casse
                }
            }
             private void rechercherParNumLivre(String numLivre) {
                DefaultTableModel model = (DefaultTableModel) tablePret.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tablePret.setRowSorter(sorter);

                if (numLivre.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + numLivre, 2)); // Recherche par auteur, ignore la casse
                }
            }
//************************************************************************************************************************************************8
    
    
     public void updateDBPret(){
        
        int c;
          Connection con1;
        PreparedStatement insert;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConnPret = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
            
            pstPret = sqlConnPret.prepareStatement("Select * from pret ORDER BY CAST(SUBSTRING(N_Pret,3) as UNSIGNED)");
            rsPret = pstPret.executeQuery();
           ResultSetMetaData stData = (ResultSetMetaData) rsPret.getMetaData();
           
            c=stData.getColumnCount() +1;
            
             RecordTablePret= (DefaultTableModel)tablePret.getModel();
            RecordTablePret.setRowCount(0);
           
            while(rsPret.next()){
            
            Vector v3 = new Vector();
           
            for(int a=1; a<=c;a++)    
            {
                   

                    v3.add(lastId = rsPret.getString("N_Pret"));
                    v3.add(rsPret.getString("numLecteur"));
                    v3.add(rsPret.getString("numLivre"));
                    v3.add(rsPret.getString("DatePret"));
                    retour = rsPret.getString("DateRetour");
//                     System.out.println(retour);
//                    if(!retour.equals("0000-00-00") ){
//                        System.out.println("deja rendu");
                       v3.add(retour); 
//                    }
//                    else{
//                        JButton button = new JButton("Modifier");
//                            button.setActionCommand(retour);
//                              v3.add(button);
//                     tablePret.getColumn("DateRetour").setCellRenderer(new ButtonRenderer());
//    tablePret.getColumn("DateRetour").setCellEditor(new ButtonEditor(new JCheckBox()));
         //  tablePret.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderPret()));

//                    }

            }
            
          RecordTablePret.addRow(v3);
          
          //pour rectifier l affichage des bouttons dans la table
 // Créer le rendu personnalisé

            }
//this.tablePret.getColumn("DateRetour").setCellRenderer(new ButtonRenderer());
// tablePret.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRenderPret());

              

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }        // TODO add your handling code here:
    }     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePret = new javax.swing.JTable();
        btn_ajout_lect = new javax.swing.JButton();
        recherchePret = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbchoixRecherche = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LISTE DES PRETS");

        tablePret.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero Pret", "NumLecteur", "NumLivre", "DatePret", "DateRetour"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePret.setAlignmentX(1.0F);
        tablePret.setRowHeight(50);
        tablePret.setSelectionBackground(new java.awt.Color(143, 91, 71));
        tablePret.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tablePret);

        btn_ajout_lect.setText("Ajouter");
        btn_ajout_lect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ajout_lect.setFocusPainted(false);
        btn_ajout_lect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ajout_lectMouseClicked(evt);
            }
        });
        btn_ajout_lect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_lectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Recherche Prêt :");

        cbchoixRecherche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numero Pret", "Numero Lecteur", "Numero Livre" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(btn_ajout_lect, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(244, 244, 244)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recherchePret, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbchoixRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(recherchePret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbchoixRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_ajout_lect, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ajout_lectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_lectMouseClicked

    }//GEN-LAST:event_btn_ajout_lectMouseClicked

    private void btn_ajout_lectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_lectActionPerformed
        // TODO add your handling code here:
        FenAjoutPret fen = new FenAjoutPret(bi,this,lastId);
        fen.setAlwaysOnTop(true);
        bi.setEnabled(false);
        fen.setVisible(true);
    }//GEN-LAST:event_btn_ajout_lectActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ajout_lect;
    private javax.swing.JComboBox<String> cbchoixRecherche;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField recherchePret;
    private javax.swing.JTable tablePret;
    // End of variables declaration//GEN-END:variables
}
