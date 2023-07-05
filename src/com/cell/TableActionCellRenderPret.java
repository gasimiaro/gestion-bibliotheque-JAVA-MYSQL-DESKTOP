/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
//import javax.swing.SwingConstants;

/**
 *
 * @author G A S I M I A R O
 */
//public class TableActionCellRenderPret extends DefaultTableCellRenderer {
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//      if(value instanceof ActionPanelPret) {
//            return (ActionPanelPret) value;
//        }
//        Component com =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//        
////        if(com instanceof JLabel){
////            ((JLabel) com).setHorizontalAlignment(SwingConstants.CENTER);
////        }
//
//        ActionPanelPret action = new ActionPanelPret();
//        action.setBackground(com.getBackground());
//        return action;
//    }
//      
//   
//}


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class TableActionCellRenderPret extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 4) { 
            if (value instanceof ActionPanelPret) {
                return (ActionPanelPret) value;
            }

            ActionPanelPret action = new ActionPanelPret();
            action.setBackground(table.getBackground());
            return action;
        }

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

//
//public class DateRenderer extends DefaultTableCellRenderer {
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//        if (value != null && value.toString().equals("0000-00-00")) {
//            ActionPanelPret button = new ActionPanelPret();
//            button.setBackground(component.getBackground());
////            button.setBackground(component.getBackground());
////            button.setBorderPainted(false);
////            button.setFocusPainted(false);
////            button.setContentAreaFilled(false);
//            return button;
//        }
//
//        return component;
//    }
//}