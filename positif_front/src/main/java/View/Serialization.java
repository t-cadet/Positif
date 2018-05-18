/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static Controller.ActionServlet.gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tcadet
 */
public class Serialization {   
    /*
    Renvoie la réponse au format suivant pour chaque action : 
    "success": (boolean) -- Requis
    "data": (Object) -- Optionel
    "forward": (String) -- Optionel
    */
    public static void outputResponse(HttpServletRequest request, HttpServletResponse response) {
        JsonObject resp = new JsonObject();
        if(request.getAttribute("success")!=null) {
            resp.add("success", gson.toJsonTree(request.getAttribute("success")));
        }
        if(request.getAttribute("data")!=null) {
            resp.add("data", gson.toJsonTree(request.getAttribute("data")));
        }
        if(request.getParameter("forward")!=null) {
            resp.addProperty("forward", request.getParameter("forward"));
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println(resp.toString());
        out.close();
    }
}
