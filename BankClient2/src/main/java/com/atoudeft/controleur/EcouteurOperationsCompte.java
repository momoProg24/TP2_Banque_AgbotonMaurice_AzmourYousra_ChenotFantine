package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private DefaultListModel<String> numeroComptes;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    public EcouteurOperationsCompte(Client client, DefaultListModel<String> numerosComptes) {

        this.client = client;
        this.numeroComptes = numerosComptes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //à compléter :
        String actionCommand = e.getActionCommand();
        if("EPARGNE".equals(actionCommand))
        {
            try{
                client.envoyer("EPARGNE");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Erreur lors de la création du compte épargne.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
