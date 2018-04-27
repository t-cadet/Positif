
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.dao.MediumDAO;
import fr.insalyon.dasi.positif.modele.Astrologue;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.service.utils.Saisie;
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Tests pour la gestion des médiums.
 * @author aleconte, clagadec
 */
public class GestionAstrologue {
    
    public static void main (String[] args) {
        JpaUtil.init();
        
        Astrologue astrologue = GestionAstrologue.creerAstrologue();
        GestionAstrologue.afficherProfilMedium(astrologue);
        GestionAstrologue.listerMedium();
        
        JpaUtil.destroy();
    }
    
    public static Astrologue creerAstrologue () {
        String nom = Saisie.lireChaine("Saisir le nom du médium astrologue : ");
        String talent = "Astrologue";
        String ecole = Saisie.lireChaine("Saisir l'école du médium : ");
        String promo = Saisie.lireChaine("Saisir la promotion du médium : ");
        String bio = Saisie.lireChaine("Sasir la bio du médium : ");
        Astrologue astrologue = new Astrologue(nom,talent,ecole, promo, bio);
        MediumDAO.creer(astrologue);
        return astrologue;
    }
    
    public static void afficherProfilMedium (Medium medium) {
        System.out.println("Medium créé");
        System.out.println(medium);
    }
    
    public static void listerMedium () {
        System.out.println("Liste de l'ensemble des employés enregistrés");
        Collection<Medium> mediums = MediumDAO.lister();
        for (Medium m : mediums) {
            System.out.println(m);
        }
    }
    
}
