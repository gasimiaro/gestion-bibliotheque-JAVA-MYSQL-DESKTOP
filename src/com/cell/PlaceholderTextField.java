/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cell;

/**
 *
 * @author G A S I M I A R O
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField implements FocusListener {
    private String placeholder;
    private boolean showingPlaceholder;
    private Font placeholderFont;
    private Color placeholderColor;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;

        addFocusListener(this);

        // Configuration de la police et de la couleur du placeholder
        this.placeholderFont = getFont().deriveFont(Font.ITALIC);
        this.placeholderColor = Color.GRAY;
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Lorsque le champ de texte obtient le focus, on supprime le placeholder s'il est affiché
        if (showingPlaceholder) {
            setText("");
            setFont(getFont().deriveFont(Font.PLAIN));
            setForeground(Color.BLACK);
            showingPlaceholder = false;
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Lorsque le champ de texte perd le focus, on réaffiche le placeholder s'il n'y a pas de texte
        if (getText().isEmpty()) {
            setText(placeholder);
            setFont(placeholderFont);
            setForeground(placeholderColor);
            showingPlaceholder = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Si le placeholder est affiché, on le dessine
        if (showingPlaceholder && !getText().isEmpty()) {
            g.setFont(placeholderFont);
            g.setColor(placeholderColor);
            int x = getInsets().left;
            int y = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();
            g.drawString(placeholder, x, y);
        }
    }
}
