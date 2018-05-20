package Action;


import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.modele.Voyance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VoyanceGetMediumAction extends Action {
    HttpSession s;
    
    public VoyanceGetMediumAction(HttpSession session) {
        s = session;
    }

    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = (Voyance)s.getAttribute("voyance");
        Medium m = null;
        if(v!=null) {
            m = v.getMedium();
        }
        if(m!=null) {
            request.setAttribute("data", m);
            request.setAttribute("success", true);
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
