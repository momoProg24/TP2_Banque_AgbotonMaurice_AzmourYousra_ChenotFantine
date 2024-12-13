package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;
import java.util.Arrays;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
//                case "HIST": //Le serveur a renvoyé
//                    panneauPrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(null,"Panneau visible");
//                    cnx.envoyer("LIST");
//                    arg = evenement.getArgument();
//                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    arg = evenement.getArgument();
                    if (arg.startsWith("OK")) {
                        String nouveauCompte = arg.substring(3).trim();
                        panneauPrincipal.ajouterCompte(nouveauCompte);
                        JOptionPane.showMessageDialog(panneauPrincipal,
                                " Le compte épargne a été créer avec succès : " + nouveauCompte);
                    } else {
                        JOptionPane.showMessageDialog(panneauPrincipal,
                                "Vous avez déja un compte épargne: " + arg,
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "SELECT":
                    arg = evenement.getArgument();

                    // Affichage pour débogage
                    System.out.println("Réponse brute du serveur : " + arg);

                    if (arg.startsWith("OK")) {
                        String detailsCompte = arg.substring(3).trim(); // Retirer "OK " et récupérer le reste
                        System.out.println("Détails du compte après 'OK' : " + detailsCompte);

                        // Split avec espace pour séparer les éléments
                        String[] parts = detailsCompte.split(" ");
                        System.out.println("Tableau après split : " + Arrays.toString(parts));

                        // Vérifiez qu'il y a au moins deux éléments : numéro du compte et solde
                        if (parts.length >= 2) {
                            String numeroCompte = parts[0]; // Identifiant du compte
                            String solde = parts[1];       // Solde du compte

                            // Mettre à jour l'interface
                            panneauPrincipal.getPanneauOperationsCompte().mettreAJourSolde("Chèque ou Épargne", solde);

                            JOptionPane.showMessageDialog(panneauPrincipal,
                                    "Compte sélectionné : " + numeroCompte + "\nSolde : " + solde,
                                    "Sélection réussie",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(panneauPrincipal,
                                    "Réponse mal formatée : " + detailsCompte,
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (arg.startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,
                                "Aucun compte sélectionné ou échec de la sélection.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panneauPrincipal,
                                "Erreur lors de la sélection du compte : " + arg,
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT":
                    arg = evenement.getArgument();
                    if (arg.startsWith("OK")) {
                        String nouveauSolde = arg.substring(3).trim();
                        panneauPrincipal.getPanneauOperationsCompte().mettreAJourSolde("Chèque", nouveauSolde);
                        JOptionPane.showMessageDialog(panneauPrincipal, "Dépôt effectué avec succès. Nouveau solde : " + nouveauSolde, "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panneauPrincipal, "Erreur lors du dépôt : " + arg, "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case "RETRAIT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"FACTURE" + arg);
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER " + arg);
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}