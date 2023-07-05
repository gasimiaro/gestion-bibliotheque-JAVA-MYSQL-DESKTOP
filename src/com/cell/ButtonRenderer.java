/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author G A S I M I A R O
 */

    public class ButtonRenderer extends JButton implements TableCellRenderer{

        public Component getTableCellRendererComponent(JTable table,
        Object value, boolean isSelected, boolean isFocus, int row, int col)
        {
        //On écrit dans le bouton ce que contient la cellule
        setText((value != null) ? "RENDRE" : value.toString());
        //On retourne notre bouton
        return this;
        }
}


