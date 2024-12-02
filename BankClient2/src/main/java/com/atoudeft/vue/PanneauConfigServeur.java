package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        // Initialisation des champs de texte
        txtAdrServeur = new JTextField(adr);
        txtNumPort = new JTextField(String.valueOf(port));

        // Ajout des composants au panneau
        setLayout(new GridLayout(2, 2, 5, 5));
        add(new JLabel("appliAdresse IP :"));
        add(txtAdrServeur);
        add(new JLabel("Port :"));
        add(txtNumPort);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
