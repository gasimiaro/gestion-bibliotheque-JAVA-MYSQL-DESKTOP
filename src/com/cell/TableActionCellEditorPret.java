/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author G A S I M I A R O
 */
//public class TableActionCellEditorPret extends DefaultCellEditor{
//    
//    private TableActionEvent event;
// 
//    public TableActionCellEditorPret(TableActionEvent event){
//        super(new JCheckBox());
//        this.event= event;
//    }
//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
// ActionPanelPret action = new ActionPanelPret();
// action.initEvent(event, row,value);
// action.setBackground(table.getSelectionBackground());
// return action;
//    }
//    
//}
//public class TableActionCellEditorPret extends DefaultCellEditor {
//    private JButton button;
//
//    public TableActionCellEditorPret(JCheckBox checkBox) {
//        super(checkBox);
//        button = new JButton();
////        button.setOpaque(true);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                fireEditingStopped();
//                // Votre logique de gestion du clic sur le bouton ici
//                // Vous pouvez récupérer la valeur de la cellule en utilisant
//                // getValue()
//              
//            }
//        });
//    }
//
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        // Personnalisez le bouton ici en fonction de la valeur de la cellule, si nécessaire
//        button.setText((value == null) ? "Rendreeee" : "RENDRE");
//        return button;
//    }
//}
public class TableActionCellEditorPret extends DefaultCellEditor{
    
    private TableActionEvent event;
 
    public TableActionCellEditorPret(TableActionEvent event){
        super(new JCheckBox());
        this.event= event;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
 ActionPanelPret action1 = new ActionPanelPret();
 action1.initEvent(event, row,value);
 action1.setBackground(table.getSelectionBackground());
 return action1;
    }
    
}
