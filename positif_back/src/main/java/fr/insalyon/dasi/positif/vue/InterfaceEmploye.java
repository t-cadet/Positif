/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.vue;

import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Employe;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import fr.insalyon.dasi.positif.service.utils.Saisie;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Interface de test en mode console pour l'IHM Employes.
 * @author aleconte, clagadec
 */
public class InterfaceEmploye {
    
    // Variable de session Numéro Employe
    private static int numEmploye = -1;
    
    /**
     * Démarrage de l'application.
     */
    public static void main (String[] args) {
        JpaUtil.init();
        FenetreEmployeConnexion();
        JpaUtil.destroy();
    }
    
    /**
     * Fenêtre Employé Connexion.
     */
    public static void FenetreEmployeConnexion () {
        Employe emp = null;
        do {
            System.out.println ("=============== Espace Employe ===============");
            String tel = Saisie.lireChaine("Saisir le numero de telephone : ");
            int num = Saisie.lireInteger("Saisir le numero employe : ");
            emp = Services.authentifierEmploye(tel, num);
            if(emp == null) {
                System.out.println("Identifiants incorrects. Veuillez reessayer.");
            } else {
                numEmploye = num;
            }
        } while (emp == null);
        System.out.println();
        FenetreEmployeTableauDeBord();
    }
    
    public static void FenetreEmployeTableauDeBord () {
        String intention = null;
        do {
            System.out.println ("=============== Tableau de Bord ===============");
            Employe emp = Services.informationsEmploye(numEmploye);
            System.out.println("Tableau de bord de " + emp.getPrenom() + " " + emp.getNom());

            TreeMap<Integer, Integer> histoMedium = Services.nombreVoyancesParMedium();
            System.out.println (">>> Médium / Nombre de voyance réalisés");
            //Parcours des valeurs
            for (Integer id : histoMedium.keySet()) {
                Medium m = Services.informationsMedium(id);
                System.out.println(m.getNom());
                System.out.println(histoMedium.get(id));
            }

            TreeMap<Integer, Integer> histoEmploye = Services.nombreVoyancesParEmploye();
            System.out.println(">>> Employes / Proportions de voyance realisees");
            //Parcours des valeurs
            for (Integer id : histoEmploye.keySet()) {
                Employe e = Services.informationsEmploye(id);
                System.out.println(e.getNom() + " " + e.getPrenom());
                System.out.println(histoEmploye.get(id));
            }

            TreeMap<Integer, Double> repartition = Services.repartitionVoyances();
            System.out.println(">>> Répartition des voyances par employe");
            //Parcours des valeurs
            for (Integer id : repartition.keySet()) {
                Employe e = Services.informationsEmploye(id);
                System.out.println(e.getNom() + " " + e.getPrenom());
                System.out.println(repartition.get(id));
            }
            
            intention = Saisie.lireChaine("Realiser une voyance ? (oui / deconnexion) : ");
            if(intention.equals("oui")) {
                Voyance v = null;
                do {
                    int num = Saisie.lireInteger("Saisir le numero du client : ");
                    v = Services.obtenirVoyanceDemandee(num);
                    if(v == null) {
                        System.out.println("Aucune demande de voyance en cours pour ce client.");
                    }
                } while (v == null);
                FenetreEmployeConstultation(v.getId());
            }
        } while(intention.equals("oui"));
        numEmploye = -1;
    }
    
    public static void FenetreEmployeConstultation (int voyanceId) {
        System.out.println ("=============== Consultation ===============");
        Voyance voyance = Services.informationsVoyance(voyanceId);
        Client client = voyance.getClient();
        System.out.println(">>> Profil du client : ");
        System.out.println("Nom : " + client.getNom());
        System.out.println("Prénom : " + client.getPrenom());
        System.out.println("Signe du Zodiaque : " + client.getSigneZodiaque());
        System.out.println("Signe astrologique chinois : " + client.getSigneAstroChinois());
        System.out.println("Couleur porte bonheur : " + client.getCouleurPorteBonheur());
        System.out.println("Animal totem : " + client.getAnimalTotem());
        System.out.println();
        
        System.out.println(">>> Historique du client : ");
        Collection<Voyance> historique = Services.hitoriqueVoyancesClient(client);
        for (Voyance v : historique) {
            System.out.println(v);
        }
        
        System.out.println(">>> Générer une prédiction : ");
        int amour = Saisie.lireInteger("Saisir une note amour : ");
        int sante = Saisie.lireInteger("Saisir une note sante : ");
        int travail = Saisie.lireInteger("Saisir une note travail : ");
        List<String> prediction = Services.genererPrediction(client, amour, sante, travail);
        System.out.println(prediction);
        
        System.out.println(">>> Voyance commencee...");
        Services.demarrerVoyance(voyance, new Date(System.currentTimeMillis()));
        System.out.println(">>> Voyance terminee...");
        Services.terminerVoyance(voyance, new Date(System.currentTimeMillis()+10000));
        System.out.println(">>> Ecrire un commentaire et valider...");
        String commentaire = Saisie.lireChaine("Commentaire : ");
        Services.validerVoyance(voyance, commentaire);
        System.out.println();
    }
    
}
