/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Employe;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.modele.Voyance;
import java.util.Collection;
import javax.persistence.Query;

/**
 *
 * @author aleconte, clagadec
 */
public class VoyanceDAO {
    
    /**
     * Persiste une voyance dans la BD.
     * Retourne la voyance en cas de succes, null en cas d'echec.
     */
    public static Voyance creer(Client client, Medium medium, Employe employe) {
        Voyance voyance = new Voyance(client, medium, employe);
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            JpaUtil.obtenirEntityManager().persist(voyance);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            voyance = null;
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerEntityManager();
        return voyance;
    }
    
    /**
     * Recherche une voyance dans la BD.
     * Retourne la voyance en cas de succes, null en cas d'echec.
     */
    public static Voyance obtenir(int id) {
        JpaUtil.creerEntityManager();
        Voyance v = null;
        try {
            v = JpaUtil.obtenirEntityManager().find(Voyance.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return v;
    }
    
    /**
     * Met a jour une voyance existante dans la BD.
     * Retourne la voyance mise a jour en cas de succes, null en cas d'echec.
     */
    public static Voyance mettreAJour (Voyance voyance) {
        Voyance v = null;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            v = JpaUtil.obtenirEntityManager().find(Voyance.class, voyance.getId());
            v.setClient(voyance.getClient());
            v.setEmploye(voyance.getEmploye());
            v.setMedium(voyance.getMedium());
            v.setDebut(voyance.getDebut());
            v.setFin(voyance.getFin());
            v.setCommentaire(voyance.getCommentaire());
            JpaUtil.obtenirEntityManager().merge(v);
            JpaUtil.validerTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            v = null;
            JpaUtil.annulerTransaction();
        }
        JpaUtil.fermerEntityManager();
        return v;
    }
    
    /**
     * Recherche la liste de toutes les voyances de la BD.
     * La liste est triée par date de début décroissante.
     * Retourne la liste en cas de succes, null en cas d'echec.
     */
    public static Collection<Voyance> lister() {
        JpaUtil.creerEntityManager();
        Collection<Voyance> voyances = null;
        try {
            String q = "SELECT v FROM Voyance v ORDER BY v.debut DESC";
            Query query = JpaUtil.obtenirEntityManager().createQuery(q);
            voyances = (Collection<Voyance>) query.getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return voyances;
    }
    
    /**
     * Recherche la liste de toutes les voyances effectuees concernant le client dans la BD.
     * La liste est triée par date de début décroissante.
     * Les voyances en cours ne sont pas comptabilisees.
     * Retourne la liste en cas de succes, null en cas d'echec.
     */
    public static Collection<Voyance> listerVoyancesPourClient(Client client) {
        JpaUtil.creerEntityManager();
        Collection<Voyance> voyances = null;
        try {
            String q = "SELECT v FROM Voyance v WHERE v.client = :client AND v.fin IS NOT NULL ORDER BY v.debut DESC";
            Query query = JpaUtil.obtenirEntityManager().createQuery(q);
            query.setParameter("client", client);
            voyances = query.getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return voyances;
    }
    
    /**
     * Recherche la liste de toutes les voyances en cours dans la BD.
     * Retourne la liste en cas de succes, null en cas d'echec.
     */
    public static Collection<Voyance> listerVoyancesEnCours() {
        JpaUtil.creerEntityManager();
        Collection<Voyance> voyances = null;
        try {
            String q = "SELECT v FROM Voyance v WHERE v.fin IS NULL";
            Query query = JpaUtil.obtenirEntityManager().createQuery(q);
            voyances = query.getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return voyances;
    }
    
}
