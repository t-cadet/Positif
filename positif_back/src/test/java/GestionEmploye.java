
import fr.insalyon.dasi.positif.dao.EmployeDAO;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.modele.Employe;
import fr.insalyon.dasi.positif.service.utils.Saisie;
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Tests pour la gestion des employés.
 * @author aleconte, clagadec
 */
public class GestionEmploye {
    
    public static void main (String[] args) {
        JpaUtil.init();
        
        Employe employe = GestionEmploye.creerEmploye();
        GestionEmploye.afficherProfilEmploye(employe);
        GestionEmploye.listerEmploye();
        
        JpaUtil.destroy();
    }
    
    public static Employe creerEmploye () {
        String nom = Saisie.lireChaine("Saisir le nom de l'employé : ");
        String prenom = Saisie.lireChaine("Saisir le prénom de l'employé : ");
        String tel = Saisie.lireChaine("Saisir le numéro de téléphone de l'employé : ");
        Employe employe = new Employe(nom,prenom,tel);
        EmployeDAO.creer(employe);
        return employe;
    }
    
    public static void afficherProfilEmploye (Employe employe) {
        System.out.println("Employé créé");
        System.out.println(employe);
    }
    
    public static void listerEmploye () {
        System.out.println("Liste de l'ensemble des employés enregistrés");
        Collection<Employe> employes = EmployeDAO.lister();
        for (Employe e : employes) {
            System.out.println(e);
        }
    }
}
