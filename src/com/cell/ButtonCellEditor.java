/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

/**
 *
 * @author G A S I M I A R O
 */


import com.formdev.flatlaf.ui.FlatUIUtils;
import gestionbiblio.FenRetourPretSucces;
import gestionbiblio.FenRetournerPret;
import gestionbiblio.PanelPret;
import gestionbiblio.biblio;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

//public class ButtonCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
//    private JButton button;
//
//    public ButtonCellEditor() {
//        button = new JButton();
//        button.addActionListener(this);
//    }
//
//    @Override
//    public Object getCellEditorValue() {
//        return null;
//    }
//
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        button.setText(value.toString());
//        return button;
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // Traitement à effectuer lors du clic sur le bouton de la cellule
//        fireEditingStopped();
//    }
//}


import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ButtonCellEditor extends DefaultCellEditor {
     Connection sqlConn=null;
   PreparedStatement pst=null;
   ResultSet rs= null;
   private biblio bi;
    private JButton button;
    private boolean clicked;
    private Object value;
      private TableActionEvent event;
      private PanelPret panPre;
      private JTable tablePret;
      private Object firstColumnValue;
      private Object thirdColumnValue;

    public ButtonCellEditor(biblio bi,PanelPret panPre) {
        super(new JCheckBox());
        this.bi = bi;
        this.panPre = panPre;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                
                fireEditingStopped();
//                 button.addMouseListener(new java.awt.event.MouseAdapter() {
//            
//                     
//                      @Override
//                      public void mouseClicked(java.awt.event.MouseEvent evt) {
//                System.out.println("ato");
//            }
//             @Override
//             public void mousePressed(java.awt.event.MouseEvent evt) {
//               System.out.println("kitika");
//               button.setText(" ");
//               
//            }
//              @Override
//              public void mouseReleased(java.awt.event.MouseEvent evt) {
//           
//               button.setText(value.toString());
//                   System.out.println("tsy kitika");
//            }
//        });
            }
           
        });
        
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.value =value;
        this.tablePret = table;
      
//        System.out.println("object "+table);
//        System.out.println("object "+value);
//        System.out.println("row "+row);
//        System.out.println("object "+column);
        if (value == null) {
            button.setText("");
        } else {
            button.setText(value.toString());
            
//            //appel de la function onReturn
//            if(value.toString().equals("0000-00-00")){
//                System.out.println("boutton mipoitra");
//                FenRetournerPret fenRe =new FenRetournerPret(panPre,firstColumnValue);
//                fenRe.setVisible(true);
//                
//            }
//            else{
//                 System.out.println("boutton tsy mipoitra");
//            }
//            System.out.println("tesue.toString());t "+value.toString());
        }
//         button.setText((value == null) ? "Rendreeee" : value.toString());
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {                  
          
                                            int row = tablePret.getEditingRow(); // Récupération de l'indice de ligne
                                             firstColumnValue = tablePret.getValueAt(row, 0); // Récupération de la valeur de la première colonne
                                              thirdColumnValue = tablePret.getValueAt(row, 2); // Récupération de la valeur de la  colonne 3
                                              Object dateValue = tablePret.getValueAt(row, 3); // Récupération de la valeur de la colonne 4 (index 3)
                                          // System.out.println("Valeur de la première colonne : " + firstColumnValue);
                                             //appel de la function onReturn
                                            if(value.toString().equals("0000-00-00")){
                                                System.out.println("boutton mipoitra");
                                                // Conversion de la valeur en objet java.util.Date si nécessaire
                                        Date date;
                                        if (dateValue instanceof Date) {
                                            date = (Date) dateValue;
                                        } else {
                                            try {
                                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                date = dateFormat.parse(dateValue.toString());
                                            } catch (Exception e) {
                                                date = null;
                                            }
                                        }
                                          // Calcul de la différence entre la date de la colonne 4 et la date d'aujourd'hui
                                        if (date != null) {
                                            Date today = new Date();
                                            long differenceInMillis = today.getTime() - date.getTime();
                                            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);
                                            System.out.println("Différence en jours : " + differenceInDays);
                                            String title = "Retour sans amende";
                                            try {
                                                        sqlConn = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
                                                        //prendre en charge la date actuel
                                                        LocalDate actuel = LocalDate.now();
                                                         DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                                         String dateFormater = actuel.format(format);
                                                         //la requete
                                                         pst = sqlConn.prepareStatement(" UPDATE pret,livre set pret.DateRetour='"+ dateFormater+"',livre.disponible='OUI' WHERE pret.N_Pret='"+firstColumnValue+"' AND livre.numLivre='"+thirdColumnValue+"'");
                                                         
                                                         //si moins de 7 jours de pret
                                                        if(differenceInDays <=7){
                                                            System.out.println(dateFormater);
                                                           //JOptionPane.showMessageDialog(null, "Livre rendu à temps", title,  JOptionPane.INFORMATION_MESSAGE);
                                                           FenRetourPretSucces fen = new  FenRetourPretSucces(bi,panPre,pst);
                                                           fen.setAlwaysOnTop(true);
                                                           bi.setEnabled(false);
                                                           fen.setVisible(true);
                                                           

                                                        }
                                                        //si plus de 7jours de pret
                                                        else{
                                                                            FenRetournerPret fenRe =new FenRetournerPret(bi,panPre,firstColumnValue,pst,differenceInDays);
                                                                             fenRe.setAlwaysOnTop(true);
                                                                              bi.setEnabled(false);
                                                                             fenRe.setVisible(true);
                                                        }
                                             } catch (SQLException ex) {
                                                        Logger.getLogger(ButtonCellEditor.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                        }
//                FenRetournerPret fenRe =new FenRetournerPret(panPre,firstColumnValue);
//                fenRe.setVisible(true);
                
            }
            else{
                 System.out.println("boutton tsy mipoitra");
            }
            // Traitement à effectuer lors du clic sur le bouton de la cellule
//            System.out.println("action1");

        }
     clicked = false;
      
        return button.getText();
    }
    

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
