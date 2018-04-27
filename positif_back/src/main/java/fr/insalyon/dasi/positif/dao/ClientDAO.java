/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.modele.Client;

/**
 *
 * @author aleconte, clagadec
 */
public class ClientDAO {
    
    /**
     * Persiste un client dans la BD.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean creer(Client client) {
        boolean succes = true;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            JpaUtil.obtenirEntityManager().persist(client);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            succes = false;
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerEntityManager();
        return succes;
    }
    
    /**
     * Recherche un client dans la BD.
     * Retourne le client en cas de succes, null en cas d'echec.
     */
    public static Client obtenir(int numClient) {
        JpaUtil.creerEntityManager();
        Client c = null;
        try {
            c = JpaUtil.obtenirEntityManager().find(Client.class, numClient);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return c;
    }
    
}
