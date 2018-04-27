/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.positif.service;

import fr.insalyon.dasi.positif.service.utils.AstroApi;
import fr.insalyon.dasi.positif.dao.ClientDAO;
import fr.insalyon.dasi.positif.dao.EmployeDAO;
import fr.insalyon.dasi.positif.dao.MediumDAO;
import fr.insalyon.dasi.positif.dao.VoyanceDAO;
import fr.insalyon.dasi.positif.modele.Astrologue;
import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Employe;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.modele.Tarologue;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.modele.Voyant;
import fr.insalyon.dasi.positif.service.utils.EnvoiMail;
import fr.insalyon.dasi.positif.service.utils.EnvoiSMS;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author aleconte, clagadec
 */
public class Services {
    
    /**
     * Instance d'AstroApi.
     * Cette API nous permet d'utiliser le service en ligne de generation de predictions.
     */
    private final static AstroApi astro = new AstroApi();
    
    /**
     * Authentifier un Client.
     * Si l'authentification a reussi, retourne les informations du client.
     * Sinon retourne null.
     */
    public static Client authentifierClient(String email, int numClient) {
        Client c = ClientDAO.obtenir(numClient);
        if(c != null && c.getEmail().equals(email)) {
            return c;
        }
        return null;
    }
    
    /**
     * Authentifier un Employe.
     * Si l'authentification a reussi, retourne les informations de l'employe.
     * Sinon retourne null.
     */
    public static Employe authentifierEmploye(String numTelephone, int numEmploye) {
        Employe e = EmployeDAO.obtenir(numEmploye);
        if(e != null && e.getNumTelephone().equals(numTelephone)) {
            return e;
        }
        return null;
    }
    
    /**
     * Obtenir un Client.
     * Retourne l'objet Client correspondant au numero client numClient.
     * L'objet Client contient notamment le profil astrologique du client.
     * Si ce client n'existe pas dans la base de donnees, retourne null.
     */
    public static Client informationsClient(int numClient) {
        return ClientDAO.obtenir(numClient);
    }
    
    /**
     * Obtenir un Employe.
     * Retourne l'objet Employe correspondant au numero employe numEmploye.
     * Si cet employe n'existe pas dans la base de donnees, retourne null.
     */
    public static Employe informationsEmploye(int numEmploye) {
        return EmployeDAO.obtenir(numEmploye);
    }
    
    /**
     * Obtenir un Medium.
     * Retourne l'objet Medium correspondant a l'id.
     * Si ce medium n'existe pas dans la base de donnees, retourne null.
     */
    public static Medium informationsMedium(int id) {
        return MediumDAO.obtenir(id);
    }
    
    /**
     * Obtenir une Voyance.
     * Retourne l'objet Voyance correspondant à cet id.
     * Si cette voyance n'existe pas dans la base de donnees, retourne null.
     */
    public static Voyance informationsVoyance(int id) {
        return VoyanceDAO.obtenir(id);
    }
    
    /**
     * Verifier les informations d'inscription d'un client.
     * Verifie si toutes les informations necessaires sont fournies.
     * Tous les champs doivent etre remplis.
     * Le numero de telephone ne doit comporter que des caracteres numeriques.
     * Retourne true si les informations sont correctes, et false sinon.
     */
    public static boolean verifierInformationsInscriptionClient(String nom, String prenom,
            Date dateNaissance, String civilite, String adressePostale, String numTelephone, String email) {
        if(nom == null || nom.isEmpty())
            return false;
        if(prenom == null || prenom.isEmpty())
            return false;
        if(dateNaissance == null || dateNaissance.after(new Date(System.currentTimeMillis())))
            return false;
        if(civilite == null)
            return false;
        if(adressePostale == null || adressePostale.isEmpty())
            return false;
        if(numTelephone == null || !numTelephone.matches("[0-9]+"))
            return false;
        if(email == null || !email.contains("@"))
            return false;
        
        return true;
    }
    
