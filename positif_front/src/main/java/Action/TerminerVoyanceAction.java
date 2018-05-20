package Action;


import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TerminerVoyanceAction extends Action {
    HttpSession s;
    
    public TerminerVoyanceAction(HttpSession session) {
        s = session;
    }

    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = (Voyance)s.getAttribute("voyance");
        if(v!=null && Services.terminerVoyance(v, new Date())) {
            request.setAttribute("success", true);
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
