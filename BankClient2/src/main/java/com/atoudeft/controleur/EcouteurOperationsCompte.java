package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauHistorique;
import com.atoudeft.vue.PanneauOperationsCompte;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauOperationsCompte panneauOperationsCompte;
    PanneauPrincipal panneauPrincipal;

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
                   effectuerDepot(); /// si je mets ca en commentaire il y aucun depot qui se faire
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
                case "HIST":
                    afficherHistoriqueCompte();
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

    //  QUESTION 5
    private void afficherHistoriqueCompte() {
        String numeroCompte = panneauPrincipal.getSelectedAccountNumber();
        if (numeroCompte == null || numeroCompte.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Veuillez sélectionner un compte pour afficher l'historique.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            // Debug : Vérifier que la commande HIST est envoyée avec le numéro de compte
            System.out.println("Envoi de la commande HIST pour le compte : " + numeroCompte);
            client.envoyer("HIST " + numeroCompte); // Envoi de la commande HIST suivie du numéro de compte
        }
    }



    // Méthode pour afficher l'historique dans une fenêtre dédiée
    public void afficherHistoriqueFenetre(String historique) {
        PanneauHistorique panneauHistorique = new PanneauHistorique();
        panneauHistorique.afficherHistorique(historique);

        // Création et affichage d'une nouvelle fenêtre pour l'historique
        JFrame fenetreHistorique = new JFrame("Historique du compte");
        fenetreHistorique.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetreHistorique.add(panneauHistorique);
        fenetreHistorique.pack();
        fenetreHistorique.setLocationRelativeTo(null);  // Centrer la fenêtre
        fenetreHistorique.setVisible(true);
    }



}