    /**
     * Inscrire un Client dans la base de donnees.
     * Inscrit le client sur le site. Un mail sera envoye au client.
     */
    public static void inscrireClient(String nom, String prenom,
            Date dateNaissance, String civilite, String adressePostale, String numTelephone, String email) {
        // Initialiser les informations personnelles du client
        Client client = new Client(nom, prenom, dateNaissance, civilite, adressePostale, numTelephone, email);
        try {
            // Calculer le profil astro
            List<String> profil = astro.getProfil(prenom, dateNaissance);
            client.setSigneZodiaque(profil.get(0));
            client.setSigneAstroChinois(profil.get(1));
            client.setCouleurPorteBonheur(profil.get(2));
            client.setAnimalTotem(profil.get(3));
            
            // Persister
            if(!ClientDAO.creer(client)) {
                // La création du client a échoué
                throw new Exception("Erreur de ClientDAO: impossible de persister le client.");
            }

            // Envoyer le mail de succès
            EnvoiMail.envoyerMailConfirmation(client);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Envoyer le mail d'erreur
            EnvoiMail.envoyerMailEchec(client);
        }
    }
    
    /**
     * Obtenir la liste des mediums.
     * Retourne la liste des mediums du site, tries par ordre alphabetique du nom.
     * Retourne null en cas d'erreur.
     */
    public static Collection<Medium> listerMediums() {
        return MediumDAO.lister();
    }
    
    /**
     * Obtenir l'historique des voyances d'un client.
     * Retourne une liste des voyances deja effectuees par le client.
     * Les voyances en cours ne sont pas comptabilisees.
     * La liste est triee par date de debut decroissante.
     * Retourne null en cas d'erreur.
     */
    public static Collection<Voyance> hitoriqueVoyancesClient (Client client) {
        return VoyanceDAO.listerVoyancesPourClient(client);
    }
    
