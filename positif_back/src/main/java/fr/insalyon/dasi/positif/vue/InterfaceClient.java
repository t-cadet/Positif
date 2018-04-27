/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.service.utils.Saisie;
import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Interface de test en mode console pour l'IHM Client.
 * @author aleconte, clagadec
 */
public class InterfaceClient {
    
    public static void main (String[] args) {
        JpaUtil.init();
        
        boolean enable = true;
        while (enable) {
            Client client;
            System.out.println("Interface présentant les services disponibles pour le client chez POSIT'IF");
            System.out.println("1. Connexion");
            System.out.println("2. Inscritpion");
            //Demande à l'utilisateur s'il souhaite s'incrire ou se connecter
            int choix = Saisie.lireInteger("Choisir un nombre entre 1 et 2 : ", Arrays.asList(1,2));
            if (choix == 1) {
                client = InterfaceClient.connexion();
                int fenetre = -1;
                if (client == null) {
                    System.out.println ("Problème d'authentification");
                    fenetre = 4;
                }
                while (fenetre != 4) {
                    System.out.println("1. Profil Astrologique");
                    System.out.println("2. Consulter un médium");
                    System.out.println("3. Historique des consulations");
                    System.out.println("4. Deconnexion");
                    fenetre = Saisie.lireInteger("Choisir une commande : ", Arrays.asList(1,2,3,4));
                    switch (fenetre) {
                        case 1 :
                            InterfaceClient.fenetreProfil(client);
                        break;
                        case 2 :
                            InterfaceClient.fenetreAfficherMedium();
                            boolean voyance = false;
                            while (!voyance) {
                                int idMedium = Saisie.lireInteger("Saisir l'identifiant d'un médium pour demander une voyance : ");
                                Medium medium = Services.informationsMedium(idMedium);
                                if (medium != null) {
                                    InterfaceClient.demanderVoyance(client, medium);
                                    voyance = true;
                                } else {
                                    System.out.println ("L'identifiant ne correspond pas à un médium de POSIT'IF");
                                }
                            }
                        break;
                        case 3 : 
                            InterfaceClient.fenetreHistoriqueClient(client);
                        break;
                        case 4 :
                            enable = false;
                        break;
                    }
                    Saisie.pause();
                }

            } else {
                try {
                    InterfaceClient.inscription();
                    System.out.println("Veuillez réinitialiser la session pour vous connecter");
                } catch (Exception e) {}            
            }
        }        
        JpaUtil.destroy();
    }
    
    /** 
     * Connexion d'un client
     * Saisie de l'email et du numéro client nécessaire pour identifier et sécuriser
     * la connexion d'un client, renvoie un Client
     * @return Client
     */
    public static Client connexion () {
        //Fenêtre de connexion
        System.out.println ("   Fenêtre de connexion Client");
        String email = Saisie.lireChaine("Saisir l'adresse electronique : ");
        int numclient = Saisie.lireInteger("Saisir le numéro client : ");
        System.out.println();
        // Vérification de la connexion avec les données de la base
        return Services.authentifierClient(email, numclient);          // TODO Afficher un message d'erreur !!!
    }
    
    /**
     * Insciption d'un client
     * Saisie de l'ensemble des données d'identité et des coordonnées de l'utilisateur 
     * nécessaire à l'inscription chez POSIT'IF
     * @throws ParseException 
     */
    public static void inscription () throws ParseException {
        //Fenêtre d'inscritpion
        System.out.println("    Fenêtre d'inscription");
        System.out.println("Identité : ");
        String nom = "Nom : ";
        nom = Saisie.lireChaine(nom);
        String prenom = "Prénom : ";
        prenom = Saisie.lireChaine(prenom);
        // Date de naissance 
        SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
        int jour = Saisie.lireInteger("Jour de naissance : ", Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31));
        int mois = Saisie.lireInteger("Mois de naissance : ", Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
        int annee = Saisie.lireInteger("Année de naissance : ");
        String date = jour + "/" + mois + "/" + annee;
        Date naissance = sdf.parse(date);
        
        String civilite = "Civilité : ";
        civilite = Saisie.lireChaine(civilite);
        System.out.println("Coordonnées : ");
        String adressePostale = "Adresse Postale : ";
        adressePostale = Saisie.lireChaine(adressePostale);
        String tel = "Telephone : ";
        tel = Saisie.lireChaine(tel);
        String adresseMail = "Adresse electronique : ";
        adresseMail = Saisie.lireChaine(adresseMail);
        System.out.println();
        boolean verif = Services.verifierInformationsInscriptionClient(nom, prenom, naissance, civilite, adressePostale, tel, adresseMail);
        if (verif) {
            //Enregistrement du client et envoie du mail de confirmation
            Services.inscrireClient(nom, prenom, naissance, civilite, adressePostale, tel, adresseMail);
        } else {
            System.out.println ("Les informations saisies ne sont pas correctes, veuillez réessayer");
        }
        
    }
    
    /**
     * fenetreProfil
     * Affichage du profil du client passé en paramètre
     * @param client 
     */
    public static void fenetreProfil (Client client) {
        // Affichage du profil du client
        String text = client.getPrenom() + " " + client.getNom();
        System.out.println (text);
        System.out.println();
        System.out.println("Signe du Zodiaque : " + client.getSigneZodiaque());
        System.out.println("Signe Astrologique Chinois : " + client.getSigneAstroChinois());
        System.out.println("Couleur Porte Bonheur : " + client.getCouleurPorteBonheur());
        System.out.println("Animal Totem : " + client.getAnimalTotem());
        System.out.println();        
    }
    
    /**
     * fenetreAfficherMedium
     * Affichage de l'ensemble des médiums consultables chez POSIT'IF ainsi que leurs informations
     */
    public static void fenetreAfficherMedium () {
        // Affichage de l'ensemble des mediums
        System.out.println("    Liste des médiums consultables : ");
        Collection<Medium> mediums = Services.listerMediums();
        for (Medium m : mediums) {
            System.out.println(m);
        }
    }
    
    /**
     * demanderVoyance
     * Permet d'appeler le service pour demander une voyance avec un médium sélectionné
     * @param client
     * @param medium 
     */
    public static void demanderVoyance(Client client, Medium medium) {
        Services.demanderVoyance(client, medium);
    }
    
    /**
     * fenetreHistoriqueClient
     * Affichage de l'ensemble des voyances étant été effectuées par le client
     * @param client 
     */
    public static void fenetreHistoriqueClient (Client client) {
        System.out.println("    Historique client : ");
        Collection<Voyance> voyances = Services.hitoriqueVoyancesClient(client);
        for (Voyance v : voyances) {
            System.out.println(v);
        }
    }
}
