
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.service.Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleconte, clagadec
 */
public class InitBD {
    
    /**
     * Initialise la base de données.
     */
    public static void main (String [] args) {
        JpaUtil.init();
        Services.importerDonnees("C:/Temp/clients.csv");
        JpaUtil.destroy();
    }
    
}
