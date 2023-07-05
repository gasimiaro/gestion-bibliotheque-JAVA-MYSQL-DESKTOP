/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionbiblio;

/**
 *
 * @author G A S I M I A R O
 */
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteButtonEditor extends DefaultCellEditor {

    private JButton deleteButton;

    public DeleteButtonEditor() {
        super(new JCheckBox());
        deleteButton = new JButton();
        deleteButton.setOpaque(true);
        deleteButton.addActionListener(e -> {
            fireEditingStopped();
            JTable table = (JTable) e.getSource();
            int modelRow = table.convertRowIndexToModel(table.getEditingRow());
            ((DefaultTableModel) table.getModel()).removeRow(modelRow);
        });
    }

    @Override
    public JButton getComponent() {
        return deleteButton;
    }
}
