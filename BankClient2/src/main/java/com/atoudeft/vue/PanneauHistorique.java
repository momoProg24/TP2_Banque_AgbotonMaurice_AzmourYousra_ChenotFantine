package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauHistorique extends JPanel {
    private JTextArea texteHistorique;
    private JScrollPane scrollPane;

    public PanneauHistorique() {
        this.setLayout(new BorderLayout());
        texteHistorique = new JTextArea(20, 40);
        texteHistorique.setEditable(false);
        scrollPane = new JScrollPane(texteHistorique);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // Méthode pour mettre à jour l'historique affiché
    public void afficherHistorique(String historique) {
        texteHistorique.setText(historique);
    }
}
