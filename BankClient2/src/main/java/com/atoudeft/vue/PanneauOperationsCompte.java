package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauOperationsCompte extends JPanel {
    private JButton bEpargne, bDepot, bRetrait, bTransfert, bFacture, bHistorique;
    private JLabel lblSolde;

    public PanneauOperationsCompte() {
        bEpargne = new JButton("Créer compte épargne");
        bDepot = new JButton("Déposer");
        bRetrait = new JButton("Retirer");
        bTransfert = new JButton("Transferer");
        bFacture = new JButton("Payer Facture");
        bHistorique = new JButton("Historique du compte");
        lblSolde = new JLabel("Solde : ");

        bEpargne.setActionCommand("EPARGNE");
        bDepot.setActionCommand("DEPOT");
        bRetrait.setActionCommand("RETRAIT");
        bTransfert.setActionCommand("TRANSFER");
        bFacture.setActionCommand("FACTURE");
        bHistorique.setActionCommand("HIST");

        //à compléter :
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(lblSolde);
        this.add(bEpargne);
        this.add(bDepot);
        this.add(bRetrait);
        this.add(bTransfert);
        this.add(bFacture);
        this.add(bHistorique);
    }
    public void setEcouteur(ActionListener ecouteur) {
        bEpargne.addActionListener(ecouteur);
        bDepot.addActionListener(ecouteur);
        bRetrait.addActionListener(ecouteur);
        bTransfert.addActionListener(ecouteur);
        bFacture.addActionListener(ecouteur);
        bHistorique.addActionListener(ecouteur);
    }

    /**
     * Met à jour le solde affiché dans le panneau. QUESTION 3
     *
     * @param typeCompte Type de compte (exemple : "cheque", "epargne").
     * @param solde      Solde actuel du compte.
     */
    public void mettreAJourSolde(String typeCompte, String solde) {
        lblSolde.setText("Type de compte : " + typeCompte + " | Solde : " + solde);
    }
}
