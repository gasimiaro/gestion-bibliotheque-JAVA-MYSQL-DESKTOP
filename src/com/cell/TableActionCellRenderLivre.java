/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
//import javax.swing.SwingConstants;

/**
 *
 * @author G A S I M I A R O
 */
public class TableActionCellRenderLivre extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        
//        if(com instanceof JLabel){
//            ((JLabel) com).setHorizontalAlignment(SwingConstants.CENTER);
//        }
        ActionPanelLivre action = new ActionPanelLivre();
        action.setBackground(com.getBackground());
        return action;
    }
   
  
        
   
}
