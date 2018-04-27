/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.dao;

import fr.insalyon.dasi.positif.modele.Employe;
import java.util.Collection;
import javax.persistence.Query;

/**
 *
 * @author aleconte, clagadec
 */
public class EmployeDAO {
    
    /**
     * Persiste un employe dans la BD.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean creer(Employe employe) {
        boolean succes = true;
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        try {
            JpaUtil.obtenirEntityManager().persist(employe);
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
     * Recherche un employe dans la BD.
     * Retourne l'employe en cas de succes, null en cas d'echec.
     */
    public static Employe obtenir(int numEmploye) {
        JpaUtil.creerEntityManager();
        Employe e = null;
        try {
            e = JpaUtil.obtenirEntityManager().find(Employe.class, numEmploye);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return e;
    }
    
    /**
     * Recherche la liste de tous les employes dans la BD.
     * Retourne la liste des employes en cas de succes, null en cas d'echec.
     */
    public static Collection<Employe> lister() {
        JpaUtil.creerEntityManager();
        Collection<Employe> employes = null;
        try {
            String q = "SELECT e FROM Employe e ORDER BY e.nom ASC";
            Query query = JpaUtil.obtenirEntityManager().createQuery(q);
            employes = (Collection<Employe>) query.getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        JpaUtil.fermerEntityManager();
        return employes;
    }
    
}
