/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import fr.insalyon.dasi.positif.service.Services;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Alexandre
 */
public class RepartitionVoyancesParEmployeAction extends Action{
        
    @Override
    public void execute(HttpServletRequest request) {
        
        TreeMap<Integer, Double> nbVM = Services.repartitionVoyances();

        System.out.println(nbVM.toString());
        
        if(nbVM != null) {
            request.setAttribute("success", true);
            request.setAttribute("data", nbVM);
        }else {
            request.setAttribute("success", false);
        }
    }
}
