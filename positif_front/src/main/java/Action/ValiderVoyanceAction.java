package Action;


import fr.insalyon.dasi.positif.modele.Voyance;
import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ValiderVoyanceAction extends Action {
    HttpSession s;
    
    public ValiderVoyanceAction(HttpSession session) {
        s = session;
    }

    @Override
    public void execute(HttpServletRequest request) {
        Voyance v = (Voyance)s.getAttribute("voyance");
        String comment = request.getParameter("comment");
        System.out.println(comment);
        if(v!=null && Services.validerVoyance(v, comment)) {
            request.setAttribute("success", true);
            s.removeAttribute("voyance");
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
