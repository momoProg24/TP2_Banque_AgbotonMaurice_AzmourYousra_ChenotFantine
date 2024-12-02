package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;
    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        //  QUESTION 3
        // Vérifie si l'utilisateur a double-cliqué
        if (evt.getClickCount() == 2) {
            Object source = evt.getSource();

            if (source instanceof JList) {
                JList<?> listeComptes = (JList<?>) source;
                Object compteSelectionne = listeComptes.getSelectedValue();

                if (compteSelectionne != null) {
                    // Convertir l'objet en chaîne et analyser
                    String compteStr = compteSelectionne.toString().toLowerCase();

                    String typeCompte = null;
                    if (compteStr.contains("cheque")) {
                        typeCompte = "cheque";
                    } else if (compteStr.contains("epargne")) {
                        typeCompte = "epargne";
                    }

                    if (typeCompte != null) {
                        client.envoyer("SELECT " + typeCompte); // Envoi au serveur
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Type de compte inconnu. Veuillez vérifier la sélection.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Aucun compte sélectionné. Veuillez choisir un compte.",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
