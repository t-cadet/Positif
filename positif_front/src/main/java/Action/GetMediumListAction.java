package Action;


import fr.insalyon.dasi.positif.modele.Medium;
import fr.insalyon.dasi.positif.service.Services;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

public class GetMediumListAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {
        Collection<Medium> c = Services.listerMediums();
        if(c!=null) {
            request.setAttribute("success", true);
            request.setAttribute("data", c);
        }
        else {
            request.setAttribute("success", false);
        }
    }
    
}
