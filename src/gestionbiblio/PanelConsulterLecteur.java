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
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.table.TableColumn;
/**
 *
 * @author G A S I M I A R O
 */
class TableComponent extends DefaultTableCellRenderer {
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
public class PanelConsulterLecteur extends javax.swing.JPanel {

           
   private int nbLigne = 0;
   private  ButtonGroup bg;
   
   Connection sqlConn=null;
   PreparedStatement pst=null;
   ResultSet rs= null;
   PreparedStatement pstdel=null;
   ResultSet rsdel= null;
   private Object obj;
       
 // Créer une liste pour stocker les numéros de lecteur
           java.util.List<String> listMoisAnnee = new java.util.ArrayList<>();
           java.util.List<String> listAnnee = new java.util.ArrayList<>();

    /**
     * Creates new form PanelConsulterLecteur
     * @param o
     * @param nom
     */
    public PanelConsulterLecteur(Object o,String nom) {
        initComponents();
        this.obj = o;
        bg = new ButtonGroup();
        bg.add(RdEntre2Dates);
        bg.add(RdAnnee);
        bg.add(RdMois);
        
         // Obtenir la date actuelle
        LocalDate currentDate = LocalDate.now();

        // Récupérer l'année actuelle
        int currentYear = currentDate.getYear();
        int currentMounth = currentDate.getMonthValue();
        
        //******************************************************
        //ajouter les annees dans le combo box
        int k;
        for(k=currentYear;k>1900;k--){
            listMoisAnnee.add(Integer.toString(k));
            listAnnee.add(Integer.toString(k));
        }
        // Convertir les listes de numéros de lecteur et de numéros de livre en tableaux
                             String[] listMoisAnneeArray = listMoisAnnee.toArray(new String[0]);
                             String[] listAnneeArray = listAnnee.toArray(new String[0]);

                             // Ajouter les numéros de lecteur et les numéros de livre aux listes déroulantes
                             cbAnnee.setModel(new javax.swing.DefaultComboBoxModel<>(listMoisAnneeArray));
                             cbMois_Annee.setModel(new javax.swing.DefaultComboBoxModel<>(listAnneeArray));
                             cbMois.setSelectedIndex(currentMounth -1);
                             
        //******************************************************************
        //date d aujoudhui dans la date 2 et 

         Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String formattedDate = dateFormat.format(date);
    txtDate2.setText(formattedDate);
    txtDate1.setText("01-01-1900");
        //***********************************************************
        ActionEvent e = null;
       RdEntre2DatesActionPerformed(e);
        consNumLec.setText((String)o);
        consNom.setText(nom);
        
         updateDB2(o,"01-01-1900",formattedDate); //new SimpleDateFormat("yyyy-MM--dd").format(new Date())
           //centrer les elements du tableau
         /***************************************/
        for (int i = 0; i < tableConsultLec.getColumnCount(); i++) {
            tableConsultLec.getColumnModel().getColumn(i).setCellRenderer(new CentredTableCellRender());
        }
        /**************************************/
         
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    private void updateDB2(Object o,String date1,String date2){
        nbLigne = 0;
        int c;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection("jdbc:mysql://localhost/baseprojet","root","");
            //jointure sur l affichage du tableau
                      pst = sqlConn.prepareStatement("SELECT DISTINCT livre.titre,livre.auteur,livre.date_edition,pret.DatePret,pret.DateRetour from lecteur,livre,pret WHERE (livre.numLivre = pret.numLivre) and ( pret.numLecteur ='"+o+"' ) AND DatePret BETWEEN STR_TO_DATE('"+date1+"', '%d-%m-%Y') AND STR_TO_DATE('"+date2+"', '%d-%m-%Y') ");//STR_TO_DATE('01-01-2023', '%d-%m-%Y')
            rs = pst.executeQuery();
           ResultSetMetaData stData = (ResultSetMetaData) rs.getMetaData();
           
            c=stData.getColumnCount() +1;
            
            DefaultTableModel RecordTable= (DefaultTableModel)tableConsultLec.getModel();
            RecordTable.setRowCount(0);
           
            while(rs.next()){
            
            Vector v2 = new Vector();
           
            for(int a=1; a<=c;a++)
                
            {
                            this.tableConsultLec.setDefaultRenderer(JButton.class, new TableComponent());
            v2.add(rs.getString("titre"));
            
            v2.add(rs.getString("auteur"));
            v2.add(rs.getString("date_edition"));
            v2.add(rs.getString("DatePret"));                
            
            //verifiersi pas encore rendu
            String dateRetour = rs.getString("DateRetour");
            String result = (dateRetour.equals("0000-00-00")) ? "EN POSSESSION" : dateRetour;
            v2.add(result);

            
            
            }
            //comptage du nombre de lignes
           nbLigne ++;
          RecordTable.addRow(v2);
            }
//              //pour afiicher le bouton supprimer correctement
//               TableColumn actionColumn1 = TableLect.getColumnModel().getColumn(2); // Remplacez 2 par l'index réel de la colonne "ACTION"
//                actionColumn1.setCellRenderer(new TableComponent());
//                
//                TableColumn actionColumn2 = TableLect.getColumnModel().getColumn(3); // Remplacez 2 par l'index réel de la colonne "ACTION"
//                actionColumn2.setCellRenderer(new TableComponent());
//                
         
                nbLivrePret.setText(String.valueOf(nbLigne));
               System.out.println(nbLigne +": nombre de ligne");

        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }        // TODO add your handling code here:
    }     
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        consNom = new javax.swing.JTextField();
        consNumLec = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableConsultLec = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        nbLivrePret = new javax.swing.JTextField();
        btnGenererPdf = new javax.swing.JButton();
        RdAnnee = new javax.swing.JRadioButton();
        RdEntre2Dates = new javax.swing.JRadioButton();
        RdMois = new javax.swing.JRadioButton();
        panelMois = new javax.swing.JPanel();
        cbMois = new javax.swing.JComboBox<>();
        cbMois_Annee = new javax.swing.JComboBox<>();
        panelAnnee = new javax.swing.JPanel();
        cbAnnee = new javax.swing.JComboBox<>();
        panel2Dates = new javax.swing.JPanel();
        txtDate1 = new javax.swing.JTextField();
        txtDate2 = new javax.swing.JTextField();
        btnChoixDate1 = new javax.swing.JButton();
        btnChoixDate2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        dateChooser1.setTextField(txtDate1);

