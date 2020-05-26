/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Client;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class LacherCommentaireAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        request.setAttribute("success", true);
        System.out.println("Succès LacherCommentaireAction");
        
    }
    
}
