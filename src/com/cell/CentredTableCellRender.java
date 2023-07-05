/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

/**
 *
 * @author G A S I M I A R O
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CentredTableCellRender extends DefaultTableCellRenderer {

    public CentredTableCellRender() {
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Centre le texte horizontalement
        if (component instanceof JLabel) {
            ((JLabel) component).setHorizontalAlignment(SwingConstants.CENTER);
        }

        return component;
    }
}

