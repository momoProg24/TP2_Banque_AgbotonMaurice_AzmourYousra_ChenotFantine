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
        Object source = e.getSource();
        try {
            if (source == panneauOperationsCompte.getPanneauDepot().getEffectuerDepotButton()) {
                effectuerDepot();
            } else if (source == panneauOperationsCompte.getBDepot()) {
                panneauOperationsCompte.showCard("DEPOT");
            } else if (source == panneauOperationsCompte.getBRetrait()) {
                panneauOperationsCompte.showCard("RETRAIT");
            } else if (source == panneauOperationsCompte.getPanneauRetrait().getEffectuerRetraitButton()) {
                effectuerRetrait();
            } else if (source == panneauOperationsCompte.getBTransfert()) {
                panneauOperationsCompte.showCard("TRANSFER");
            } else if (source == panneauOperationsCompte.getPanneauTransfert().getEffectuerTransfertButton()) {
                effectuerTransfert();
            } else if (source == panneauOperationsCompte.getBFacture()) {
                panneauOperationsCompte.showCard("FACTURE");
            } else if (source == panneauOperationsCompte.getPanneauPaiementFacture().getEffectuerPaiementButton()) {
                effectuerPaiementFacture();
            } else if (source == panneauOperationsCompte.getBEpargne()) {
                client.envoyer("EPARGNE");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Opération non reconnue.",
                        "Erreur", JOptionPane.ERROR_MESSAGE);
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