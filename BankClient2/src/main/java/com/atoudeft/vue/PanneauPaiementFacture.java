package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauPaiementFacture extends JPanel {
    private JTextField montantField;
    private JTextField numeroFactureField;
    private JTextField descriptionField;
    private JButton effectuerPaiementButton;

    public PanneauPaiementFacture() {
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Montant:"));
        montantField = new JTextField();
        add(montantField);

        add(new JLabel("Numéro de facture:"));
        numeroFactureField = new JTextField();
        add(numeroFactureField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        effectuerPaiementButton = new JButton("Effectuer Paiement");
        add(effectuerPaiementButton);
    }

    public JTextField getMontantField() {
        return montantField;
    }

    public JTextField getNumeroFactureField() {
        return numeroFactureField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JButton getEffectuerPaiementButton() {
        return effectuerPaiementButton;
    }

    public void setEcouteur(ActionListener ecouteur) {
        effectuerPaiementButton.addActionListener(ecouteur);
    }
}