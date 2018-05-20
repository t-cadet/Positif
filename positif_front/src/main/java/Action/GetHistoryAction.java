/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexandre
 */
public class GetHistoryAction extends Action {
    
    private Client c;

    public GetHistoryAction(Client c) {
        this.c = c;
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        Collection<Voyance> history = Services.hitoriqueVoyancesClient(c);
        if(history!=null) {
            request.setAttribute("success", true);
            request.setAttribute("data", history);
        }
        else {
            request.setAttribute("success", false);
        }
    }
}
