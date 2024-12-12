package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauDepot extends JPanel {
    private JTextField montantField;
    private JButton effectuerDepotButton;

    public PanneauDepot() {
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Montant:"));
        montantField = new JTextField();
        add(montantField);

        effectuerDepotButton = new JButton("Effectuer Dépôt");
        add(effectuerDepotButton);
    }

    public JTextField getMontantField() {
        return montantField;
    }

    public JButton getEffectuerDepotButton() {
        return effectuerDepotButton;
    }

    public void setEcouteur(ActionListener ecouteur) {
        effectuerDepotButton.addActionListener(ecouteur);
    }
}