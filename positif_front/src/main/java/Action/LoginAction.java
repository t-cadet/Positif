/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Controller.ActionServlet;
import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.service.Services;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexandre
 */
public class LoginAction extends Action{
        
    @Override
    public void execute(HttpServletRequest request) {
        
        String email = request.getParameter("login");
        String numClient = request.getParameter("password");
        
        Client c = Services.authentifierClient(email, Integer.parseInt(numClient));
            
        if(c != null) {
            request.setAttribute("success", true);
            request.setAttribute("data", c);
        }else {
            request.setAttribute("success", false);
        }
    }
}