        dateChooser2.setTextField(txtDate2);

        setBackground(new java.awt.Color(143, 91, 71));
        setPreferredSize(new java.awt.Dimension(1000, 650));

        jLabel2.setFont(new java.awt.Font("Adobe Fan Heiti Std B", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LISTE DES PRETS D'UN LECTEUR");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NumLecteur :");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nom              :");

        consNom.setEditable(false);
        consNom.setBackground(null);
        consNom.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        consNom.setForeground(new java.awt.Color(255, 255, 255));
        consNom.setBorder(null);
        consNom.setEnabled(false);

        consNumLec.setEditable(false);
        consNumLec.setBackground(null);
        consNumLec.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        consNumLec.setForeground(new java.awt.Color(255, 255, 255));
        consNumLec.setBorder(null);
        consNumLec.setEnabled(false);

        tableConsultLec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DESIGNATION", "AUTEUR", "DATE EDITION", "DATE PRET", "DATE RETOUR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableConsultLec);

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre de livre prêtés :");

        nbLivrePret.setEditable(false);
        nbLivrePret.setBackground(null);
        nbLivrePret.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        nbLivrePret.setForeground(new java.awt.Color(255, 255, 255));
        nbLivrePret.setBorder(null);

        btnGenererPdf.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnGenererPdf.setText("Generer PDF");
        btnGenererPdf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGenererPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenererPdfActionPerformed(evt);
            }
        });

        RdAnnee.setBackground(null);
        RdAnnee.setForeground(new java.awt.Color(255, 255, 255));
        RdAnnee.setText("Année");
        RdAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdAnneeActionPerformed(evt);
            }
        });

        RdEntre2Dates.setBackground(null);
        RdEntre2Dates.setForeground(new java.awt.Color(255, 255, 255));
        RdEntre2Dates.setSelected(true);
        RdEntre2Dates.setText("Entre 2 dates");
        RdEntre2Dates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdEntre2DatesActionPerformed(evt);
            }
        });

        RdMois.setBackground(null);
        RdMois.setForeground(new java.awt.Color(255, 255, 255));
        RdMois.setText("Mois");
        RdMois.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RdMoisActionPerformed(evt);
            }
        });

        cbMois.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre" }));

        cbMois_Annee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelMoisLayout = new javax.swing.GroupLayout(panelMois);
        panelMois.setLayout(panelMoisLayout);
        panelMoisLayout.setHorizontalGroup(
            panelMoisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMoisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbMois, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMois_Annee, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMoisLayout.setVerticalGroup(
            panelMoisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMoisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMoisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMois_Annee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbAnnee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelAnneeLayout = new javax.swing.GroupLayout(panelAnnee);
        panelAnnee.setLayout(panelAnneeLayout);
        panelAnneeLayout.setHorizontalGroup(
            panelAnneeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnneeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAnneeLayout.setVerticalGroup(
            panelAnneeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnneeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panel2Dates.setEnabled(false);

        txtDate1.setEditable(false);
        txtDate1.setForeground(new java.awt.Color(0, 0, 0));

        txtDate2.setEditable(false);

        btnChoixDate1.setText("...");
        btnChoixDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoixDate1ActionPerformed(evt);
            }
        });

        btnChoixDate2.setText("...");
        btnChoixDate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChoixDate2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Date1");

        jLabel6.setText("Date2");

        javax.swing.GroupLayout panel2DatesLayout = new javax.swing.GroupLayout(panel2Dates);
        panel2Dates.setLayout(panel2DatesLayout);
        panel2DatesLayout.setHorizontalGroup(
            panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2DatesLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate2)
                    .addComponent(txtDate1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnChoixDate2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChoixDate1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panel2DatesLayout.setVerticalGroup(
            panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2DatesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoixDate1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(panel2DatesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChoixDate2)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenererPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nbLivrePret, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(consNom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(consNumLec, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RdMois)
                            .addComponent(RdAnnee)
                            .addComponent(RdEntre2Dates)
                            .addComponent(panelMois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelAnnee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel2Dates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(322, 322, 322))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(consNumLec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(consNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nbLivrePret, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(btnGenererPdf)
                .addContainerGap(93, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RdEntre2Dates)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel2Dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RdMois)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMois, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RdAnnee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(194, 194, 194))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenererPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenererPdfActionPerformed
        // TODO add your handling code here:
        // ***************************************************************
         // Création d'un sélecteur de fichiers
    JFileChooser fileChooser = new JFileChooser();

    // Définition du filtre de fichiers pour afficher uniquement les fichiers PDF
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers PDF", "pdf");
    fileChooser.setFileFilter(filter);

    // Affichage de la boîte de dialogue de sélection de fichiers
    int result = fileChooser.showSaveDialog(this);

    // Vérification si l'utilisateur a sélectionné un emplacement de fichier valide
    if (result == JFileChooser.APPROVE_OPTION) {
        // Récupération du fichier sélectionné par l'utilisateur
        File selectedFile = fileChooser.getSelectedFile();

        // Obtention du chemin absolu du fichier sélectionné
        String filePath = selectedFile.getAbsolutePath();

        // Génération du fichier PDF à l'emplacement spécifié
        // Utilisez la valeur de 'filePath' pour générer le fichier PDF

        // Affichez un message de succès ou effectuez d'autres opérations en fonction de vos besoins
        System.out.println("Le fichier PDF sera generer à l'emplacement : " + filePath);
        // ***********************************************************************
        
        //********************************************************
        //creation du pdf
        try{
             Document doc = new Document();
        PdfWriter.getInstance(doc,new FileOutputStream(filePath+".pdf") );
        
        doc.open();
            Paragraph numLecteur = new Paragraph("Numero Lecteur : "+consNumLec.getText());
            Paragraph nom = new Paragraph("Nom Lecteur : "+consNom.getText());
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("CONSULTATION LECTEUR  "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            //si recherche entre 2 dates
            if(RdEntre2Dates.isSelected()){
                doc.add(new Paragraph("Date    : Entre "+txtDate1.getText()+" et "+txtDate2.getText()));
            }
            else if(RdMois.isSelected()){
                doc.add(new Paragraph("Date    : Pendant le mois de  "+cbMois.getSelectedItem()+" "+cbMois_Annee.getSelectedItem()));
            }
            else if(RdAnnee.isSelected()){
                doc.add(new Paragraph("Date    : Pendant l' Année "+cbAnnee.getSelectedItem()));
            }
            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(numLecteur);
            doc.add(nom);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
               // Ajoutez votre tableau du JTable ici
            PdfPTable table = new PdfPTable(tableConsultLec.getColumnCount());
            // Boucle pour ajouter les en-têtes de colonne
            for (int i = 0; i < tableConsultLec.getColumnCount(); i++) {
                table.addCell(tableConsultLec.getColumnName(i));
            }
            // Boucle pour ajouter les données de chaque ligne
            for (int row = 0; row < tableConsultLec.getRowCount(); row++) {
                for (int col = 0; col < tableConsultLec.getColumnCount(); col++) {
                    table.addCell(tableConsultLec.getValueAt(row, col).toString());
                }
            }
            table.setWidthPercentage(100);
            doc.add(table);
            ///************************************************************************************
             doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            
            doc.add(new Paragraph("Nombre de livre prêtés : "+nbLivrePret.getText()));
            
            
        
        doc.close();
                   int reponse = new JOptionPane().showConfirmDialog(null, "Pdf generer avec success.\n\nVoulez vous l' ouvrir ?","PDF Generé",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE );
                   if(reponse == 0){
                       File fichier = new File(filePath+".pdf");
                       if(fichier.exists()){
                           try{
                               Desktop.getDesktop().open(fichier);
                           }
                           catch(IOException e){
                               e.printStackTrace();
                           }   
                       }
                       else{
                           System.out.println("Fichier n' existe pas");
                       }
                   }

        }catch(Exception e){
            e.printStackTrace();
        }
       
        
        
        
        //*********************************************************************************
    }
    }//GEN-LAST:event_btnGenererPdfActionPerformed

    private void btnChoixDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoixDate1ActionPerformed
        // TODO add your handling code here:
        dateChooser1.showPopup();
    }//GEN-LAST:event_btnChoixDate1ActionPerformed

    private void btnChoixDate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChoixDate2ActionPerformed
        // TODO add your handling code here:
        dateChooser2.showPopup();
    }//GEN-LAST:event_btnChoixDate2ActionPerformed

    private void RdEntre2DatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdEntre2DatesActionPerformed
        // TODO add your handling code here:
        txtDate1.setEnabled(true);
        txtDate2.setEnabled(true);
        btnChoixDate1.setEnabled(true);
        btnChoixDate2.setEnabled(true);
        cbAnnee.setEnabled(false);
        cbMois.setEnabled(false);
        cbMois_Annee.setEnabled(false);
        panelAnnee.setEnabled(false);
        panelMois.setEnabled(false);
        //actualiser la table
        updateDB2(obj,txtDate1.getText(),txtDate2.getText());
        //***********************************
         // Ajoutez un écouteur d'événements pour détecter les changements de sélection dans le JComboBox
         DocumentListener doc =new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
     updateDB2(obj,txtDate1.getText(),txtDate2.getText());
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
          updateDB2(obj,txtDate1.getText(),txtDate2.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // Ne sera pas appelé pour les champs de texte simples
    }
};
          txtDate1.getDocument().addDocumentListener(doc);
          txtDate2.getDocument().addDocumentListener(doc);

// ...............................................................
        
        System.out.println("2dates");
    }//GEN-LAST:event_RdEntre2DatesActionPerformed

    private void RdMoisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdMoisActionPerformed
        // TODO add your handling code here:
        
          txtDate1.setEnabled(false);
        txtDate2.setEnabled(false);
        btnChoixDate1.setEnabled(false);
        btnChoixDate2.setEnabled(false);
        cbAnnee.setEnabled(false);
        cbMois.setEnabled(true);
        cbMois_Annee.setEnabled(true);
        panelAnnee.setEnabled(false);
        panel2Dates.setEnabled(false);
        //actualiser table
        updateDB2(obj,
                 "01-"+Integer.toString(cbMois.getSelectedIndex()+1)+"-"+(String)cbMois_Annee.getSelectedItem(),
                 "31-"+Integer.toString(cbMois.getSelectedIndex()+1)+"-"+(String)cbMois_Annee.getSelectedItem());
                 System.out.println("mois");
                 //******************************************
          // Ajoutez un écouteur d'événements pour détecter les changements de sélection dans le JComboBox
          ActionListener ecoute = new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Lorsque l'option est modifiée, appelez la fonction de recherche correspondante
                            
                             updateDB2(obj,
                 "01-"+Integer.toString(cbMois.getSelectedIndex()+1)+"-"+(String)cbMois_Annee.getSelectedItem(),
                 "31-"+Integer.toString(cbMois.getSelectedIndex()+1)+"-"+(String)cbMois_Annee.getSelectedItem());
                        }
                    };
          cbMois.addActionListener(ecoute);
          cbMois_Annee.addActionListener(ecoute);

