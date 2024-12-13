package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauOperationsCompte extends JPanel {
    private JButton bEpargne, bDepot, bRetrait, bTransfert, bFacture, bHistorique;
    private JLabel lblSolde;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private PanneauDepot panneauDepot;
    private PanneauRetrait panneauRetrait;
    private PanneauTransfert panneauTransfert;
    private PanneauPaiementFacture panneauPaiementFacture;

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

        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(lblSolde);
        buttonPanel.add(bEpargne);
        buttonPanel.add(bDepot);
        buttonPanel.add(bRetrait);
        buttonPanel.add(bTransfert);
        buttonPanel.add(bFacture);
        buttonPanel.add(bHistorique);
        this.add(buttonPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        panneauDepot = new PanneauDepot();
        panneauRetrait = new PanneauRetrait();
        panneauTransfert = new PanneauTransfert();
        panneauPaiementFacture = new PanneauPaiementFacture();

        cardPanel.add(panneauDepot, "DEPOT");
        cardPanel.add(panneauRetrait, "RETRAIT");
        cardPanel.add(panneauTransfert, "TRANSFER");
        cardPanel.add(panneauPaiementFacture, "FACTURE");

        this.add(cardPanel, BorderLayout.CENTER);
    }

    public void setEcouteur(ActionListener ecouteur) {
        bEpargne.addActionListener(ecouteur);
        bDepot.addActionListener(ecouteur);
        bRetrait.addActionListener(ecouteur);
        bTransfert.addActionListener(ecouteur);
        bFacture.addActionListener(ecouteur);
        bHistorique.addActionListener(ecouteur);

        panneauDepot.setEcouteur(ecouteur);
        panneauRetrait.setEcouteur(ecouteur);
        panneauTransfert.setEcouteur(ecouteur);
        panneauPaiementFacture.setEcouteur(ecouteur);
    }

    public void showCard(String card) {
        cardLayout.show(cardPanel, card);
    }

    public void mettreAJourSolde(String typeCompte, String solde) {
        lblSolde.setText("Type de compte : " + typeCompte + " | Solde : " + solde);
    }

    public PanneauDepot getPanneauDepot() {
        return panneauDepot;
    }

    public PanneauRetrait getPanneauRetrait() {
        return panneauRetrait;
    }

    public PanneauTransfert getPanneauTransfert() {
        return panneauTransfert;
    }

    public PanneauPaiementFacture getPanneauPaiementFacture() {
        return panneauPaiementFacture;
    }

    public JButton getBDepot() {
        return bDepot;
    }

    public JButton getBRetrait() {
        return bRetrait;
    }

    public JButton getBTransfert() {
        return bTransfert;
    }

    public JButton getBFacture() {
        return bFacture;
    }

    public JButton getBEpargne() {
        return bEpargne;
    }

}