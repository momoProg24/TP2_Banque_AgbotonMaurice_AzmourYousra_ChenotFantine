package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauOperationsCompte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauOperationsCompte panneauOperationsCompte;

    public EcouteurOperationsCompte(Client client, PanneauOperationsCompte panneauOperationsCompte) {
        this.client = client;
        this.panneauOperationsCompte = panneauOperationsCompte;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        try {
            switch (actionCommand) {
                case "DEPOT":
                    panneauOperationsCompte.showCard("DEPOT");
                    effectuerDepot();
                    break;
                case "RETRAIT":
                    panneauOperationsCompte.showCard("RETRAIT");
                    effectuerRetrait();
                    break;
                case "TRANSFER":
                    panneauOperationsCompte.showCard("TRANSFER");
                    effectuerTransfert();
                    break;
                case "FACTURE":
                    panneauOperationsCompte.showCard("FACTURE");
                    effectuerPaiementFacture();
                    break;
                case "EPARGNE":
                    client.envoyer("EPARGNE");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opération non reconnue.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erreur lors de l'opération : " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void effectuerDepot() throws Exception {
        String montant = panneauOperationsCompte.getPanneauDepot().getMontantField().getText();
        client.envoyer("DEPOT " + montant);
    }

    private void effectuerRetrait() throws Exception {
        String montant = panneauOperationsCompte.getPanneauRetrait().getMontantField().getText();
        client.envoyer("RETRAIT " + montant);
    }

    private void effectuerTransfert() throws Exception {
        String montant = panneauOperationsCompte.getPanneauTransfert().getMontantField().getText();
        String compteDestinataire = panneauOperationsCompte.getPanneauTransfert().getCompteDestinataireField().getText();
        client.envoyer("TRANSFER " + montant + " " + compteDestinataire);
    }

    private void effectuerPaiementFacture() throws Exception {
        String montant = panneauOperationsCompte.getPanneauPaiementFacture().getMontantField().getText();
        String numeroFacture = panneauOperationsCompte.getPanneauPaiementFacture().getNumeroFactureField().getText();
        String description = panneauOperationsCompte.getPanneauPaiementFacture().getDescriptionField().getText();
        client.envoyer("FACTURE " + montant + " " + numeroFacture + " " + description);
    }
}