package Action;


import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObtenirVoyanceDemandeeAction extends Action {
    
    HttpSession s;

    public ObtenirVoyanceDemandeeAction(HttpSession session) {
        s = session;
    }
       
    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = Services.obtenirVoyanceDemandee(Integer.parseInt(request.getParameter("no_client")));
        if(v!=null) {
            request.setAttribute("success", true);
            s.setAttribute("voyance", v);
        }
        else {
            request.setAttribute("success", false);
        }
    }    
}
