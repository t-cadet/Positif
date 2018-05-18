package Controller;


import Action.Action;
import Action.LoginAction;
import Action.RegisterClientAction;
import View.Serialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import fr.insalyon.dasi.positif.modele.Client;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    public static final SimpleDateFormat HOUR = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_HOUR = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final Gson gson = new GsonBuilder().create();
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        super.destroy();
        JpaUtil.destroy();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String todo = request.getParameter("todo");
        System.out.println("service HTTPServletRequest");
        System.out.println(todo);
        
        HttpSession session = request.getSession(true);
                
        switch(todo) {
            case "registerClient" : {
                Action action = new RegisterClientAction();
                action.execute(request);
                Serialization.outputResponse(request, response);
                break;
            }
            case "login" : {
                Action action = new LoginAction();
                action.execute(request);
                Serialization.outputResponse(request, response);
                Client c = (Client)request.getAttribute("data");
                session.setAttribute("user", c);
                
                break;
            }
            case "deconnexion" : {
                session.invalidate();
                break;
            }
            case "session" : {
                request.setAttribute("data", session.getAttribute("user"));
                Serialization.outputResponse(request, response);
                
                break;
            }
            case "historiqueClient" : {
                request.setAttribute("data", session.getAttribute("user"));
                Serialization.outputResponse(request, response);
                
                break;
            }
            default: {
                System.err.println("La requête " + request.toString() + " n'est pas une requête valide.");
                break;
            }
        }
        System.out.println("Fin de service");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        service(req, resp);
    }

    public ActionServlet() {
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res); //To chan;ge body of generated methods, choose Tools | Templates.
        System.out.println("service ServletRequest");
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doOptions(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return super.getLastModified(req); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
