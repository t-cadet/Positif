package Controller;


import fr.insalyon.dasi.positif.service.Services;
import javax.servlet.http.HttpServletRequest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tcadet
 */
public abstract class Action {
   public abstract void execute(HttpServletRequest request); 
}
