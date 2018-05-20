package Action;


import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tcadet
 */
public class GenererPredictionAction extends Action {
    HttpSession s;
    
    public GenererPredictionAction(HttpSession session) {
        s = session;
    }
    
    
    @Override
    public void execute(HttpServletRequest request) {

        /*Map<String, String[]> m = request.getParameterMap();
        m.forEach((k,v) -> System.out.println("key: "+k));*/
        Voyance v = (Voyance)s.getAttribute("voyance");
        Client c = null;
        if(v!=null) {
            c = v.getClient();
        }
        if(c!=null) {
            int love = Integer.parseInt(request.getParameter("love"));
            int work = Integer.parseInt(request.getParameter("work"));
            int health = Integer.parseInt(request.getParameter("health"));
            List<String> predictions = Services.genererPrediction(c, love, health, work);
            if(predictions != null);
                request.setAttribute("data", predictions);
                request.setAttribute("success", true);
            }
            else {
                request.setAttribute("success", false);
            }
    }    
}
