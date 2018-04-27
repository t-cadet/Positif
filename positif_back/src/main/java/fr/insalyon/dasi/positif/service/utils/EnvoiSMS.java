/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.service.utils;

import fr.insalyon.dasi.positif.modele.Voyance;

/**
 * 
 * @author aleconte, clagadec
 */
public class EnvoiSMS {
    
    public static void envoyerSMSDemandeVoyance(Voyance voyance) {
        System.out.println("Voyance demandee pour " + voyance.getClient().getPrenom() + " " + voyance.getClient().getNom() + " (#" + voyance.getId() + "), Medium : " + voyance.getMedium());
        System.out.println("");
    }
    
}
