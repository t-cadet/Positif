package Action;


import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Voyance;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VoyanceGetClientAction extends Action {
    HttpSession s;
    
    public VoyanceGetClientAction(HttpSession session) {
        s = session;
    }

    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = (Voyance)s.getAttribute("voyance");
        Client c = null;
        if(v!=null) {
            c = v.getClient();
            System.out.println("VoyanceGetClientAction : v!=null");
        }
        if(c!=null) {
            request.setAttribute("data", c);
            request.setAttribute("success", true);
            System.out.println("VoyanceGetClientAction : c!=null");
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
