/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author G A S I M I A R O
 */
  public class ButtonEditor extends DefaultCellEditor 
  {
    private String label;
    private JButton button;
    
    public ButtonEditor(JCheckBox checkBox,JButton button)
    {
        super(checkBox);
       this.button=button;
      
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? value.toString() :"RENDRE" ;
      button.setText(label);
      return button;
    }

    public Object getCellEditorValue() 
    {
      return new String(label);
    }
  }