    /**
     * Obtenir le nombre de voyances faites par chaque employe.
     * Retourne une table qui associe chaque numero d'employe au nombre de voyances
     * que l'employe a deja effectuees. les voyances en cours ne sont pas comptabilisees.
     * Retourne null en cas d'erreur.
     */
    public static TreeMap<Integer, Integer> nombreVoyancesParEmploye() {
        TreeMap<Integer, Integer> histo = new TreeMap<Integer, Integer>();
        Collection<Voyance> voyances = VoyanceDAO.lister();
        Collection<Employe> employes = EmployeDAO.lister();
        try {
            // Initialisation de l'histogramme
            for(Employe e : employes) {
                histo.put(e.getNumEmploye(), 0);
            }
            // Construction de l'histogramme
            for(Voyance v : voyances) {
                if(v.getFin() != null) { // Si la voyance a bien été faite
                    int n = histo.get(v.getEmploye().getNumEmploye());
                    histo.replace(v.getEmploye().getNumEmploye(), n+1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return histo;
    }
    
    /**
     * Obtenir le nombre de voyances faites par chaque medium.
     * Retourne une table qui associe chaque id de medium au nombre de voyances
     * que le medium a deja effectuees. les voyances en cours ne sont pas comptabilisees.
     * Retourne null en cas d'erreur.
     */
    public static TreeMap<Integer, Integer> nombreVoyancesParMedium() {
        TreeMap<Integer, Integer> histo = new TreeMap<Integer, Integer>();
        Collection<Voyance> voyances = VoyanceDAO.lister();
        Collection<Medium> mediums = MediumDAO.lister();
        try {
            // Initialisation de l'histogramme
            for(Medium m : mediums) {
                histo.put(m.getId(), 0);
            }
            // Construction de l'histogramme
            for(Voyance v : voyances) {
                if(v.getFin() != null) { // Si la voyance a bien été faite
                    int n = histo.get(v.getMedium().getId());
                    histo.replace(v.getMedium().getId(), n+1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return histo;
    }
    
    /**
     * Obtenir la repartition des voyances entre les employes.
     * Retourne une table qui associe chaque numero d'employe au pourcentage de voyances
     * qu'il a effectuees. les voyances en cours ne sont pas comptabilisees.
     * Retourne null en cas d'erreur.
     */
    public static TreeMap<Integer, Double> repartitionVoyances() {
        try {
            // Nombre de voyances par employes
            TreeMap<Integer, Integer> histo = Services.nombreVoyancesParEmploye();
            
            // Calcul du rapport a appliquer
            int nombreVoyances = 0;
            for(Map.Entry<Integer,Integer> entry : histo.entrySet()) {
                nombreVoyances += entry.getValue();
            }
            double ratio = 100.0 / nombreVoyances;

            // Calcul du pourcentage pour chaque employe
            TreeMap<Integer, Double> camembert = new TreeMap<Integer, Double>();
            for(Map.Entry<Integer,Integer> entry : histo.entrySet()) {
                camembert.put(entry.getKey(), entry.getValue() * ratio);
            }
            return camembert;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Demander une voyance.
     * Genere et persiste une nouvelle voyance, et lui affecte un employe.
     * Retourne true si la voyance a ete affectee a un employe, et false si aucun employe
     * n'est disponible ou si le client a deja une voyance en cours (dans ce cas la voyance
     * n'est pas persistee).
     */
    public static boolean demanderVoyance(Client client, Medium medium) {
        // Affecter l'employe
        Employe employe = null;
        
        // Liste de toutes les voyances en cours
        Collection<Voyance> voyances = VoyanceDAO.listerVoyancesEnCours();
        // Liste des employes pouvant incarner ce medium
        Collection<Employe> employes = medium.getEmployes();
        
        // Retirer de la liste des employes ceux qui ont deja une voyance en cours
        for (Voyance v : voyances) {
            if (v.getFin() == null) { // Cette Voyance est bien en cours
                // Si la voyance est en cours et concerne notre client
                if(v.getClient().equals(client)) {
                    // On n'a pas le droit de demander une nouvelle voyance pour ce client
                    return false;
                }
                // Cet employe n'est pas disponible
                employes.remove(v.getEmploye());
            }
        }
        
        // Si aucun employe n'est disponible pour incarner ce medium
        if(employes.isEmpty()) {
            // Pas possible d'effectuer cette voyance
            return false;
        }
        
        // Parmi les employes restants, on prend celui qui a fait le moins de voyances
        Employe[] employesTab = (Employe[])employes.toArray(new Employe[0]);
        TreeMap<Integer, Integer> histo = nombreVoyancesParEmploye();
        int min = 0;
        for (int i=1; i<employesTab.length; i++) {
            if(histo.get(employesTab[i].getNumEmploye()) <= histo.get(employesTab[min].getNumEmploye())) {
                min = i;
            }
        }
        employe = employesTab[min];

        // Créer et persister la voyance
        Voyance voyance = VoyanceDAO.creer(client, medium, employe);
        
        if(voyance == null) {
            return false;
        }
        
        // Envoyer un message à l'employé
        EnvoiSMS.envoyerSMSDemandeVoyance(voyance);
        return true;
    }

    /**
     * Obtenir la demande de voyance d'un client.
     * Retourne la derniere voyance demandee par le client, qui n'a pas encore ete realisee.
     * Retourne null si le client n'a pas fait de demande de voyance non satisfaite.
     */
    public static Voyance obtenirVoyanceDemandee(int numClient) {
        Collection<Voyance> voyances = VoyanceDAO.listerVoyancesEnCours();
        for (Voyance v : voyances) {
            // Si la voyance est en cours et concerne notre client
            if (v.getFin() == null && v.getClient().getNumClient() == numClient) {
                return v;
            }
        }
        return null;
    }

    /**
     * Obtenir des predictions pour un client.
     * Genere une liste de predictions (amour, sante, travail). Les notes amour, sante, travail
     * doivent etre des entiers entre 1 et 4 inclus.
     * Retourne null en cas d'erreur.
     */
    public static List<String> genererPrediction(Client client, int amour, int sante, int travail) {
        try {
            return astro.getPredictions(client.getCouleurPorteBonheur(), client.getAnimalTotem(), amour, sante, travail);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Demarre une voyance.
     * Met a jour l'heure de debut de la voyance.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean demarrerVoyance (Voyance v, Date dateDebut) {
        if (v != null) {
            v.setDebut(dateDebut);
            if(VoyanceDAO.mettreAJour(v) == null) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Termine une voyance.
     * Met a jour l'heure de fin de la voyance.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean terminerVoyance (Voyance v, Date dateFin) {
        if (v != null) {
            v.setFin(dateFin);
            if(VoyanceDAO.mettreAJour(v) == null) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Valide une voyance.
     * Met à jour le comentaire d'une voyance.
     * Retourne true en cas de succes, false en cas d'echec.
     */
    public static boolean validerVoyance (Voyance v, String commentaire) {
        if (v != null) {
            v.setCommentaire(commentaire);
            if(VoyanceDAO.mettreAJour(v) == null) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * Importer les employes et mediums.
     * Service de creation en dur dans le code des employes et des mediums.
     * Importe aussi les clients enregistrés dans le fichier CSV fichierClients
     */
    public static void importerDonnees (String fichierClients) {
        // Importer les employes
        Employe e1 = new Employe("GIREUX", "Zouhair", "0123456789");
        Employe e2 = new Employe("TCHIUMAKOVA", "Nicolas", "10123456789");
        Employe e3 = new Employe("KEMARO", "Cedric", "2123456789");
        Employe e4 = new Employe("PAMITESCU", "Olena", "3123456789");
        
        EmployeDAO.creer(e1);
        EmployeDAO.creer(e2);
        EmployeDAO.creer(e3);
        EmployeDAO.creer(e4);
        
        // Importer les mediums
        Voyant m1 = new Voyant("Gwenael", "Voyant", "Boule de Cristal", "Specialiste des grandes conversations au-dela de TOUTES les frontieres");
        m1.addEmployes(e4);
        
        Voyant m2 = new Voyant("J. Dalmarre", "Voyant", "Marc de Cafe", "Votre avenir est devant vous: regardons-le ensemble!");
        m2.addEmployes(e1);
        m2.addEmployes(e2);
        m2.addEmployes(e3);
        
        Tarologue m3 = new Tarologue("Mme Irma", "Tarologue", "Tarot de Marseille", "Comprenez votre entourage grace a mes cartes! Resultats rapides.");
        m3.addEmployes(e4);
        
        Tarologue m4 = new Tarologue("Mme Lisa Maria NGUYINIA", "Tarologue", "Tarot de Broceliande", "Mes cartes specialisees pour la region Bretagne repondront a toutes vos questions personnelles");
        m4.addEmployes(e4);
        
        Astrologue m5 = new Astrologue("Mme Sara", "Astrologue", "Ecole Normale Superieure d'Astrologie (ENS-Astro)", "2006", "Basee a CHampigny-sur-Marne, Mme Sara vous revelera votre avenir pour eclairer votre passe");
        m5.addEmployes(e4);
        
        Astrologue m6 = new Astrologue("Mme Mounia Mounia", "Astrologue", "Institut des Nouveaux Savoirs Astrologiques", "2010", "Avenir, avenir, que nous reserves-tu? N'attendez plus, demandez a me consulter!");
        m6.addEmployes(e4);
        
        MediumDAO.creer(m1);
        MediumDAO.creer(m2);
        MediumDAO.creer(m3);
        MediumDAO.creer(m4);
        MediumDAO.creer(m5);
        MediumDAO.creer(m6);
        
        // Importer les clients
        if(fichierClients != null) {
            try {
                SimpleDateFormat format = AstroApi.JSON_DATE_FORMAT;
                BufferedReader fichier = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichierClients))));
                String line;
                while((line = fichier.readLine()) != null && !line.isEmpty()) {
                    String[] infos = line.split(";");
                    Services.inscrireClient(infos[1], infos[2], format.parse(infos[3]), infos[0], infos[4], infos[5], infos[6]);
                }
                fichier.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}