package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauRetrait extends JPanel {
    private JTextField montantField;
    private JButton effectuerRetraitButton;

    public PanneauRetrait() {
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Montant:"));
        montantField = new JTextField();
        add(montantField);

        effectuerRetraitButton = new JButton("Effectuer Retrait");
        add(effectuerRetraitButton);
    }

    public JTextField getMontantField() {
        return montantField;
    }

    public JButton getEffectuerRetraitButton() {
        return effectuerRetraitButton;
    }

    public void setEcouteur(ActionListener ecouteur) {
        effectuerRetraitButton.addActionListener(ecouteur);
    }
}