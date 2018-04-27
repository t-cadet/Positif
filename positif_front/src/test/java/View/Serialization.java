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
    private static void printResponse(HttpServletResponse response, JsonObject... json) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(Serialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = 0; i<json.length; i++) {
            out.println(gson.toJson(json[i]));
        }
        out.close();
    }
    public static void outputRegisterClient(HttpServletRequest request, HttpServletResponse response) {
        JsonObject success = new JsonObject();
        success.addProperty("success", request.getParameter("success"));     
        printResponse(response, success);
    }
}
