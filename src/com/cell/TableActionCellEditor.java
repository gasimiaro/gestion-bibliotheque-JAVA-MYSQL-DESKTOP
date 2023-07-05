/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author G A S I M I A R O
 */
public class TableActionCellEditor extends DefaultCellEditor{
    
    private TableActionEvent event;
 
    public TableActionCellEditor(TableActionEvent event){
        super(new JCheckBox());
        this.event= event;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
 ActionPanel action = new ActionPanel();
 action.initEvent(event, row,value);
 action.setBackground(table.getSelectionBackground());
 return action;
    }
    
}
