/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionbiblio;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.io.File;



public class biblio extends javax.swing.JFrame {

    private biblio bi = biblio.this;
    public biblio() {
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
         // Chemin relatif de l'image
        String imagePath = "src/ressource/book_shelf_54px.png";

        // Construction du chemin absolu
        File imageFile = new File(imagePath);
        String absolutePath = imageFile.getAbsolutePath();
        

        // Création de l'objet ImageIcon
        ImageIcon logo = new ImageIcon(absolutePath);
        setIconImage(logo.getImage());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        initComponents();
        selectPret();
        
       }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GESTION DE BIBLIOTHEQUE");
        setMaximumSize(new java.awt.Dimension(800, 1100));
        setPreferredSize(new java.awt.Dimension(800, 650));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 237, 231));

        btn1.setBackground(new java.awt.Color(143, 91, 71));
        btn1.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        btn1.setForeground(new java.awt.Color(255, 255, 255));
        btn1.setText(" LECTEUR");
        btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn1.setFocusPainted(false);
        btn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1.setMargin(new java.awt.Insets(6, 0, 0, 0));
        btn1.setMaximumSize(new java.awt.Dimension(150, 50));
        btn1.setPreferredSize(new java.awt.Dimension(120, 50));
        btn1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1MouseClicked(evt);
            }
        });
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(143, 91, 71));
        btn2.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        btn2.setForeground(new java.awt.Color(255, 255, 255));
        btn2.setText("LIVRE");
        btn2.setToolTipText("");
        btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn2.setFocusPainted(false);
        btn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn2.setMargin(new java.awt.Insets(7, 0, 5, 0));
        btn2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn2MouseClicked(evt);
            }
        });
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(143, 91, 71));
        btn3.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        btn3.setForeground(new java.awt.Color(255, 255, 255));
        btn3.setText("PRET");
        btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn3.setFocusPainted(false);
        btn3.setHideActionText(true);
        btn3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btn3.setMargin(new java.awt.Insets(6, 0, 2, 0));
        btn3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn3MouseClicked(evt);
            }
        });
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 704, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
         
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MouseClicked
   
           Component[] components = getContentPane().getComponents();
        for (Component c : components) {
        if (c instanceof PanelPret) {
            getContentPane().remove(c);
        }
        if (c instanceof PanelLivre) {
            getContentPane().remove(c);
        }
        if (c instanceof PanelConsulterLecteur) {
            getContentPane().remove(c);
        }}
        
        bi.setSize(800, 650);
    PanelLecteur pnlLecteur = new PanelLecteur(bi);
    pnlLecteur.setSize(800, 614);
 
    this.add(pnlLecteur); 
    this.revalidate();
    this.repaint();
    btn1.setBackground(new java.awt.Color(255, 255, 255));
    btn1.setForeground(new java.awt.Color(143, 91, 71));
     btn2.setBackground(new java.awt.Color(143, 91, 71));
     btn2.setForeground(new java.awt.Color(255, 255, 255));
      btn3.setBackground(new java.awt.Color(143, 91, 71));
      btn3.setForeground(new java.awt.Color(255, 255, 255));
      
    }//GEN-LAST:event_btn1MouseClicked

    private void btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn2MouseClicked
         Component[] components = getContentPane().getComponents();
    for (Component c : components) {
        if (c instanceof PanelPret) {
            getContentPane().remove(c);
        }
        if (c instanceof PanelLecteur) {
            getContentPane().remove(c);
        }
     if (c instanceof PanelConsulterLecteur) {
            getContentPane().remove(c);
        }}
     bi.setSize(800, 650);
    PanelLivre pnlLivre = new PanelLivre(bi);
    pnlLivre.setSize(827, 614);
 
    this.add(pnlLivre); 
    this.revalidate();
    this.repaint();   
    btn2.setBackground(new java.awt.Color(255, 255, 255));
    btn2.setForeground(new java.awt.Color(143, 91, 71));
     btn1.setBackground(new java.awt.Color(143, 91, 71));
     btn1.setForeground(new java.awt.Color(255, 255, 255));
      btn3.setBackground(new java.awt.Color(143, 91, 71));
      btn3.setForeground(new java.awt.Color(255, 255, 255));
       
    }//GEN-LAST:event_btn2MouseClicked

    private void btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn3MouseClicked
        selectPret();
        
    }//GEN-LAST:event_btn3MouseClicked

    public void selectPret(){
         Component[] components = getContentPane().getComponents();
    for (Component c : components) {
        if (c instanceof PanelLivre) {
            getContentPane().remove(c);
        }
        if (c instanceof PanelLecteur) {
            getContentPane().remove(c);
        }
     if (c instanceof PanelConsulterLecteur) {
            getContentPane().remove(c);
        }}    
     bi.setSize(800, 650);
     PanelPret pnlPret = new PanelPret(bi);
     pnlPret.setSize(828, 614);
   

    this.add(pnlPret); 
    this.revalidate();
    this.repaint();
    
    
    btn3.setBackground(new java.awt.Color(255, 255, 255));
    btn3.setForeground(new java.awt.Color(143, 91, 71));
     btn2.setBackground(new java.awt.Color(143, 91, 71));
     btn2.setForeground(new java.awt.Color(255, 255, 255));
      btn1.setBackground(new java.awt.Color(143, 91, 71));
      btn1.setForeground(new java.awt.Color(255, 255, 255));
        
    }
    
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn3ActionPerformed

   
    public static void main(String args[]) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new biblio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
   
}
