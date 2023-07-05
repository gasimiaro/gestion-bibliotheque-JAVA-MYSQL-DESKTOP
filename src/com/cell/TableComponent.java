/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author G A S I M I A R O
 */
public class TableComponent extends DefaultTableCellRenderer{
     public Component getTableCellRendererComponent(JTable table,
              Object value, boolean isSelected, boolean hasFocus, int row, int
              column) {
              //Si la valeur de la cellule est un JButton, on transtype cette
           
              if (value instanceof JButton){
                  return (JButton) value;
              }
              
              else
              return this;
               }

}