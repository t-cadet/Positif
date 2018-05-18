package Action;


import static Controller.ActionServlet.gson;
import fr.insalyon.dasi.positif.modele.Client;
import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DivinationAction extends Action {
    HttpSession s;
    
    public DivinationAction(HttpSession session) {
        s = session;
    }
    
    @Override
    public void execute(HttpServletRequest request) {
        Client c = (Client)s.getAttribute("user");
        Medium m = gson.fromJson(request.getParameter("medium"), Medium.class);
        if(c!=null && m!=null && Services.demanderVoyance(c, m)) {
            request.setAttribute("success", true);
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
