/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gestionbiblio;

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

    class TableComponent1 extends DefaultTableCellRenderer {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        if (value instanceof JButton) {
                            return (JButton) value;
                        } else if (value instanceof String) {
                            JButton button = new JButton((String) value);
                            // Configurez le bouton selon vos besoins (par exemple, ajoutez des écouteurs d'événements)
                            return button;
                        }
                        return this;
                        }

            }


public class PanelLecteur extends javax.swing.JPanel {

   private static final String usernamme="root";
   private static final String password="";
   private static final String dataConn="jdbc:mysql://localhost/baseprojet";
   
   
   Connection sqlConn=null;
   PreparedStatement pst=null;
   ResultSet rs= null;
   PreparedStatement pstdel=null;
   ResultSet rsdel= null;
   private static biblio bi;
   private String lastId;
   
    public PanelLecteur(biblio bi) {
        this.bi =  bi;
           Connection con1;
        initComponents();
 
         updateDB();
        
       
         
         //centrer les elements du tableau
         /***************************************/
        for (int i = 0; i < TableLect.getColumnCount(); i++) {
            TableLect.getColumnModel().getColumn(i).setCellRenderer(new CentredTableCellRender());
        }
        /**************************************/
        
        
        TableActionEvent event = new TableActionEvent() {
            
            
            
               @Override
               public void onView(int row,Object o) {
                   System.out.println("regarder ligne " + row);
//                   System.out.println("width : " +bi.getWidth()+";  Height :"+bi.getHeight());
                   bi.setSize(1000, 650);
//                   System.out.println("width : " +bi.getWidth()+";  Height :"+bi.getHeight());
                    //recuperer d abord le nom de l utilisateur
                   try {
                       pst = sqlConn.prepareStatement("Select nom from lecteur WHERE numLecteur='"+o+"'");
                       rs = pst.executeQuery();
                     String nom = null;
                     while(rs.next()){
                         nom= rs.getString("nom");
                     }
                  
                     
                     onViewButtonClicked(o,nom);   
                      } catch (SQLException ex) {
                       Logger.getLogger(PanelLecteur.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }

                @Override
               public void onReturn(int row,Object o) {
                
               }
               @Override
               public void onDelete(int row,Object o) {
                   try{
                       
                   System.out.println(o);
                   if(TableLect.isEditing()){
                       TableLect.getCellEditor().stopCellEditing();
                   }
                   JOptionPane message = new JOptionPane();
                   //recuperer d abord le nom de l utilisateur
                  
                   pst = sqlConn.prepareStatement("Select nom from lecteur WHERE numLecteur='"+o+"'");
                     rs = pst.executeQuery();
                     String nom = null;
                     while(rs.next()){
                         nom= rs.getString("nom");
                     }
                     System.out.println(nom);
                     
                   int reponse = message.showConfirmDialog(null, "Voulez vous supprimer "+nom+" ?","Suppression",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
                   if(reponse==0){
                       
                        System.out.println("supprimer ligne " + row +"  |  "+ o);
                        DefaultTableModel model= (DefaultTableModel) TableLect.getModel();
                        model.removeRow(row);
                   
                       pstdel = sqlConn.prepareStatement(" DELETE FROM lecteur WHERE numLecteur='"+o+"'");
                       pstdel.executeUpdate();
                  //mettre a jour la table apres suppression
                       updateDB();
                   }
                  }catch(Exception e)
                {
                    e.printStackTrace();
                }


               }
               @Override
               public void onEdit(int row,Object o) {
                  
               }
        };
        TableLect.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
        TableLect.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event));
                 ////recherche lecteur////////////////////////////////////////////
//         RechercheLecteur = new PlaceholderTextField("numLecteur");

         RechercheLecteur.getDocument().addDocumentListener(new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        rechercherNumeroLecteur(RechercheLecteur.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        rechercherNumeroLecteur(RechercheLecteur.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Ne sera pas appelé pour les champs de texte simples
    }
});
///////////////////////////////////////////////////////////////////////////
        
    }
//Recherche Lecteur dans la bare de recherche
    /*****************************************************************/
     private void rechercherNumeroLecteur(String numeroLecteur) {
    DefaultTableModel model = (DefaultTableModel) TableLect.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    TableLect.setRowSorter(sorter);

    if (numeroLecteur.trim().isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter(numeroLecteur));
    }
}
/****************************************************/
    @SuppressWarnings("unchecked")
    
    
   public void updateDB(){
        
        int c;
          Connection con1;
        PreparedStatement insert;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
            
            pst = sqlConn.prepareStatement("Select * from lecteur ORDER BY CAST(SUBSTRING(numLecteur,3) as UNSIGNED)");
            rs = pst.executeQuery();
           ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();
           
            c=stData.getColumnCount() +1;
            
            DefaultTableModel RecordTable= (DefaultTableModel)TableLect.getModel();
            RecordTable.setRowCount(0);
           
            while(rs.next()){
            
            Vector v2 = new Vector();
           
            for(int a=1; a<=c;a++)
                
            {
                            this.TableLect.setDefaultRenderer(JButton.class, new TableComponent1());
                            v2.add(lastId = rs.getString("numLecteur"));

                            v2.add(rs.getString("nom"));


            }
            
          RecordTable.addRow(v2);
            }

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }        // TODO add your handling code here:
    }     
                
             // TODO add your handling code here:
              
       
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableLect = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btn_ajout_lect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        RechercheLecteur = new javax.swing.JTextField();

        setBackground(new java.awt.Color(143, 91, 71));

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

        jLabel2.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LISTE DES LECTEURS");

        btn_ajout_lect.setText("Ajouter");
        btn_ajout_lect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ajout_lect.setFocusPainted(false);
        btn_ajout_lect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ajout_lectMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ajout_lectMousePressed(evt);
            }
        });
        btn_ajout_lect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ajout_lectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Recherche Lecteur :");

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
                                .addComponent(RechercheLecteur, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(RechercheLecteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_ajout_lect, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ajout_lectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_lectMouseClicked
       
        FenAjoutLecteur fenetre= new FenAjoutLecteur(bi,this,lastId);
        fenetre.setAlwaysOnTop(true);
//        System.out.println(bi);
        bi.setEnabled(false);
//           this.setEnabled(false);
        fenetre.show();
     
    }//GEN-LAST:event_btn_ajout_lectMouseClicked

    private void btn_ajout_lectMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ajout_lectMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ajout_lectMousePressed

    private void btn_ajout_lectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ajout_lectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ajout_lectActionPerformed

    
   private void onViewButtonClicked(Object o,String nom) {
      
    Container parent = this.getParent();
    parent.remove(this);  // Supprimez le panel actuel
     PanelConsulterLecteur PanelConsultLec = new PanelConsulterLecteur(o,nom);  // Remplacez "NouveauPanel" par votre nouveau panel
    parent.add(PanelConsultLec);  // Ajoutez le nouveau panel
    
//     PanelConsultLec.setSize(760, 700);
        PanelConsultLec.setSize(1000, 650);

 
    
    
    parent.revalidate();  // Mettez à jour l'interface utilisateur
    parent.repaint();
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RechercheLecteur;
    private javax.swing.JTable TableLect;
    private javax.swing.JButton btn_ajout_lect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}


