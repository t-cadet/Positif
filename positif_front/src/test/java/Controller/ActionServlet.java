package Controller;


import View.Serialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import fr.insalyon.dasi.positif.dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tcadet
 */

public class ActionServlet extends HttpServlet {
    public static final SimpleDateFormat HOUR = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat DATE_HOUR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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
        switch(todo) {
            case "registerClient" : {
                Action action = new RegisterClientAction();
                action.execute(request);
                Serialization.outputRegisterClient(request, response);                
            }
            default: {
                System.err.println("La requête " + request.toString() + " n'est pas une requête valide.");
            }
        }
    }
}
