package com.cell;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author G A S I M I A R O
 */
import gestionbiblio.PanelPret;
import gestionbiblio.biblio;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
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

//
//import javax.swing.table.DefaultTableCellRenderer;
//import java.awt.Component;
//import javax.swing.JTable;
//import javax.swing.SwingConstants;
//
//public class DateRenderer extends DefaultTableCellRenderer {
//
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//       
//        if(value instanceof ActionPanelPret) {
//                   return (ActionPanelPret) value;
//               }
//         Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        if (value != null && value.toString().equals("0000-00-00")) {
//            ActionPanelPret button = new ActionPanelPret();
//            button.setBackground(table.getBackground());
//            component = button;
//        }
//        else{
//            System.out.println("probleme");
//        }
//      
//
//        if (component instanceof JLabel) {
//            ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
//        }
//
//        return component;
//    }
//}


import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;

public class DateRenderer extends DefaultTableCellRenderer {
    private TableCellEditor editor;
    private biblio bi;
private PanelPret panPre;
    public DateRenderer(biblio bi,PanelPret panPre) {
        this.bi = bi;
        this.panPre = panPre;
        editor = new ButtonCellEditor(bi,panPre);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component;

        if (value != null && value.toString().equals("0000-00-00")) {
            ActionPanelPret button = new ActionPanelPret();
            button.setBackground(table.getBackground());
            component = button;
        } else {
            component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

        if (component instanceof JLabel) {
            ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
        }

        return component;
    }

    public TableCellEditor getCellEditor(int row, int column) {
        return editor;
    }
}
