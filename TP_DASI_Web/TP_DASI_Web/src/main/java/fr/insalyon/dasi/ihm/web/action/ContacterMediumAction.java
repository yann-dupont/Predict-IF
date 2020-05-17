/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class ContacterMediumAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request){
        System.out.println("ContacterMediumAction bien appelé");
        System.out.println(request.getParameter("id"));
        request.setAttribute("success", true);
        
        //request.setAttribute("success", false);
    }
}
