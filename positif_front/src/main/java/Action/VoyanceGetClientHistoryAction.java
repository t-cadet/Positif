package Action;


import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VoyanceGetClientHistoryAction extends Action {
    HttpSession s;
    
    public VoyanceGetClientHistoryAction(HttpSession session) {
        s = session;
    }

    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = (Voyance)s.getAttribute("voyance");
        Client c = null;
        Collection<Voyance> history = null;
        if(v!=null) {
            c = v.getClient();
            System.out.println("VoyanceGetClientHistorytAction : v!=null");
            if(c!=null) {
                history = Services.hitoriqueVoyancesClient(c);
                System.out.println("VoyanceGetClientHistorytAction : c!=null");
            }
        }
        if(history!=null) {
            request.setAttribute("data", history);
            request.setAttribute("success", true);
            System.out.println("VoyanceGetClientHistorytAction : history!=null");
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
