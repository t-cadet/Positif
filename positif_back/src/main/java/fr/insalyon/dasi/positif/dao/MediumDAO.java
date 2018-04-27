/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.modele.Medium;
import java.util.Collection;
import javax.persistence.Query;

/**
 *
 * @author aleconte, clagadec
 */
public class MediumDAO {
    
    /**
     * Persiste un medium dans la BD.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean creer(Medium medium) {
        boolean succes = true;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            JpaUtil.obtenirEntityManager().persist(medium);
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
     * Recherche un medium dans la BD.
     * Retourne le medium en cas de succes, null en cas d'echec.
     */
    public static Medium obtenir(int id) {
        JpaUtil.creerEntityManager();
        Medium m = null;
        try {
            m = JpaUtil.obtenirEntityManager().find(Medium.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return m;
    }
    
    /**
     * Recherche la liste de tous les mediums dans la BD.
     * La liste est triee par ordre alphabetique du nom des mediums.
     * Retourne la liste des mediums en cas de succes, null en cas d'echec.
     */
    public static Collection<Medium> lister() {
        JpaUtil.creerEntityManager();
        Collection<Medium> mediums = null;
        try {
            String q = "SELECT m FROM Medium m ORDER BY m.nom ASC";
            Query query = JpaUtil.obtenirEntityManager().createQuery(q);
            mediums = (Collection<Medium>) query.getResultList();
        } catch(Exception e) {
            e.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return mediums;
    }
    
}
