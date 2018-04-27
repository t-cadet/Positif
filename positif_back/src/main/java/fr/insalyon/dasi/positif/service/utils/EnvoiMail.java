/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.service.utils;

import fr.insalyon.dasi.positif.modele.Client;

/**
 * 
 * @author aleconte, clagadec
 */
public class EnvoiMail {
    
    public static void envoyerMailConfirmation(Client client) {
        System.out.println("Expediteur : contact@posit.if");
        System.out.println("Pour : " + client.getEmail());
        System.out.println("Sujet : Bienvenue chez POSIT'IF");
        System.out.println("");
        System.out.println("Bonjour " + client.getPrenom() + ",");
        System.out.println("Nous vous confirmons votre inscription au service POSIT'IF. Votre numero de client est : " + client.getNumClient() + ".");
        System.out.println("");
    }
    
    public static void envoyerMailEchec(Client client) {
        System.out.println("Expediteur : contact@posit.if");
        System.out.println("Pour : " + client.getEmail());
        System.out.println("Sujet : Erreur d'inscription chez POSIT'IF");
        System.out.println("");
        System.out.println("Bonjour " + client.getPrenom() + ",");
        System.out.println("Votre inscription au service POSIT'IF a malencontreusement echoue... Merci de recommencer ulterieurement.");
        System.out.println("");
    }
    
}