// ...............................................................
        

    }//GEN-LAST:event_RdMoisActionPerformed

    private void RdAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RdAnneeActionPerformed
        // TODO add your handling code here:
                  txtDate1.setEnabled(false);
        txtDate2.setEnabled(false);
        btnChoixDate1.setEnabled(false);
        btnChoixDate2.setEnabled(false);
        cbAnnee.setEnabled(true);
        cbMois.setEnabled(false);
        cbMois_Annee.setEnabled(false);
        panel2Dates.setEnabled(false);
        panelMois.setEnabled(false);
        //actualiser table
         updateDB2(obj,
                 "01-01-"+(String)cbAnnee.getSelectedItem(),
                 "31-12-"+(String)cbAnnee.getSelectedItem());
                          System.out.println("annee");
        //********************************************************
         // Ajoutez un écouteur d'événements pour détecter les changements de sélection dans le JComboBox
          ActionListener ecoute = new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Lorsque l'option est modifiée, appelez la fonction de recherche correspondante
                            
                             updateDB2(obj,
                 "01-01-"+(String)cbAnnee.getSelectedItem(),
                 "31-12-"+(String)cbAnnee.getSelectedItem());
                        }
                    };
          cbAnnee.addActionListener(ecoute);
         // *******************************************************************
    }//GEN-LAST:event_RdAnneeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RdAnnee;
    private javax.swing.JRadioButton RdEntre2Dates;
    private javax.swing.JRadioButton RdMois;
    private javax.swing.JButton btnChoixDate1;
    private javax.swing.JButton btnChoixDate2;
    private javax.swing.JButton btnGenererPdf;
    private javax.swing.JComboBox<String> cbAnnee;
    private javax.swing.JComboBox<String> cbMois;
    private javax.swing.JComboBox<String> cbMois_Annee;
    private javax.swing.JTextField consNom;
    private javax.swing.JTextField consNumLec;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nbLivrePret;
    private javax.swing.JPanel panel2Dates;
    private javax.swing.JPanel panelAnnee;
    private javax.swing.JPanel panelMois;
    private javax.swing.JTable tableConsultLec;
    private javax.swing.JTextField txtDate1;
    private javax.swing.JTextField txtDate2;
    // End of variables declaration//GEN-END:variables
}
