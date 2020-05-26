/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.dasi.ihm.web.action;

import fr.insalyon.dasi.metier.modele.Consultation;
import fr.insalyon.dasi.metier.modele.Employe;
import fr.insalyon.dasi.metier.modele.Statut;
import fr.insalyon.dasi.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author emman
 */
public class LacherCommentaireAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        Service s = new Service();
        
        System.out.println(request.getParameter("id"));
        Long employeId = Long.parseLong(request.getParameter("id"));
        Employe emp = s.rechercherEmployeParId(employeId);
        
        if(emp == null){
            request.setAttribute("success", false);
            return;
        }
        Consultation c = s.obtenirConsultationAFaire(emp);
                
        if(c == null){
            request.setAttribute("success", false);
            return;
        }
        
        s.terminerConsultation(c, emp, request.getParameter("text"));
        
        request.setAttribute("success", true);
        System.out.println("Succès LacherCommentaireAction");
        
    }
    
}
