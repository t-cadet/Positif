/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Employe;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexandre
 */
public class LoginEmployeAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        String tel = request.getParameter("tel");
        String numEmploye = request.getParameter("numEmploye");
        
        System.out.println("ffffffffffffffff");
        System.out.println(numEmploye);
        
        Employe e = Services.authentifierEmploye(tel, Integer.parseInt(numEmploye));
            
        System.out.println(e.toString());
        
        if(e != null) {
            request.setAttribute("success", true);
            request.setAttribute("data", e);
        }else {
            request.setAttribute("success", false);
        }
    }
}
