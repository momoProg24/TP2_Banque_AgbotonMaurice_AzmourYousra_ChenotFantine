package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauTransfert extends JPanel {
    private JTextField montantField;
    private JTextField compteDestinataireField;
    private JButton effectuerTransfertButton;

    public PanneauTransfert() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Montant:"));
        montantField = new JTextField();
        add(montantField);

        add(new JLabel("Numéro de compte destinataire:"));
        compteDestinataireField = new JTextField();
        add(compteDestinataireField);

        effectuerTransfertButton = new JButton("Effectuer Transfert");
        add(effectuerTransfertButton);
    }

    public JTextField getMontantField() {
        return montantField;
    }

    public JTextField getCompteDestinataireField() {
        return compteDestinataireField;
    }

    public JButton getEffectuerTransfertButton() {
        return effectuerTransfertButton;
    }

    public void setEcouteur(ActionListener ecouteur) {
        effectuerTransfertButton.addActionListener(ecouteur);
    }
}